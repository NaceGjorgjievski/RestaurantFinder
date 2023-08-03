package mk.ukim.finki.backend.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public String logout(HttpServletRequest req){
        req.getSession().setAttribute("user", null);
        req.getSession().setAttribute("email", null);
        return "redirect:/home";
    }
}
