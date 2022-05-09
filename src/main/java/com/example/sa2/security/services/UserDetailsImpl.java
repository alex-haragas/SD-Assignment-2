package com.example.sa2.security.services;

import com.example.sa2.model.Admin;
import com.example.sa2.model.Client;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private Admin admin;
    private Client client;

    public UserDetailsImpl(Admin user) {
        this.admin = user;
        this.client=null;
    }

    public UserDetailsImpl(Client user) {
        this.admin = null;
        this.client=user;
    }

    public Long getId(){
        if(admin!=null){
            return (long) admin.getId();
        }
        else{
            return (long) client.getId();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(admin!=null) {
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else {
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_CLIENT"));

        }
    }

    public String getRole(){
        if(admin!=null){
            return "ADMIN";
        }
        else {
            return "CLIENT";
        }
    }
    @Override
    public String getPassword() {
        if(admin!=null) {
            return admin.getPassword();
        }
        else {
            return client.getPassword();
        }
    }

    @Override
    public String getUsername() {
        if(admin!=null) {
            return admin.getUsername();
        }
        else {
            return client.getUsername();
        }    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) obj;

        if(admin!=null) {
            return this.admin.getId() == user.getId();
        }
        else{
            return this.client.getId() == user.getId();
        }
    }
}