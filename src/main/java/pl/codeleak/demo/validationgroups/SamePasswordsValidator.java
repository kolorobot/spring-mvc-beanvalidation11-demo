package pl.codeleak.demo.validationgroups;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SamePasswordsValidator implements ConstraintValidator<SamePasswords, PasswordAware> {

	@Override
	public void initialize(SamePasswords constraintAnnotation) {}

	@Override
	public boolean isValid(PasswordAware value, ConstraintValidatorContext context) {
		if(value.getConfirmedPassword() == null) {
			return true;
		}
		return value.getConfirmedPassword().equals(value.getPassword());
	}
}
