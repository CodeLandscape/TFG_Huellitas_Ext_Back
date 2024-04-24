package com.preving.restapi.base.exceptions.errors;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RestApiErrorCode {

    USER_INCORRECT(1001, "Revise los datos del usuario. Validación de datos incorrecta"),

    USER_NOT_FOUND(1002, "No se encuentra el usuario en el sistema"),


    // Definir nuevos codigos de error aqui

    USER_MANDATORY(1003, "El usuario es obligatorio"),
    PROVINCE_MANDATORY(1004, "La provincia es obligatoria"),
    PROVINCE_NOT_FOUND(1005, "La provincia no se encuentra en el sistema"),
    REQUEST_NOT_FOUND(1006, "La solicitud no se encuentra en el sistema"),

    CANDIDATE_NOT_FOUND(1007, "El candidato no se encuentra en el sistema"),

    WRONG_FIELDS(1008, "Revise los datos del usuario. Validación de datos incorrecta"),

    USER_ALREADY_EXISTS(1009, "El usuario ya existe en el sistema"),

    // códigos >= 9000 para excepciones no controladas y otros errores
    EXCEPCION_NO_CONTROLADA(9000, "Excepción no controlada por el sistema"),

    ERROR_ACCESO_DATOS(9001, "Error de acceso a datos");

    private final int value;
    private final String message;

    RestApiErrorCode(int value, String message) {
        this.value = value;
        this.message = message;
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
    public String getMessage() {
        return this.message;
    }

    public String toString() {
        return Integer.toString(this.value);
    }
    /**
     * Return the enum constant of this type with the specified numeric value.
     * @param codeValue the numeric value of the enum to be returned
     * @return the enum constant with the specified numeric value
     * @throws IllegalArgumentException if this enum has no constant for the specified numeric value
     */
    public static RestApiErrorCode valueOf(int codeValue) {
        for (RestApiErrorCode code : values()) {
            if (code.value == codeValue) {
                return code;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + codeValue + "]");
    }



}
