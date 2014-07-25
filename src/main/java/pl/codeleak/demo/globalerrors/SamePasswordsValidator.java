package pl.codeleak.demo.globalerrors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SamePasswordsValidator implements ConstraintValidator<SamePasswords, PasswordForm> {

	@Override
	public void initialize(SamePasswords constraintAnnotation) {}

	@Override
	public boolean isValid(PasswordForm value, ConstraintValidatorContext context) {
		if(value.getConfirmedPassword() == null) {
			return true;
		}
		return value.getConfirmedPassword().equals(value.getPassword());
	}
}
