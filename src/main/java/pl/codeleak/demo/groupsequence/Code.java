package pl.codeleak.demo.groupsequence;

import javax.validation.GroupSequence;
import javax.validation.constraints.Size;

@GroupSequence(value = {ValidationOrder.First.class, ValidationOrder.Next.class})
interface ValidationOrder {
    interface First {}
    interface Next {}
}


@ExistingCode(groups = ValidationOrder.Next.class)
public class Code {

    @Size(min = 1, max = 3, groups = ValidationOrder.First.class)
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