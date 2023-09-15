package utilities;

import com.jayway.jsonpath.DocumentContext;

public class RequestBodyService {
    public void setRequestBodyForPost(DocumentContext requestBody, String userId, String title, String body){
        requestBody.set("userId", userId );
        requestBody.set("title", title );
        requestBody.set("body", body );

    }
}
