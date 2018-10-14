package LimpBiscuit.Demo.Controller;

import LimpBiscuit.Demo.Entities.Routine;
import LimpBiscuit.Demo.Repositories.RoutineRepository;
import LimpBiscuit.Demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class RoutineController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoutineRepository routineRepository;

    @GetMapping("/home")
    public ModelAndView home(Principal user) {
        List<Routine> routines = routineRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("Home");

        modelAndView.addObject("routines", routines);

        System.out.println(userRepository.findUsersNotDoneRoutines());

        return modelAndView;


    }



}
