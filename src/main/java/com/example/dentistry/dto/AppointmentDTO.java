package com.example.dentistry.dto;

import com.example.dentistry.entities.Appointment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentDTO {
    private Integer id;
    private String date;
    private String time;
    private String patientName;
    private String comments;

    public AppointmentDTO(Integer id, LocalDateTime dateTime, String patientName, String comments) {
        this.id = id;
        this.date = dateTime.toLocalDate().toString();
        this.time = dateTime.toLocalTime().toString();
        this.patientName = patientName;
        this.comments = comments;
    }

}

