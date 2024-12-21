package com.Oralens.Tests.Integration;

import com.Oralens.POJO.AppointmentBooking;
import com.Oralens.POJO.AppointmentBookingResponse;
import com.Oralens.base.BaseTest;
import com.Oralens.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestIntegrationFlow extends BaseTest {
    //  Test Integration Scenario 1

    //  1. Create a Booking -> bookingID

    // 2. Verify that the Create Booking is working - GET Request to bookingID

    // 3. Update the booking ( bookingID, Token) - Need to get the bookingID from above request

    // 4. Delete the Booking - Need to get the bookingID from above request

    @Test(groups = "qa", priority = 1)
    @Owner("Tester")
    @Description("TC#INT1 - Step 1. Verify that the Appointment can be Created")
    public void testCreateBooking(ITestContext iTestContext){
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.createPayloadBookingAsString()).post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);

        AppointmentBookingResponse bookingResponse = payloadManager.bookingResponseJava(response.asString());
        assertActions.verifyStringKey(bookingResponse.getName(), "Garima");
        System.out.println(bookingResponse.getId());

        iTestContext.setAttribute("bookingid",bookingResponse.getId());
    }

    @Test(groups = "qa", priority = 2)
    @Owner("Tester")
    @Description("TC#INT1 - Step 2. Verify that the Appointment By ID")
    public void testVerifyBookingId(ITestContext iTestContext){
        System.out.println(iTestContext.getAttribute("bookingid"));
        Assert.assertTrue(true);

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        // GET Request - to verify that the firstname after creation is James
        String basePathGET = "/" + bookingid;
        System.out.println(basePathGET);

        requestSpecification.basePath(basePathGET);
        response = RestAssured
                .given(requestSpecification)
                .when().get();
        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        AppointmentBooking booking = payloadManager.getResponseFromJSON(response.asString());
        assertThat(booking.getName()).isNotNull().isNotBlank();
        assertThat(booking.getName()).isEqualTo("Garima");
    }

    @Test(groups = "qa", priority = 3)
    @Owner("Tester")
    @Description("TC#INT1 - Step 3. Verify Updated Appointment by ID")
    public void testUpdateBookingByID(ITestContext iTestContext){
        System.out.println(iTestContext.getAttribute("bookingid"));

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");


        String basePathPUTPATCH = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathPUTPATCH);

        requestSpecification.basePath(basePathPUTPATCH);

        response = RestAssured
                .given(requestSpecification)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();


        validatableResponse = response.then().log().all();
        // Validatable Assertion
        validatableResponse.statusCode(200);

        AppointmentBooking booking = payloadManager.getResponseFromJSON(response.asString());

        assertThat(booking.getName()).isNotNull().isNotBlank();
        assertThat(booking.getName()).isEqualTo("Koyal");
        assertThat(booking.getDoctorName()).isEqualTo("Dr. Shilpa");
    }

    @Test(groups = "qa", priority = 4)
    @Owner("Tester")
    @Description("TC#INT1 - Step 4. Delete the Appointment by ID")
    public void testDeleteBookingById(ITestContext iTestContext) {

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");

        String basePathDELETE = "/" + bookingid;

        requestSpecification.basePath(basePathDELETE);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(200);

    }

}

