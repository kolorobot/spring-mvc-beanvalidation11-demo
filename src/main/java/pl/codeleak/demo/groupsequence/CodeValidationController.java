package pl.codeleak.demo.groupsequence;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.codeleak.demo.support.MessageHelper;

@Controller
@RequestMapping("groupsequence")
public class CodeValidationController {

    @RequestMapping({"", "/", "code"})
    public String form(Model model) {
        model.addAttribute("code", new Code());
        return "groupsequence/code";
    }

    @RequestMapping(value = "code", method = RequestMethod.POST)
    public String validate(@ModelAttribute @Validated(ValidationOrder.class) Code code, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return "groupsequence/code";
        }
        MessageHelper.addSuccessAttribute(ra, "Token retrieved: " + code.getToken());
        return "redirect:/groupsequence";
    }
}
