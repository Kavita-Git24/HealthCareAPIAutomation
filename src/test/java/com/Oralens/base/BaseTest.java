package com.Oralens.base;

import com.Oralens.asserts.AssertActions;
import com.Oralens.endpoints.APIConstants;
import com.Oralens.modules.PayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    public RequestSpecification requestSpecification;
    public Response response;
    public ValidatableResponse validatableResponse;
    public PayloadManager payloadManager;
    public AssertActions assertActions;
    public JsonPath jsonPath;
    public String baseUrl;
    @BeforeTest
    public void setup()
    {
        // BASE URL, Content Type JSON
        payloadManager = new PayloadManager();
        assertActions=new AssertActions();

        requestSpecification= RestAssured
                .given()
                .baseUri(APIConstants.BASE_URL)
                .contentType(ContentType.JSON)
                .log().all();
        baseUrl=APIConstants.BASE_URL;
    }

}

