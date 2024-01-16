package de.bht.azur;

import de.bht.azur.model.Appointment;
import de.bht.azur.model.Appointment;
import de.bht.azur.model.Appointment;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.derby.DerbyDatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
@QuarkusTestResource(DerbyDatabaseTestResource.class)
public class AppointmentControllerTest extends ParentTest{

        @BeforeEach
        void beforeEach() {
                cleanup();
        }

        @Test
        public void testGetAppointments() {
                Appointment appointment = new Appointment();
                appointment.setTitle("Abschlussfeier");
                appointment.setDescription("Abschlussfeier der Azur Gruppe");
                appointment.setStart(LocalDateTime.now());
                appointment.setEnd(LocalDateTime.now().plus(Duration.ofHours(2)));

                // fetch all appointments
                given()
                                .when().get("/appointments")
                                .then()
                                .statusCode(200)
                                .body("", hasSize(0));

                // create new appointment
                int appointment_id = given()
                                .body(appointment)
                                .contentType(ContentType.JSON)
                                .when()
                                .post("/appointments")
                                .then()
                                .statusCode(202)
                                .extract().path("id");

                // fetch all appointments
                given()
                                .when().get("/appointments")
                                .then()
                                .statusCode(200)
                                .body("", hasSize(1));

                // fetch single appointment
                given()
                                .when().get("appointments/" + appointment_id)
                                .then()
                                .statusCode(200)
                                .body("title", equalTo("Abschlussfeier"));

                // change appointment
                appointment.setTitle("Party an der BHT");
                given()
                                .body(appointment)
                                .contentType(ContentType.JSON)
                                .when()
                                .put("/appointments/" + appointment_id)
                                .then()
                                .body("id", equalTo(1))
                                .statusCode(202);

                // fetch single appointment
                given()
                                .when().get("appointments/" + appointment_id)
                                .then()
                                .statusCode(200)
                                .body("title", equalTo("Party an der BHT"));

                // delete appointment
                given()
                                .when().delete("appointments/" + appointment_id)
                                .then()
                                .statusCode(204);

                // fetch all appointments
                given()
                                .when().get("/appointments")
                                .then()
                                .statusCode(200)
                                .body("", hasSize(0));
        }
}
