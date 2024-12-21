package com.Oralens.Tests.Integration;

import com.Oralens.POJO.AppointmentBookingResponse;
import com.Oralens.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class IntegrationSc1_Create_Delete extends BaseTest {
    @Test(groups = "qa", priority = 1)
    @Owner("Tester")
    @Description("TC#INTSc1 - Step 1. Verify that the Appointment can be Created")
    public void testCreateBooking_I1(ITestContext iTestContext){
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);

        AppointmentBookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        assertActions.verifyStringKey(bookingResponse.getName(), "Garima");
        System.out.println(bookingResponse.getId());

        iTestContext.setAttribute("bookingid",bookingResponse.getId());
    }

    @Test(groups = "qa", priority = 4)
    @Owner("Tester")
    @Description("TC#INTSc1 - Step 2. Delete the Appointment by ID")
    public void testDeleteBookingById_I1(ITestContext iTestContext) {
        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathDELETE = "/" + bookingid;

        requestSpecification.basePath(basePathDELETE);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(200);

    }

}
