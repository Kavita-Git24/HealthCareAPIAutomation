package com.Oralens.Tests.CRUD;

import com.Oralens.POJO.AppointmentBookingResponse;
import com.Oralens.base.BaseTest;
import com.Oralens.endpoints.APIConstants;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

@Owner("Tester")
@TmsLink("https://google.com")
@Link(name = "Link to TC", url = "https://bugz.atlassian.net/browse/RBT-4")
@Issue("JIRA_RBT-4")
//@Description("Verify that POST request is working fine.")
@Test(groups = "qa")
public class testCreateAppointment extends BaseTest {
    public void testVerifyCreateBookingPOST01() {
        String basePathGet= APIConstants.CREATE_UPDATE_BOOKING_URL;
        System.out.println("BaseUrl is: " +baseUrl);


       // requestSpecification.basePath(basePathGet);

        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);

        AppointmentBookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        assertActions.verifyStringKey(bookingResponse.getName(), "Garima");
    }
}
