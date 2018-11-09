package LimpBiscuit.Demo.Controller;

import LimpBiscuit.Demo.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stats")
public class StatsController {
    @Autowired
    RequestRepository requestRepository;

    @GetMapping
    public String get(Model model) {
        model.addAttribute("osStats", requestRepository.getOperatingSystemStats());

        return "Stats";
    }
}
