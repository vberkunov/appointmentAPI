package com.appointment.payload.request;

import lombok.Data;

import java.sql.Date;

@Data
public class LessonRequest {
    private String description;

    private Date reservedTime;

    private Date reservedDate;
}
