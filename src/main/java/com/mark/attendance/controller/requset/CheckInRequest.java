package com.mark.attendance.controller.requset;

import lombok.Data;

@Data
public class CheckInRequest {

    private Long employeeId;
    private Double latitude;
    private Double longitude;
}
