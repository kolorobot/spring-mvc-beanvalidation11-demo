package pl.codeleak.demo.validationgroups;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.ValidationException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("validationgroups")
@SessionAttributes("account")
public class AccountController {

    private static final String VIEW_START = "validationgroups/start";
    private static final String VIEW_STEP_ONE = "validationgroups/stepOne";
    private static final String VIEW_STEP_TWO = "validationgroups/stepTwo";
    private static final String VIEW_SUMMARY = "validationgroups/summary";

    @RequestMapping({"", "/", "start"})
    public String start() {
        return VIEW_START;
    }

    @RequestMapping(value = "stepOne")
    public String stepOne(Model model) {
        model.addAttribute("account", new Account());
        return VIEW_STEP_ONE;
    }

    @RequestMapping(value = "stepOne", method = RequestMethod.POST)
    public String stepOne(@Validated(Account.ValidationStepOne.class) Account account, Errors errors) {
        if (errors.hasErrors()) {
            return VIEW_STEP_ONE;
        }
        return "redirect:stepTwo";
    }

    @RequestMapping(value = "stepTwo")
    public String stepTwo() {
        return VIEW_STEP_TWO;
    }

    @RequestMapping(value = "stepTwo", method = RequestMethod.POST)
    public String stepTwo(@Validated(Account.ValidationStepTwo.class) Account account, Errors errors) {
        if (errors.hasErrors()) {
            return VIEW_STEP_TWO;
        }
        return "redirect:summary";
    }

    @RequestMapping(value = "summary")
    public String summary() {
        return VIEW_SUMMARY;
    }

    @RequestMapping(value = "confirm")
    public String confirm(@Validated({Account.ValidationStepOne.class, Account.ValidationStepTwo.class}) Account account, Errors errors, SessionStatus status) {
        status.setComplete();
        if (errors.hasErrors()) {
            throw new ValidationException("Did not pass full validation for the following fields: " +
                    errors.getFieldErrors().stream()
                            .map(fe -> fe.getField())
                            .collect(Collectors.joining(",")));
        }
        return "redirect:start";
    }

}
