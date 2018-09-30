package LimpBiscuit.Demo.Controller;

import LimpBiscuit.Demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String getLogin() {
        return "Login";
    }

    @PostMapping("/login")
    public String postLogin() {
        System.out.println(userRepository.findAll());

        return "Login";
    }
}
