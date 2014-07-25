package pl.codeleak.demo.globalerrors;

import org.hibernate.validator.constraints.NotBlank;

@SamePasswords
public class PasswordForm {

    @NotBlank
    private String password;

    @NotBlank
    private String confirmedPassword;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }
}
