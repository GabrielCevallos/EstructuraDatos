package com.example.models.enumerator;

public enum TipoIdentificacion {
    CEDULA("CEDULA"), PASAPORTE("PASAPORTE"), RUC("RUC");

    private String name;

    TipoIdentificacion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
