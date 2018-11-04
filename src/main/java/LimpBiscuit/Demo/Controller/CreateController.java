package LimpBiscuit.Demo.Controller;

import LimpBiscuit.Demo.Entities.Routine;
import LimpBiscuit.Demo.Repositories.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/create")
public class CreateController {
    @Autowired
    private RoutineRepository routineRepository;

    @GetMapping("")
    public String get() {
        return "Create";
    }

    @PostMapping("")
    public String post(@RequestParam("title") String title, @RequestParam("text") String text,
                       @RequestParam("color") String color, OAuth2Authentication authentication) {
        LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();
        String email = String.valueOf(properties.get("email"));

        Date now = new Date();

        Routine routine = new Routine(email, title, text, color, now);
        routineRepository.save(routine);

        return "redirect:/home";
    }
}
