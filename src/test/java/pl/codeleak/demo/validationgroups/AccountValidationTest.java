package pl.codeleak.demo.validationgroups;

import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountValidationTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void has4ViolationsWhileValidatingBothGroups() {
        Account account = new Account();
        Set<ConstraintViolation<Account>> constraintViolations = validator.validate(
            account, Account.ValidationStepOne.class, Account.ValidationStepTwo.class
        );
        assertThat(constraintViolations).hasSize(4);
    }

    @Test
    public void has2ViolationsWhileInStepOne() {
        Account account = new Account();
        Set<ConstraintViolation<Account>> constraintViolations = validator.validate(
            account, Account.ValidationStepOne.class
        );
        assertThat(constraintViolations).hasSize(2);

    }
}