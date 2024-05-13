package com.evg.restapi.base.exceptions.errors;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RestApiErrorKey {

    // Define las keys de los errores

    ERROR(1000, "error.generico");

    private final int value;
    private final String key;

    RestApiErrorKey(int value, String key){
        this.value = value;
        this.key = key;
    }

    /**
     * Return the integer value of this rest api code.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Return the message of this rest api code.
     */
    public String getKey() {
        return this.key;
    }

}
