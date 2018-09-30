package LimpBiscuit.Demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "Index";
    }

    @GetMapping("/home")
    public String home() {
        return "Home";
    }

    @GetMapping("/about")
    public String about() {
        return "About";
    }

    @GetMapping("/contact")
    public String contact() {
        return "Contact";
    }
}
