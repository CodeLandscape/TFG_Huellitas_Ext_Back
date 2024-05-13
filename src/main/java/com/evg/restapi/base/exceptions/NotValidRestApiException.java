package com.evg.restapi.base.exceptions;

import com.evg.restapi.base.exceptions.errors.RestApiErrorCode;
import com.evg.restapi.base.exceptions.errors.RestApiErrorDetail;
import org.springframework.http.HttpStatus;

import java.util.List;

public class NotValidRestApiException extends CustomRestApiException {


    public NotValidRestApiException(RestApiErrorCode code) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, code);
    }

    public NotValidRestApiException(RestApiErrorCode code, String customMessage) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, code, customMessage);
    }

    public NotValidRestApiException(RestApiErrorCode code, List<RestApiErrorDetail> errorsDetail) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, code, errorsDetail);
    }

    public NotValidRestApiException(RestApiErrorCode code, String customMessage, List<RestApiErrorDetail> errorsDetail) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, code, customMessage, errorsDetail);
    }
}
