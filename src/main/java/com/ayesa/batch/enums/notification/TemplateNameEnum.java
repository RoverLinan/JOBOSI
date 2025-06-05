package com.ayesa.batch.enums.notification;

public enum TemplateNameEnum {

    TEMPLATE_MAIL_OUTLOOK_ERROR("KITERR-01","notification.mail.outlook.error");


    private final String propertyName;
    private final String kitName;

    TemplateNameEnum(String kitName, String propertyName ) {
        this.propertyName = propertyName;
        this.kitName = kitName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getKitName() {
        return kitName;
    }

    public static TemplateNameEnum fromKitName(String kitName) {
        for (TemplateNameEnum template : TemplateNameEnum.values()) {
            if (template.getKitName().equals(kitName)) {
                return template;
            }
        }
        throw new IllegalArgumentException("No enum constant with property name: " + kitName);
    }
}
