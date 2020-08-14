package com.appointment.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Time;

@Data
public class LessonRequest {
    @NotBlank(message = "Please provide a description")
    @Size(min = 25, max = 100, message = "Description must be between 25 and 100 characters")
    private String description;

    private Time timeFrom;
    private Time timeTo;
    @NotBlank
    private String price;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Future
    private Date reservedDate;
}
