package com.example.sa2.model.auth;

public class JwtClientResponse {
    private String jwt;
    private Long Id;
    private String username;
    private String role;

    public JwtClientResponse(String jwt, Long id, String username,String role) {
        this.jwt = jwt;
        Id = id;
        this.username = username;
        this.role=role;
    }
    @Override
    public String toString() {
        return "JwtClientResponse{" +
                "jwt='" + jwt + '\n' +
                ", Id=" + Id +
                ", username='" + username +"' role='" + role + '\'' +
                '}';
    }



    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}