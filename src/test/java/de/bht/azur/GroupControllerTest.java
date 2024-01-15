package de.bht.azur;

import de.bht.azur.model.Appointment;
import de.bht.azur.model.Group;
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
}
