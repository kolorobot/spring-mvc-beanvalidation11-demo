package pl.codeleak.demo.manual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("manual")
public class ManualValidationController {

    @Autowired
    private Validator validator;

    @RequestMapping(value = "/user")
    public String form(Model model) {
        model.addAttribute("user", new User());
        return "manual/user";
    }

@RequestMapping(value = "/user", method = RequestMethod.POST)
public String validate(@ModelAttribute User user, Errors errors) {

    ValidationUtils.invokeValidator(validator, user, errors, ValidationOrder.class);

    if (errors.hasErrors()) {
        return "manual/user";
    }
    // success
    return "manual/user";
}
}
