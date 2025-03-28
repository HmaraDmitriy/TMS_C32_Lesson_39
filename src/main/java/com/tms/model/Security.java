package com.tms.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Scope("prototype")
@Component
public class Security {
    private Long id;
    private String login;
    private String password;
    private Role role;
    private Timestamp created;
    private Timestamp updated;
    private Long userId;

    public Security() {
    }

    public Security(Long id, String login, String password, Role role, Timestamp created, Timestamp updated, Long userId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.created = created;
        this.updated = updated;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Security{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", created=" + created +
                ", updated=" + updated +
                ", userId=" + userId +
                '}';
    }
}
