package com.teclinecg.noxus.enums;

public enum PizzaSizeEnum {
    SMALL(1),
    MEDIUM(2),
    LARGE(3);

    private final int pizzaSizeCode;

    PizzaSizeEnum(int pizzaSizeCode) {
        this.pizzaSizeCode = pizzaSizeCode;
    }

    public int getPizzaSizeCode() {
        return pizzaSizeCode;
    }

    public static PizzaSizeEnum fromPizzaSizeCode(int pizzaSizeCode) {
        for (PizzaSizeEnum pizzaSize : PizzaSizeEnum.values()) {
            if (pizzaSize.getPizzaSizeCode() == pizzaSizeCode) {
                return pizzaSize;
            }
        }
        throw new IllegalArgumentException("Código de Pizza Size inválido: " + pizzaSizeCode);
    }
}
