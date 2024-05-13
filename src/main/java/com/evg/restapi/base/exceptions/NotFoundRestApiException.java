package com.evg.restapi.base.exceptions;

import com.evg.restapi.base.exceptions.errors.RestApiErrorCode;
import com.evg.restapi.base.exceptions.errors.RestApiErrorDetail;
import org.springframework.http.HttpStatus;

import java.util.List;

public class NotFoundRestApiException extends CustomRestApiException {


    public NotFoundRestApiException(RestApiErrorCode code) {
        super(HttpStatus.NOT_FOUND, code);
    }

    public NotFoundRestApiException(RestApiErrorCode code, String customMessage) {
        super(HttpStatus.NOT_FOUND, code, customMessage);
    }

    public NotFoundRestApiException(RestApiErrorCode code, List<RestApiErrorDetail> errorsDetail) {
        super(HttpStatus.NOT_FOUND, code, errorsDetail);
    }

    public NotFoundRestApiException(RestApiErrorCode code, String customMessage, List<RestApiErrorDetail> errorsDetail) {
        super(HttpStatus.NOT_FOUND, code, customMessage, errorsDetail);
    }
}
