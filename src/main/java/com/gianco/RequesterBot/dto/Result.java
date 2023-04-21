package com.gianco.RequesterBot.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Result {
    private boolean isFound = false;
    private String text;
}
