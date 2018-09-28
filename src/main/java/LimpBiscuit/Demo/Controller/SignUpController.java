package LimpBiscuit.Demo.Controller;

import LimpBiscuit.Demo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignUpController {

//    @RequestMapping("/signup")
//    public String index(Model model) {
//        model.addAttribute("text", "Hello");
//        return "signup";
//    }

    @RequestMapping(value="/signup", method= RequestMethod.GET)
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

//    @PostMapping("/signup")
//    public String signupSubmit(@ModelAttribute User user) {
//        if(user.getPassword().equals(user.getPassword_repeat())){
//            return "signupsuccess";
//        }
//        return "signup";
//    }
}
