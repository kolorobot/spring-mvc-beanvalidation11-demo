package pl.codeleak.demo.bettermessages;

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
@RequestMapping("bettermessages")
public class BidController {

    @RequestMapping({"", "/", "bid"})
    public String index(Model model) {
        model.addAttribute("bid", new Bid("John", new Date(), BigDecimal.valueOf(5.00)));
        return "bettermessages/bid";
    }

    @RequestMapping(value = "bid", method = RequestMethod.POST)
    public String create(@ModelAttribute @Valid Bid bid, Errors errors) {
        if (errors.hasErrors()) {
            return "bettermessages/bid";
        }

        // create a bid here

        return "redirect:bettermessages";
    }
}
