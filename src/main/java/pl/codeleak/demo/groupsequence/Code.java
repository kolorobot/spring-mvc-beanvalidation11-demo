package pl.codeleak.demo.groupsequence;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.GroupSequence;
import javax.validation.constraints.Size;

@GroupSequence(value = {ValidationOrder.Field.class, ValidationOrder.Class.class})
interface ValidationOrder {
    interface Field {}
    interface Class {}
}


@ExistingCode(groups = ValidationOrder.Class.class)
public class Code {

    @NotBlank(groups = ValidationOrder.Field.class)
    @Size(min = 1, max = 3, groups = ValidationOrder.Field.class)
    private String code;

    private transient String token;

    protected Code() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    void withToken(String token) {
        this.token = token;
    }

    String getToken() {
        return token;
    }
}