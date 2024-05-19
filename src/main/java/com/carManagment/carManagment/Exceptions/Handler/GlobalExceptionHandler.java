package com.carManagment.carManagment.Exceptions.Handler;

import com.carManagment.carManagment.Exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            InvalidCivilInsuranceException.class,
            InvalidCASKOException.class,
            InvalidRepairException.class,
            InvalidTechnicalInspectionException.class,
            InvalidVignetteException.class,
            InvalidTireException.class
    })
    @ResponseBody
    public ResponseEntity<?> handleInvalidException(Exception ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getClass().getSimpleName(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(errorResponse.status).contentType(MediaType.APPLICATION_JSON).body(errorResponse.toString());
    }

    @ExceptionHandler({
            CarNotFoundException.class,
            CivilInsuranceNotFoundException.class,
            CASKONotFoundException.class,
            RepairNotFoundException.class,
            TechnicalInspectionNotFoundException.class,
            VignetteNotFoundException.class,
            TireNotFoundException.class,
            EmployeeNotFoundException.class,
            EmployeeCreationException.class
    })
    @ResponseBody
    public ResponseEntity<?> handleNotFoundException(RuntimeException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getClass().getSimpleName(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(errorResponse.status).contentType(MediaType.APPLICATION_JSON).body(errorResponse.toString());
    }

    @Getter
    static class ErrorResponse {
        private final LocalDateTime timestamp;
        private final int status;
        private final String error;
        private final String message;
        private final String path;

        public ErrorResponse(HttpStatus status, String error, String message, String path) {
            this.timestamp = LocalDateTime.now();
            this.status = status.value();
            this.error = error;
            this.message = message;
            this.path = path;
        }

        @Override
        public String toString() {
            return "{" + "\n" +
                    "   \"timestamp\": \"" + timestamp + "\",\n" +
                    "   \"status\": \"" + status + "\",\n" +
                    "   \"error\": \"" + error + "\",\n" +
                    "   \"message\": \"" + message + "\",\n" +
                    "   \"path\": \"" + path + "\"\n" +
                    "}";
        }
    }
}