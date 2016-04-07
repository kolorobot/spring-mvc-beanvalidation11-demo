package pl.codeleak.demo.method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@ContextConfiguration(classes = {SomeServiceTest.Config.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class SomeServiceTest {

    @Configuration
    public static class Config {
        @Bean
        public MethodValidationPostProcessor methodValidationPostProcessor() {
            return new MethodValidationPostProcessor();
        }

        @Bean
        public SomeService userCreateService() {
            return new SomeService();
        }
    }

    @Autowired
    private SomeService service;

    @Test
    public void throwsViolationExceptionWhenAllArgumentsInvalid() {
        assertThatExceptionOfType(ConstraintViolationException.class)
            .isThrownBy(() -> service.createUser(null, null, null))
            .matches(e -> e.getConstraintViolations().size() == 3);
    }

    @Test
    public void throwsViolationExceptionWhen2ArgumentsInvalid() {
        assertThatExceptionOfType(ConstraintViolationException.class)
            .isThrownBy(() -> service.createUser(null, null, "valid"))
            .matches(e -> e.getConstraintViolations().size() == 2);

    }


    @Test
    public void throwsViolationExceptionWhenEmailInvalidArgumentsInvalid() {
        assertThatExceptionOfType(ConstraintViolationException.class)
            .isThrownBy(() -> service.createUser("invalid_email", "valid", "valid"))
            .matches(e -> e.getConstraintViolations().size() == 1)
            .matches(e -> e.getConstraintViolations().stream()
                           .allMatch(v -> v.getMessage().equals("not a well-formed email address")));

    }

    @Test
    public void throwsViolationExceptionWhenReturnValueTooLong() {
        assertThatExceptionOfType(ConstraintViolationException.class)
            .isThrownBy(() -> service.createUser("user@domain.com", "too_long_username", "valid"))
            .matches(e -> e.getConstraintViolations().size() == 1)
            .matches(e -> e.getConstraintViolations().stream()
                           .allMatch(v -> v.getMessage().equals("length must be between 3 and 5")));

    }

    @Test
    public void createsUser() {
        service.createUser("user@domain.com", "valid", "valid");
    }
}
