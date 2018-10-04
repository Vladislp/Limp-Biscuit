package LimpBiscuit.Demo.Controller;

import LimpBiscuit.Demo.Entities.Routine;
import LimpBiscuit.Demo.Repositories.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private RoutineRepository routineRepository;

    @GetMapping("/")
    public String index() {
        return "Index";
    }

    @GetMapping("/home")
    public ModelAndView home() {
        List<Routine> routines = routineRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("Home");
//        modelAndView.addObject("routine", "Baeldung");
//        for (Routine r: routines){
//            modelAndView.addObject("routine", r);
//        }
        modelAndView.addObject("routines", routines);

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
