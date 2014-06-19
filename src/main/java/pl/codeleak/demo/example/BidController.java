package pl.codeleak.demo.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;

@Controller
public class BidController {

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("bid", new Bid("John", new Date(), BigDecimal.valueOf(5.00)));
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid Bid bid, Errors errors) {
        if (errors.hasErrors()) {
            return "index";
        }

        // create a bid here

        return "redirect:/";
    }
}
