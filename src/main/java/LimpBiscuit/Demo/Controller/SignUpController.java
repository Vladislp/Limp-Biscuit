package LimpBiscuit.Demo.Controller;

import LimpBiscuit.Demo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignUpController {

//    @RequestMapping(value="/signup", method= RequestMethod.GET)
//    public ModelAndView getSignupForm() {
//        ModelAndView model = new ModelAndView("Signup");
//
//        return model;
//    }
//
//    @RequestMapping(value="/SignupSubmit", method= RequestMethod.POST)
//    public ModelAndView submitSignupForm(@RequestParam("email") String email, @RequestParam("psw") String password,
//            @RequestParam("psw-repeat") String password_repeat) {
//
//        ModelAndView model = new ModelAndView("SignupSuccess");
//        model.addObject("msg", ""+email + " "+password);
//
//        return model;
//    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(@ModelAttribute User user) {
        if(user.getPassword().equals(user.getPassword_repeat())){
            return "signupsuccess";
        }
        return "signup";
    }
}
