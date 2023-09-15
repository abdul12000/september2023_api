package restAssuredTest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestAssuredGet {

//Testing GET http method
    @Test
    public void getSpecificPost() {
        given().log().all().contentType(ContentType.JSON).
                when().get("https://jsonplaceholder.typicode.com/posts/{id}", 50).
                then().log().all().statusCode(200).body("title", equalTo("repellendus qui recusandae incidunt voluptates tenetur qui omnis exercitationem"));
    }

    //Testing DELETE Http method:
    @Test
    public void deleteSpecificPost() {
        given().log().all().contentType(ContentType.JSON).
                when().delete("https://jsonplaceholder.typicode.com/posts/{id}", 50).
                then().log().all().statusCode(200);
    }
}
