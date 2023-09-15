package stepDefinitions;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseSteps {

    public Headers headers;
    private String endpointPath;
    private Response response;
    public DocumentContext requestBodyJson;
    String ServiceUrl;
    String PostUrl;
    String PostPayloadPath;

    public BaseSteps() {
        ServiceUrl = "https://jsonplaceholder.typicode.com";
        PostUrl = ServiceUrl + "/posts/";
        PostPayloadPath = "/payloads/PostPayload.json";
    }

    public void setHeaders(Headers value) {
        restHeaders();
        headers = value;
    }

    private void restHeaders() {
        headers = null;
    }

    public void setHeadersWithContentType() {
        headers = new Headers(
                new Header("Content-Type", "application/json"));
        setHeaders(headers);
    }

    public void setHeadersWithContentTypeAndAccept() {
        headers = new Headers(
                new Header("Content-Type", "application/json"),
                new Header("Accept", "application/json"));
        setHeaders(headers);
    }

    protected URL getURL() {
        try {
            return new URL(endpointPath);
        } catch (MalformedURLException e) {
            throw new RuntimeException();
        }
    }

    public Response getCall() {
        response = RestAssured.given().log().all()
                .relaxedHTTPSValidation()
                .headers(headers)
                .when().get(getURL())
                .then().log().all().extract().response();
        return response;
    }

    //delete call
    public Response deleteCall() {
        response = RestAssured.given().log().all()
                .relaxedHTTPSValidation()
                .headers(headers)
                .when().delete(getURL())
                .then().log().all().extract().response();
        return response;
    }

    public Response postCall() {
        response = RestAssured.given().log().all().relaxedHTTPSValidation()
                .headers(headers)
                .body(requestBodyJson.jsonString())
                .when().post(getURL())
                .then().log().all().extract().response();
        return response;
    }

    //Put call
    public Response putCall() {
        response = RestAssured.given().log().all()
                .relaxedHTTPSValidation().headers(headers)
                .body(requestBodyJson.jsonString())
                .when().put(getURL())
                .then().log().all().extract().response();
        return response; }

    //patch
    public Response patchCall() {
        response = RestAssured.given().log().all()
                .relaxedHTTPSValidation().headers(headers)
                .body(requestBodyJson.jsonString())
                .when().patch(getURL())
                .then().log().all().extract().response();
        return response; }

    public void setEndpointPath(String endpointPath) {
        this.endpointPath = endpointPath;
    }

    public DocumentContext loadJsonTemplate(String path) {
        requestBodyJson = JsonPath.parse(this.getClass().getResourceAsStream(path));
        return requestBodyJson;
    }
}

