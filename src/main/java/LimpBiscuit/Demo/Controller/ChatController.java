package LimpBiscuit.Demo.Controller;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;

@Controller
public class ChatController {
    @RequestMapping("/chat")
    public String index(HttpServletRequest request, Model model, OAuth2Authentication authentication) {
        LinkedHashMap<String, Object> properties = (LinkedHashMap<String, Object>) authentication.getUserAuthentication().getDetails();
        String email = String.valueOf(properties.get("email"));
        String username = email.split("@")[0];

        model.addAttribute("username", username);

        return "Chat";
    }
}