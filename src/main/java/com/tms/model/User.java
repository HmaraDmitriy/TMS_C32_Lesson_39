package com.tms.model;


import com.tms.annotation.CustomAge;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Scope("prototype")
@Component
public class User {
    private Long id;
    @NotNull(message = "Firstname cannot be null.")
    @Size(min = 2, max = 20, message = "Firstname must be between 2 and 20 characters.")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Firstname must contain only letters.")
    private String firstname;
    @NotNull(message = "Second name cannot be null.")
    @Size(min = 2, max = 20, message = "Second name must be between 2 and 20 characters.")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+$", message = "Second name must contain only letters.")
    private String secondName;
    @CustomAge
    private Integer age;
    @NotNull(message = "Email cannot be null.")
    @Email(message = "Invalid email format.")
    private String email;
    private String sex;
    @Pattern(regexp = "[0-9]{12}", message = "Telephone number must be exactly 12 digits.")
    private String telephoneNumber;
    private Timestamp created;
    private Timestamp updated;
    private Boolean isDeleted;
    private Security securityInfo;

    public User() {}

    public User(Long id, String firstname, String secondName, Integer age, String email, String sex, String telephoneNumber, Timestamp created, Timestamp updated, Boolean isDeleted, Security securityInfo) {
        this.id = id;
        this.firstname = firstname;
        this.secondName = secondName;
        this.age = age;
        this.email = email;
        this.sex = sex;
        this.telephoneNumber = telephoneNumber;
        this.created = created;
        this.updated = updated;
        this.isDeleted = isDeleted;
        this.securityInfo = securityInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Security getSecurityInfo() {
        return securityInfo;
    }

    public void setSecurityInfo(Security securityInfo) {
        this.securityInfo = securityInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", isDeleted=" + isDeleted +
                ", securityInfo=" + securityInfo +
                '}';
    }
}