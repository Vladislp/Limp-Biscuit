package LimpBiscuit.Demo.Controller;

import LimpBiscuit.Demo.Entities.User;
import LimpBiscuit.Demo.Repositories.UserRepository;
import LimpBiscuit.Demo.Services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/signup")
public class SignUpController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailService mailService;

    @GetMapping("")
    public String get() {
        return "SignUp";
    }

    @PostMapping("")
    public String post(@RequestParam("email") String email, @RequestParam("password") String password,
                       @RequestParam("psw-repeat") String psw_repeat) {

        if (password.equals(psw_repeat)) {

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hash = passwordEncoder.encode(password);

            User user = new User(email, hash);
            userRepository.save(user);

            String message = "Thanks for registration";

            try {
                this.mailService.sendEmail(user.getEmail(), message);
            } catch (Exception e) {
                System.out.println("something wrong with sending email");
            }

            return "redirect:/home";
        }
        return "SignUp";
    }
}
