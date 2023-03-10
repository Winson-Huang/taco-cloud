package tacos.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.DiscountCodeProps;

@Controller
@RequestMapping("/discount")
public class DiscountController {
    
    private DiscountCodeProps discountProps;

    public DiscountController(DiscountCodeProps discountProps) {
        this.discountProps = discountProps;
    }

    @GetMapping
    public String displayDiscountCode(Model model) {
        model.addAttribute("codes", discountProps.getCodes());
        return "discountList";
    }
}
