package com.ventas.abm.manager.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ErrorResponseDto {

    private LocalDateTime timestamp;
    private Integer status;
    private String message;

    public ErrorResponseDto() {
        super();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
