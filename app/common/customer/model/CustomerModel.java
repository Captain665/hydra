package common.customer.model;

import common.base.BaseModel;
import common.enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "customers")
public class CustomerModel extends BaseModel {
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "gender")
    private Gender gender;
    @Column(name = "password")
    private String password;

    @Column(name = "new_user")
    private Boolean isNewUser;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getNewUser() {
        return isNewUser;
    }

    public void setNewUser(Boolean newUser) {
        isNewUser = newUser;
    }

    public CustomerModel(String fullName, String mobile, String emailId, Boolean active, Gender gender, String password, Boolean isNewUser) {
        this.fullName = fullName;
        this.mobile = mobile;
        this.emailId = emailId;
        this.active = active;
        this.gender = gender;
        this.password = password;
        this.isNewUser = isNewUser;
    }

    public CustomerModel() {
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "fullName='" + fullName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", emailId='" + emailId + '\'' +
                ", active=" + active +
                ", gender=" + gender +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", createdAt=" + createdAt +
                ", createdBy=" + createdBy +
                ", updatedAt=" + updatedAt +
                ", updatedBy=" + updatedBy +
                '}';
    }
}
