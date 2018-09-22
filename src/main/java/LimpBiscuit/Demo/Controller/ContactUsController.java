package LimpBiscuit.Demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactUsController {

    @RequestMapping("/contact")
    public String index(Model model) {
        model.addAttribute("text", "Hello");
        return "Contact";
    }
}
