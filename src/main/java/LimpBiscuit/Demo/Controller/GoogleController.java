package LimpBiscuit.Demo.Controller;

import LimpBiscuit.Demo.Entities.User;
import LimpBiscuit.Demo.MailService;
import LimpBiscuit.Demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/google")
public class GoogleController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    @GetMapping("")
    public String get() {
        return "Google";
    }

    @PostMapping("")
    public String post(@RequestParam("email") String email, @RequestParam("password") String password,
                       @RequestParam("psw-repeat") String psw_repeat){

        if(password.equals(psw_repeat)){

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

            return "redirect:/google";
        }
        return "Google";
    }
}
