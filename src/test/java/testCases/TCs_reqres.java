package testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.baseURI;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//static ... to use given() directly without creating object

public class TCs_reqres {


	@BeforeClass
	public void before() {
		baseURI = "https://reqres.in/";
	}


	@Test(priority =1)
	public void getUserList() {

		given()
		.get("/api/users")
		.then()
		.statusCode(200)
		.log().all();
	}

	@Test(priority =2)
	public void singleUserNotFound() {

		given()
		.get("/api/users/23")
		.then()
		.statusCode(404)
		.log().all();
	}

	@Test(priority =3)
	public void successfulRegistration() {

		final String json = "{\"email\": \"sydney@fife\", \"password\": \"pistol\"}";


		given()
		.contentType("application/json")
		.body(json)
		.when()
		.post("/api/resgister")
		.then()
		.statusCode(201)
		.contentType("application/json")
		.body("token", equalTo("QpwL5tke4Pnpja7X4"))
		.log().all();
	}

	@Test(priority =4)
	public void unSuccessfulRegistration() {

		final String json = "{\"email\": \"sydney@fife\"}";

		given()
		.contentType("application/json")
		.body(json)
		.when()
		.post("/api/resgister")
		.then()
		.statusCode(400)
		.contentType("application/json")
		.body("error", equalTo("Missing password"))
		.log().all();
	}
}
