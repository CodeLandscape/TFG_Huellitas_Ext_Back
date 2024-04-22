package com.preving.restapi.base.exceptions;

import com.preving.restapi.base.exceptions.errors.RestApiError;
import com.preving.restapi.base.exceptions.errors.RestApiErrorCode;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ CustomRestApiException.class})
    public ResponseEntity<Object> handleAll(final CustomRestApiException ex) {

        // Mostramos por consola todos los errores que no sean de validacion ni de autenticacion
        if (ex.getError() != null &&
                (!HttpStatus.UNPROCESSABLE_ENTITY.equals(ex.getError().getStatus())) &&
                (!HttpStatus.UNAUTHORIZED.equals(ex.getError().getStatus()))) {
            ex.printStackTrace();
        }

        return new ResponseEntity<Object>(ex.getError(), new HttpHeaders(), ex.getError().getStatus());
    }

    @ExceptionHandler({DataAccessException.class })
    public ResponseEntity<Object> handleAll(DataAccessException ex) {
        ex.printStackTrace();
        RestApiError rae = new RestApiError(RestApiErrorCode.ERROR_ACCESO_DATOS);
        return new ResponseEntity<Object>(rae, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex) {
        ex.printStackTrace();
        RestApiError rae = new RestApiError(RestApiErrorCode.EXCEPCION_NO_CONTROLADA);
        return new ResponseEntity<Object>(rae, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
