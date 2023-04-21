package com.gianco.RequesterBot.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Appointment {
    private String date;
    private String time;
    private String location;
}
