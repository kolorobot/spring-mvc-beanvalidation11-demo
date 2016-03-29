package pl.codeleak.demo.globalerrors;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE}) // class level constraint
@Retention(RUNTIME)
@Constraint(validatedBy = SamePasswordsValidator.class) // validator
@Documented
public @interface SamePasswords {

    String message() default "passwords do not match"; // default error message

	Class<?>[] groups() default {}; // required

	Class<? extends Payload>[] payload() default {}; // required
}
