package com.evg.restapi.base.exceptions.errors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

/**
 * Created by javier-montesinos on 8/03/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestApiError {

    // Unix timestamp
    private long timestamp;

    @JsonIgnore
    private HttpStatus status;

    private RestApiErrorCode code;
    private String customMessage;
    private List<RestApiErrorDetail> errorsDetail;

    public RestApiError() {
        // Tiempo en formato UNIX en milisegundos
        this.timestamp = Instant.now().toEpochMilli();
    }

    public RestApiError(RestApiErrorCode code) {
        this();
        this.code = code;
    }

    public RestApiError(HttpStatus status, RestApiErrorCode code) {
        this(code);
        this.status = status;
    }

    public RestApiError(RestApiErrorCode code, String customMessage) {
        this(code);
        this.customMessage = customMessage;
    }

    public RestApiError(HttpStatus status, RestApiErrorCode code, String customMessage) {
        this(code, customMessage);
        this.status = status;
    }

    public RestApiError(RestApiErrorCode code, List<RestApiErrorDetail> errorsDetail) {
        this(code);
        this.errorsDetail = errorsDetail;
    }

    public RestApiError(HttpStatus status, RestApiErrorCode code, List<RestApiErrorDetail> errorsDetail) {
        this(code, errorsDetail);
        this.status = status;
    }

    public RestApiError(RestApiErrorCode code, String customMessage, List<RestApiErrorDetail> errorsDetail) {
        this(code, customMessage);
        this.errorsDetail = errorsDetail;
    }

    public RestApiError(HttpStatus status, RestApiErrorCode code, String customMessage, List<RestApiErrorDetail> errorsDetail) {
        this(code, customMessage, errorsDetail);
        this.status = status;
    }


    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public RestApiErrorCode getCode() {
        return code;
    }

    public void setCode(RestApiErrorCode code) {
        this.code = code;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    public List<RestApiErrorDetail> getErrorsDetail() {
        return errorsDetail;
    }

    public void setErrorsDetail(List<RestApiErrorDetail> errorsDetail) {
        this.errorsDetail = errorsDetail;
    }
}
