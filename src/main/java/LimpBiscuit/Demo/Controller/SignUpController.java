package LimpBiscuit.Demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {

    @RequestMapping("/signup")
    public String index(Model model) {
        model.addAttribute("text", "Hello");
        return "SignUp";
    }

    @PostMapping("/signup")
    public String hello(ModelMap map, @RequestParam String userInput) {
        /*your code goes here*/
        map.put("userInput", userInput);
        return "jsp1";   //first jsp name
    }
}
