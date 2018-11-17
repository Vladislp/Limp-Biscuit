package LimpBiscuit.Demo.Controller;

import LimpBiscuit.Demo.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/stats")
public class StatsController {
    @Autowired
    RequestRepository requestRepository;

    @GetMapping("")
    public String get(Model model) {
        model.addAttribute("osStats", stringAndNumberToString(requestRepository.getOperatingSystemStats()));
        model.addAttribute("browserStats", stringAndNumberToString(requestRepository.getBrowserStats()));
        model.addAttribute("dates", stringAndNumberToString(requestRepository.getDates()));

        return "Stats";
    }

    private String stringAndNumberToString(List<RequestRepository.StringAndNumber> stringAndNumbers) {
        StringBuilder stringBuilder = new StringBuilder();

        for (RequestRepository.StringAndNumber stringAndNumber : stringAndNumbers) {
            stringBuilder.append(stringAndNumber.getString());
            stringBuilder.append('/');
            stringBuilder.append(stringAndNumber.getNumber());
            stringBuilder.append(';');
        }

        if (stringBuilder.length() > 0) {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }

        return stringBuilder.toString();
    }
}
