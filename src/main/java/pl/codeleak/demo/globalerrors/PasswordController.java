package pl.codeleak.demo.globalerrors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("globalerrors")
public class PasswordController {

    @RequestMapping(value = "password")
    public String password(Model model) {
        model.addAttribute(new PasswordForm());
        return "globalerrors/password";
    }

    @RequestMapping(value = "password", method = RequestMethod.POST)
    public String stepTwo(@Valid PasswordForm passwordForm, Errors errors) {
        if (errors.hasErrors()) {
            return "globalerrors/password";
        }
        return "redirect:password";
    }
}
