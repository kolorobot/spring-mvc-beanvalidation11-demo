package pl.codeleak.demo.groupsequence;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE}) // class level constraint
@Retention(RUNTIME)
@Constraint(validatedBy = ExistingCodeValidator.class) // validator
@Documented
public @interface ExistingCode {
    String message() default "invalid code"; // default error message

    java.lang.Class<?>[] groups() default {}; // required

    java.lang.Class<? extends Payload>[] payload() default {}; // required
}

class ExistingCodeValidator implements ConstraintValidator<ExistingCode, Code> {

    private TokenRetrievalService tokenRetrievalService;

    @Autowired
    public ExistingCodeValidator(TokenRetrievalService tokenRetrievalService) {
        this.tokenRetrievalService = tokenRetrievalService;
    }

    @Override
    public void initialize(ExistingCode constraintAnnotation) {

    }

    @Override
    public boolean isValid(Code value, ConstraintValidatorContext context) {
        try {
            value.withToken(tokenRetrievalService.getToken(value.getCode()));
        } catch (TokenNotFoundException e) {
            if (context != null) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Code is invalid!")
                       .addPropertyNode("code") // to be tested otherwise refactoring may break the validation
                       .addConstraintViolation();
            }
            return false;
        }
        return true;
    }
}