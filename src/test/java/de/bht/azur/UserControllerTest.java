package de.bht.azur;

import de.bht.azur.model.Appointment;
import de.bht.azur.model.Group;
import de.bht.azur.model.User;
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
public class UserControllerTest {


    @Test
    public void testGetUsers() {
        User user = new User();
        user.setGivenName("Max");
        user.setFamilyName("Mustermann");
        user.setEmail("test.test@gmail.com");


        // fetch all users
        given()
                .when().get("/users")
                .then()
                .statusCode(200)
                .body("", hasSize(0));

        // create new user
        int user_id = given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post("/users")
                .then()
                .statusCode(202)
                .extract().path("id");

        // fetch all users
        given()
                .when().get("/users")
                .then()
                .statusCode(200)
                .body("", hasSize(1));

        // fetch single user
        given()
                .when().get("users/" + user_id)
                .then()
                .statusCode(200)
                .body("givenName", equalTo("Max"));

        // change user
        user.setGivenName("Martin");
        given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .put("/users/" + user_id)
                .then()
                .body("id", equalTo(1))
                .statusCode(202);

        // fetch single user
        given()
                .when().get("users/" + user_id)
                .then()
                .statusCode(200)
                .body("givenName", equalTo("Martin"));

        // delete user
        given()
                .when().delete("users/" + user_id)
                .then()
                .statusCode(204);

        // fetch all users
        given()
                .when().get("/users")
                .then()
                .statusCode(200)
                .body("", hasSize(0));
    }

    @Test
    public void testUserAppointments() {
        User user = new User();
        user.setGivenName("Max");
        user.setFamilyName("Mustermann");
        user.setEmail("test.test@gmail.com");

        Appointment appointment = new Appointment();
        appointment.setTitle("Weihnachtsfeier");
        appointment.setStart(LocalDateTime.now());
        appointment.setEnd(LocalDateTime.now().plus(Duration.ofHours(4)));
        appointment.setDescription("Jährliche Weihnachtsfeier");

        // create new user
        int user_id = given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post("/users")
                .then()
                .statusCode(202)
                .extract().path("id");

        //create new appointment
        int appointment_id = given()
                .body(appointment)
                .contentType(ContentType.JSON)
                .when()
                .post("/appointments")
                .then()
                .statusCode(202)
                .extract().path("id");

        // fetch all appointments for user
        given()
                .when().get("/users/" + user_id + "/appointments")
                .then()
                .statusCode(200)
                .body("", hasSize(0));

        // invite user to appointment
        given()
                .when().post("/users/" + user_id + "/appointments/" + appointment_id)
                .then()
                .statusCode(202);

        // fetch all appointments for user
        given()
                .when().get("/users/" + user_id + "/appointments")
                .then()
                .statusCode(200)
                .body("", hasSize(1))
                .body("[0].status", equalTo("INVITED"));

        // user accepts invitation
        given()
                .when().put("/users/" + user_id + "/appointments/" + appointment_id + "/status/ACCEPTED")
                .then()
                .statusCode(202);

        // fetch all appointments for user
        given()
                .when().get("/users/" + user_id + "/appointments")
                .then()
                .statusCode(200)
                .body("", hasSize(1))
                .body("[0].status", equalTo("ACCEPTED"));

        // delete appointment for user
        given()
                .when().delete("/users/" + user_id + "/appointments/" + appointment_id)
                .then()
                .statusCode(204);

        // fetch all appointments for user
        given()
                .when().get("/users/" + user_id + "/appointments")
                .then()
                .statusCode(200)
                .body("", hasSize(0));
    }

    @Test
    public void testUserGroups() {
        User user = new User();
        user.setGivenName("Max");
        user.setFamilyName("Mustermann");
        user.setEmail("test.test@gmail.com");

        Group group = new Group();
        group.setName("Azur");

        // create new user
        int user_id = given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .post("/users")
                .then()
                .statusCode(202)
                .extract().path("id");

        //create new group
        int group_id = given()
                .body(group)
                .contentType(ContentType.JSON)
                .when()
                .post("/groups")
                .then()
                .statusCode(202)
                .extract().path("id");

        // fetch all groups for user
        given()
                .when().get("/users/" + user_id + "/groups")
                .then()
                .statusCode(200)
                .body("", hasSize(0));

        // invite user to group
        given()
                .when().post("/users/" + user_id + "/groups/" + group_id)
                .then()
                .statusCode(202);

        // fetch all groups for user
        given()
                .when().get("/users/" + user_id + "/groups")
                .then()
                .statusCode(200)
                .body("", hasSize(1))
                .body("[0].status", equalTo("INVITED"));

        // user accepts invitation
        given()
                .when().put("/users/" + user_id + "/groups/" + group_id + "/status/JOINED")
                .then()
                .statusCode(202);

        // fetch all groups for user
        given()
                .when().get("/users/" + user_id + "/groups")
                .then()
                .statusCode(200)
                .body("", hasSize(1))
                .body("[0].status", equalTo("JOINED"));

        // delete group for user
        given()
                .when().delete("/users/" + user_id + "/groups/" + group_id)
                .then()
                .statusCode(204);

        // fetch all groups for user
        given()
                .when().get("/users/" + user_id + "/groups")
                .then()
                .statusCode(200)
                .body("", hasSize(0));
    }

}
