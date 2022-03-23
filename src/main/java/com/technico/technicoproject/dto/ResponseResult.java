package com.technico.technicoproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is a Generic Type of Response Result which is the object of Response to all Controllers
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {
    private T data;
    private ResponseStatus status;
    private String message;
}
