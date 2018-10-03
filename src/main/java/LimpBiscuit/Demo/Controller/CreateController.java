package LimpBiscuit.Demo.Controller;

import LimpBiscuit.Demo.Entities.Routine;
import LimpBiscuit.Demo.Repositories.RoutineRepository;
import LimpBiscuit.Demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/create")
public class CreateController {

    @Autowired
    private RoutineRepository routineRepository;

    @GetMapping("")
    public String get() {
        return "Login";
    }

    @PostMapping("")
    public String post(@RequestParam("title") String title, @RequestParam("text") String text) {

//        Date date = new Date();

        Routine routine = new Routine(title, text);
        routineRepository.save(routine);

        return "Create";
    }
}
