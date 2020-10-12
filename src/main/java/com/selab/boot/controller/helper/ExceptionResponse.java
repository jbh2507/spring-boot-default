package com.selab.boot.controller.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private ZonedDateTime timestamp;
    private String exception;
    private String message;
    private String detail;

}
