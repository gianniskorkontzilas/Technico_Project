package com.technico.technicoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {
    private T data;
    private ResponseStatus status;
    private String message;
}
