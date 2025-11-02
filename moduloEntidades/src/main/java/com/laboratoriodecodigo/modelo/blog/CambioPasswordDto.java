package com.laboratoriodecodigo.modelo.blog;


public class CambioPasswordDto {
    private String currentPassword;
    private String newPassword;
    // Deben tener getters y setters

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}