package LimpBiscuit.Demo.Controller;

import LimpBiscuit.Demo.Entities.Routine;
import LimpBiscuit.Demo.Repositories.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private RoutineRepository routineRepository;

    @GetMapping(value = {"/","/index"})
    public String index() {

        return "Index";
    }

    @GetMapping("/home")
    public ModelAndView home(Principal user) {
        List<Routine> routines = routineRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("Home");
//        modelAndView.addObject("routine", "Baeldung");
//        for (Routine r: routines){
//            modelAndView.addObject("routine", r);
//        }
        modelAndView.addObject("routines", routines);
        modelAndView.addObject("user", user);
        System.out.println(user.toString());
        return modelAndView;
    }

    @GetMapping("/about")
    public String about() {

        return "About";
    }

    @GetMapping("/contact")
    public String contact() {

        return "Contact";
    }
}
