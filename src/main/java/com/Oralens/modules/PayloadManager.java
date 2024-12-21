package com.Oralens.modules;

import com.Oralens.POJO.AppointmentBooking;
import com.Oralens.POJO.AppointmentBookingResponse;
import com.google.gson.Gson;

public class PayloadManager {
    Gson gson;
    public String createPayloadBookingAsString() {
        /*{
        "id": "30",
            "name": "Sohan",
            "age": 85,
            "appointment_date": "2024-12-11",
            "doctor_name": "Dr.Satya",
            "comment" : "Body Checkup"
    }*/
        AppointmentBooking booking = new AppointmentBooking();
        booking.setId(2);
        booking.setName("Mahi");
        booking.setAge(25);
        booking.setAppointmentDate("2018-01-01");
        booking.setDoctorName("DrHansa");
        booking.setComment("Physiotherapy");

        //System.out.println(booking);

        gson = new Gson();
        String jsonStringpayload = gson.toJson(booking);
        System.out.println(jsonStringpayload);
        return jsonStringpayload;
    }
    // Converting the String to the JAVA Object
    public AppointmentBookingResponse bookingResponseJava(String responseString) {
        gson = new Gson();
        AppointmentBookingResponse bookingResponse = gson.fromJson(responseString, AppointmentBookingResponse.class);
        return bookingResponse;
    }

    public AppointmentBooking getResponseFromJSON(String getResponse){
        gson = new Gson();
        AppointmentBooking booking = gson.fromJson(getResponse,AppointmentBooking.class);
        return booking;
    }

    public String fullUpdatePayloadAsString() {
        AppointmentBooking booking = new AppointmentBooking();
        booking.setName("Koyal");
        booking.setAge(25);
        booking.setAppointmentDate("2018-01-01");
        booking.setDoctorName("Dr. Shilpa");
        booking.setComment("Physiotherapy");
        return gson.toJson(booking);
    }
}
