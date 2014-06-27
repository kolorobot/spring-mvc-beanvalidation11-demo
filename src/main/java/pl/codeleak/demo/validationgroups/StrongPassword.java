package pl.codeleak.demo.validationgroups;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.*;

import javax.validation.*;
import javax.validation.constraints.*;

@Size(min = 4, max = 10) // constraint composition
// @Pattern(regexp = ".*(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).*$")
@Target({FIELD, METHOD, ANNOTATION_TYPE}) // 
@Retention(RUNTIME)
@Constraint(validatedBy = {}) // composite constraint with no validator
@Documented
public @interface StrongPassword {
	  String message() default ""; // required
	  Class<?>[] groups() default {}; // required
	  Class<? extends Payload>[] payload() default {}; // required
}
