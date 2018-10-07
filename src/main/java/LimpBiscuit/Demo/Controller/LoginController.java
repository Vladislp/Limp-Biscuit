package LimpBiscuit.Demo.Controller;

import LimpBiscuit.Demo.Entities.User;
import LimpBiscuit.Demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String get() {

        return "Login";
    }

    @PostMapping("")
    public String post(@RequestParam("email") String email, @RequestParam("password") String password) {

        User user = userRepository.findByEmail(email);
        try {
            user.getEmail();
        } catch (Exception e) {
            return "Login";
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hash = passwordEncoder.encode(password);

        System.out.println(hash);
        System.out.println(user.getHash());

        if (passwordEncoder.matches(password, user.getHash())) {
            return "Home";
        }
        return "Login";
    }
}
