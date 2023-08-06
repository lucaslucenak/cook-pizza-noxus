package com.teclinecg.noxus.enums;

public enum StatusEnum {
    ACTIVE(1),
    INACTIVE(2);

    private final int statusCode;

    StatusEnum(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public static StatusEnum fromStatusCode(int statusCode) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.getStatusCode() == statusCode) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de status inválido: " + statusCode);
    }
}
