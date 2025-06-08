package com.ayesa.batch.business.dto.notification;

import java.util.Objects;

public class MailParameterDTO extends NotificationParameterDTO {
    private String host;
    private int port;
    private String mailFrom;
    private String mailTo;
    private String cc;
    private String subject;
    private String type;
    private int option;
    private String message;
    private String attachmentDirectory;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAttachmentDirectory() {
        return attachmentDirectory;
    }

    public void setAttachmentDirectory(String attachmentDirectory) {
        this.attachmentDirectory = attachmentDirectory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailParameterDTO that = (MailParameterDTO) o;
        return port == that.port && option == that.option && Objects.equals(host, that.host) && Objects.equals(mailFrom, that.mailFrom) && Objects.equals(mailTo, that.mailTo) && Objects.equals(cc, that.cc) && Objects.equals(subject, that.subject) && Objects.equals(type, that.type) && Objects.equals(message, that.message) && Objects.equals(attachmentDirectory, that.attachmentDirectory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port, mailFrom, mailTo, cc, subject, type, option, message, attachmentDirectory);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MailParameterDTO{");
        sb.append("host='").append(host).append('\'');
        sb.append(", port=").append(port);
        sb.append(", mailFrom='").append(mailFrom).append('\'');
        sb.append(", mailTo='").append(mailTo).append('\'');
        sb.append(", cc='").append(cc).append('\'');
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", option=").append(option);
        sb.append(", message='").append(message).append('\'');
        sb.append(", attachmentDirectory='").append(attachmentDirectory).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
