package restAssuredTest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestAssuredPost {

//Testing POST http method
    @Test
    public void CreateAPost() {
        HashMap<String, String> postBody = new HashMap<>();
        postBody.put("userId", "1");
        postBody.put("title", "My Holiday to Dubai");
        postBody.put("body", "I went a ,holiday to dubai with my family . we had a good time");

        given().log().all().contentType(ContentType.JSON).body(postBody).
                when().post("https://jsonplaceholder.typicode.com/posts").
                then().log().all().statusCode(201).body("title", equalTo("My Holiday to Dubai"));
    }

    //Testing PUT http method
    @Test
    public void PutAPost() {
        HashMap<String, String> postBody = new HashMap<>();
        postBody.put("userId", "1");
        postBody.put("title", "My Holiday to Dubai");
        postBody.put("body", "I went a ,holiday to dubai with my family . we had a good time");

        given().log().all().contentType(ContentType.JSON).body(postBody).
                when().put("https://jsonplaceholder.typicode.com/posts/1").
                then().log().all().statusCode(200).body("title", equalTo("My Holiday to Dubai"));
    }

    //Testing Patch http method
    @Test
    public void PatchAPost() {
        HashMap<String, String> postBody = new HashMap<>();
        postBody.put("userId", "1");
        postBody.put("title", "My Holiday to London");

        given().log().all().contentType(ContentType.JSON).body(postBody).
                when().patch("https://jsonplaceholder.typicode.com/posts/1").
                then().log().all().statusCode(200).body("title", equalTo("My Holiday to London"));
    }
}
