package com.mark.attendance.controller.requset;

import lombok.Data;

@Data
public class CheckInRequest {

    private String employeeId;
    private Double latitude;
    private Double longitude;
}
