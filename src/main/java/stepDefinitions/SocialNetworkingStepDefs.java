package stepDefinitions;

import com.jayway.jsonpath.DocumentContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utilities.RequestBodyService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SocialNetworkingStepDefs extends BaseSteps {

    Response responseForServiceCall;
    Response responseForGetSpecificPost;
    Response responseForCreatePost;
    Response responseForDeleteAPost;
    Response responseForPutPost;

    @Given("service is up and running")
    public void service_is_up_and_running() {
        setHeadersWithContentType();
        setEndpointPath(ServiceUrl);
        responseForServiceCall = getCall();
        assertThat(responseForServiceCall.statusCode(), equalTo(200));
    }

    @When("I send GET request to get a specific post using id {string}")
    public void i_send_get_request_to_get_a_specific_post_using_id(String id) {
    setHeadersWithContentType();
    setEndpointPath(PostUrl + id);
        responseForGetSpecificPost = getCall();

    }

    @Then("the specific post details{string}, {string} and {string} are returned with status code of {int}")
    public void theSpecificPostDetailsAndAreReturnedWithStatusCodeOf(String id, String title, String body, int statCode) {
        assertThat(responseForGetSpecificPost.statusCode(),equalTo(statCode) );
        assertThat(responseForGetSpecificPost.body().jsonPath().get("id"),equalTo(Integer.parseInt(id)) );
        assertThat(responseForGetSpecificPost.body().jsonPath().get("title"),equalTo(title) );
        assertThat(responseForGetSpecificPost.body().jsonPath().get("body"),equalTo(body) );
    }

    @When("I post with these details {string}, {string} and {string}")
    public void iPostWithTheseDetailsAnd(String uId, String title, String body) {
        setHeadersWithContentType();
        setEndpointPath(PostUrl);
        RequestBodyService requestBodyService = new RequestBodyService();
        DocumentContext reqBody = loadJsonTemplate(PostPayloadPath);
        requestBodyService.setRequestBodyForPost(reqBody,uId,title,body);
        responseForCreatePost = postCall();
    }

    @Then("I should get response with statusCode of {int} and the following {string}, {string} and {string}")
    public void iShouldGetResponseWithStatusCodeOfAndTheFollowingAnd(int sCode, String uId, String title, String body) {
        assertThat(responseForCreatePost.statusCode(),equalTo(sCode) );
        assertThat(responseForCreatePost.body().jsonPath().get("userId"),equalTo(uId) );
        assertThat(responseForCreatePost.body().jsonPath().get("title"),equalTo(title) );
        assertThat(responseForCreatePost.body().jsonPath().get("body"),equalTo(body) );
    }

    @When("I send Delete request to delete a specific post using id {string}")
    public void iSendDeleteRequestToDeleteASpecificPostUsingId(String id) {
        setHeadersWithContentType();
        setEndpointPath(PostUrl + id);
        responseForDeleteAPost = deleteCall();
    }

    @Then("status code of {int} is returned")
    public void statusCodeOfIsReturned(int sCode) {
        assertThat(responseForDeleteAPost.statusCode(),equalTo(sCode) );
    }

    @When("I update record {string} with these details {string}, {string} and {string}")
    public void iUpdateRecordWithTheseDetailsAnd(String id, String uId, String title, String body) {
        setHeadersWithContentType();
        setEndpointPath(PostUrl + id);
        RequestBodyService requestBodyService = new RequestBodyService();
        DocumentContext reqBody = loadJsonTemplate(PostPayloadPath);
        requestBodyService.setRequestBodyForPost(reqBody,uId,title,body);
        responseForPutPost = putCall();
    }

    @Then("I should get response with statusCode of {int} and the following {string}, {string} and {string} for the Update")
    public void iShouldGetResponseWithStatusCodeOfAndTheFollowingAndForTheUpdate(int sCode, String uId, String title, String body) {
        assertThat(responseForPutPost.statusCode(),equalTo(sCode) );
        assertThat(responseForPutPost.body().jsonPath().get("userId"),equalTo(uId) );
        assertThat(responseForPutPost.body().jsonPath().get("title"),equalTo(title) );
        assertThat(responseForPutPost.body().jsonPath().get("body"),equalTo(body) );
    }
}
