package com.ayesa.batch.enums;

public enum CodeResponseOsinergminEnum {
    OSI_001("OSI-001", "Proceso culminó con éxito"),
    OSI_301("OSI-301", "Datos no válidos"),
    OSI_302("OSI-302", "Errores al procesar filas"),
    OSI_303("OSI-303", "La tabla indicada no existe"),
    OSI_304("OSI-304", "El archivo debe estar en formato zip y contener un archivo de texto"),
    OSI_305("OSI-305", "Ocurrió un error en la carga"),
    OSI_306("OSI-306", "El archivo zip supera el tamaño máximo permitido(10MB)"),
    OSI_307("OSI-307", "Usuario o clave no válidos"),
    OSI_901("OSI-901", "Ha ocurrido una excepción en la operación");

    private final String code;
    private final String message;

    CodeResponseOsinergminEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
