package LimpBiscuit.Demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @GetMapping(value = {"/", "/index"})
    public String index() {

        return "Index";
    }

    @GetMapping("/about")
    public String about() {

        return "About";
    }

    @GetMapping("/contact")
    public String contact() {

        return "Contact";
    }

    @GetMapping("/line")
    public String get() {
        return "Line";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession(true).invalidate();
        return "redirect:/";
    }
}
