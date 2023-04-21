package com.gianco.RequesterBot.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Response {
    private List<Appointment> appointmentsList;
}
