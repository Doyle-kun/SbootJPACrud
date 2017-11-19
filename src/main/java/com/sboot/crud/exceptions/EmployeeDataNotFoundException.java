package com.sboot.crud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Employee") // 404
public class EmployeeDataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 6018474289239120962L;

}
