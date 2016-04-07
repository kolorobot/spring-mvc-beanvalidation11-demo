package pl.codeleak.demo.manual;

import javax.validation.GroupSequence;

@GroupSequence(value = {ValidationOrder.First.class, ValidationOrder.Second.class})
interface ValidationOrder {
    interface First {}
    interface Second {}
}


public class User {

    @UserExists(groups = ValidationOrder.First.class)
    @UserIsEntitledToDiscount(groups = ValidationOrder.Second.class)
    private String username;

    protected User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}