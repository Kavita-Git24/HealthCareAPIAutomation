package com.Oralens.Tests.Integration;

import com.Oralens.POJO.AppointmentBooking;
import com.Oralens.POJO.AppointmentBookingResponse;
import com.Oralens.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationSc2_Get_Delete extends BaseTest {
    @Test(groups = "IntSc2", priority = 1)
    @Owner("Tester")
    @Description("TC#INTSc2 - Step 1. Verify that the Appointment By ID")
    public void testVerifyBookingId(ITestContext iTestContext){

        response = RestAssured
                .given(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);
    }

    @Test(groups = "IntSc2", priority = 2)
    @Owner("Tester")
    @Description("TC#INTSc2 - Step 2. Delete the Appointment by ID")
    public void testDeleteBookingById_I1(ITestContext iTestContext) {
        iTestContext.setAttribute("bookingid",1);

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        // GET Request - to verify that the firstname after creation
//        String basePathGET = "/" + bookingid;
//        System.out.println(basePathGET);


        String basePathDELETE = "/" + bookingid;

        requestSpecification.basePath(basePathDELETE);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(200);

    }

}
