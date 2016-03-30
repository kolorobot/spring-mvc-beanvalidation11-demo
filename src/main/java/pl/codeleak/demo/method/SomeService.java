package pl.codeleak.demo.method;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class SomeService {

    @Length(min = 3, max = 5)
    public String createUser(@NotBlank @Email String email,
                             @NotBlank String username,
                             @NotBlank String password) {
        return username;
    }

}
