package LimpBiscuit.Demo.Controller;

import LimpBiscuit.Demo.Entities.Routine;
import LimpBiscuit.Demo.Repositories.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private RoutineRepository routineRepository;

    @GetMapping(value = {"/", "/index"})
    public String index() {

        return "Index";
    }



    @GetMapping("/about")
    public String about() {

        return "About";
    }

    @GetMapping("/contact")
    public String contact() {

        return "Contact";
    }

    @GetMapping("/line")
    public String get(){
        return "Line";
    }

    @GetMapping("/logoutt")
    public String logout(HttpServletRequest request){
        request.getSession(true).invalidate();

        return "Index";
    }
}
