package com.appointment.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Time;

@Data
public class LessonRequest {
    @NotBlank
    @Size(min = 25, max = 100)
    private String description;
    @NotEmpty
    private Time timeFrom;
    @NotEmpty
    private Time timeTo;
    @NotBlank
    private String price;
    @NotEmpty
    private Date reservedDate;
}
