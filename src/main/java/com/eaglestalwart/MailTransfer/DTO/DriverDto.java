package com.eaglestalwart.MailTransfer.DTO;

public class DriverDto {
    private Long id;
    private String firstName;
    private String email;
    private String role;
    private String authToken;

    public DriverDto() {
    }

    public DriverDto(Long id, String firstName, String email, String role, String authToken) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.role = role;
        this.authToken = authToken;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
