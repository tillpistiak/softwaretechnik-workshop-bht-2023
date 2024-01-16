package de.bht.azur;

import de.bht.azur.model.Appointment;
import de.bht.azur.model.Group;
import de.bht.azur.model.User;
import de.bht.azur.model.Group;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.derby.DerbyDatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
@QuarkusTestResource(DerbyDatabaseTestResource.class)
public class GroupControllerTest {

        @Test
        public void testGetGroups() {
                Group group = new Group();
                group.setName("Azur");

                // fetch all groups
                given()
                                .when().get("/groups")
                                .then()
                                .statusCode(200)
                                .body("", hasSize(0));

                // create new group
                int group_id = given()
                                .body(group)
                                .contentType(ContentType.JSON)
                                .when()
                                .post("/groups")
                                .then()
                                .statusCode(202)
                                .extract().path("id");

                // fetch all groups
                given()
                                .when().get("/groups")
                                .then()
                                .statusCode(200)
                                .body("", hasSize(1));

                // fetch single group
                given()
                                .when().get("groups/" + group_id)
                                .then()
                                .statusCode(200)
                                .body("name", equalTo("Azur"));

                // change group
                group.setName("BHT");
                given()
                                .body(group)
                                .contentType(ContentType.JSON)
                                .when()
                                .put("/groups/" + group_id)
                                .then()
                                .body("id", equalTo(1))
                                .statusCode(202);

                // fetch single group
                given()
                                .when().get("groups/" + group_id)
                                .then()
                                .statusCode(200)
                                .body("name", equalTo("BHT"));

                // delete group
                given()
                                .when().delete("groups/" + group_id)
                                .then()
                                .statusCode(204);

                // fetch all groups
                given()
                                .when().get("/groups")
                                .then()
                                .statusCode(200)
                                .body("", hasSize(0));
        }

        @Test
        public void testGroupInvitations() {
                Group group = new Group();
                User user = new User();
                Appointment appointment = new Appointment();

                // create new group
                int group_id = given()
                                .body(group)
                                .contentType(ContentType.JSON)
                                .when()
                                .post("/groups")
                                .then()
                                .statusCode(202)
                                .extract().path("id");

                // create new appointment
                int appointment_id = given()
                                .body(appointment)
                                .contentType(ContentType.JSON)
                                .when()
                                .post("/appointments")
                                .then()
                                .statusCode(202)
                                .extract().path("id");
                // create new user 1
                int user_id = given()
                                .body(user)
                                .contentType(ContentType.JSON)
                                .when()
                                .post("/users")
                                .then()
                                .statusCode(202)
                                .extract().path("id");
                // create new user 2
                int user_id2 = given()
                                .body(user)
                                .contentType(ContentType.JSON)
                                .when()
                                .post("/users")
                                .then()
                                .statusCode(202)
                                .extract().path("id");
                // create new user 3
                int user_id3 = given()
                                .body(user)
                                .contentType(ContentType.JSON)
                                .when()
                                .post("/users")
                                .then()
                                .statusCode(202)
                                .extract().path("id");
                // invite user 1 to group 1 (POST /users/1/groups/1
                given()
                                .when().post("/users/" + user_id + "/groups/" + group_id)
                                .then()
                                .statusCode(202);
                // invite user 2 to group 1 (POST /users/2/groups/1
                given()
                                .when().post("/users/" + user_id2 + "/groups/" + group_id)
                                .then()
                                .statusCode(202);
                // invite user 3 to group 1 (POST /users/3/groups/1
                given()
                                .when().post("/users/" + user_id3 + "/groups/" + group_id)
                                .then()
                                .statusCode(202);
                // update user 1 status for group 1 to JOINED
                given()
                                .when().put("/users/" + user_id + "/groups/" + group_id + "/status/JOINED")
                                .then()
                                .statusCode(202);
                // update user 2 status for group 1 to JOINED
                given()
                                .when().put("/users/" + user_id2 + "/groups/" + group_id + "/status/JOINED")
                                .then()
                                .statusCode(202);
                // call POST /groups/1/appointments/1
                given()
                                .when().post("/groups/" + group_id + "/appointments/" + appointment_id)
                                .then()
                                .statusCode(202);
                // check if user 1 is invited to appointment 1
                given()
                                .when().get("/users/" + user_id + "/appointments/")
                                .then()
                                .statusCode(200)
                                .body("", hasSize(1));
                // check if user 2 is invited to appointment 1
                given()
                                .when().get("/users/" + user_id2 + "/appointments/")
                                .then()
                                .statusCode(200)
                                .body("", hasSize(1));
                // check if user 3 is not invited to appointment 1
                given()
                                .when().get("/users/" + user_id3 + "/appointments/")
                                .then()
                                .statusCode(200)
                                .body("", hasSize(0));

        }

}
