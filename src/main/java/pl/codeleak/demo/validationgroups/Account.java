package pl.codeleak.demo.validationgroups;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@SamePasswords(groups = {Account.ValidationStepTwo.class})
public class Account implements PasswordAware {

    interface ValidationStepOne {
        // validation group marker interface
    }

    interface ValidationStepTwo {
        // validation group marker interface
    }

    @NotBlank(groups = {ValidationStepOne.class})
    private String username;

    @Email(groups = {ValidationStepOne.class})
    @NotBlank(groups = {ValidationStepOne.class})
    private String email;

    @NotBlank(groups = {ValidationStepTwo.class})
    @StrongPassword(groups = {ValidationStepTwo.class})
    private String password;

    @NotBlank(groups = {ValidationStepTwo.class})
    private String confirmedPassword;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
