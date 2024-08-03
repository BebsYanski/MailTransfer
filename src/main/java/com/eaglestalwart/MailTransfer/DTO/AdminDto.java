package com.eaglestalwart.MailTransfer.DTO;

import lombok.Data;

@Data
public class AdminDto {
    private Long adminId;
    private String firstName;
    private String email;
    private String role;
    private String authToken;

    public AdminDto(Long adminId, String firstName, String email, String role, String authToken) {
        this.adminId = adminId;
        this.firstName = firstName;
        this.email = email;
        this.role = role;
        this.authToken = authToken;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String token) {
        this.authToken = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
