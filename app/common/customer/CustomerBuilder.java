package common.customer;

import common.customer.model.CustomerModel;
import common.enums.Gender;

public class CustomerBuilder {

    public String fullName;
    public String emailId;
    public Gender gender;
    public String mobile;
    public boolean active;
    public String password;
    public Boolean newUser;


    public CustomerBuilder setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public CustomerBuilder setEmailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public CustomerBuilder setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public CustomerBuilder setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public CustomerBuilder setActive(boolean active) {
        this.active = active;
        return this;
    }

    public CustomerBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public CustomerBuilder(){

    }
    public CustomerModel build(){
        return new CustomerModel(
                this.fullName,
                this.mobile,
                this.emailId,
                this.active,
                this.gender,
                this.password,
                this.newUser
        );
    }



}
