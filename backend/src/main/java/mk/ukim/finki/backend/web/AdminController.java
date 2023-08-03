package mk.ukim.finki.backend.web;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.backend.model.Exceptions.InvalidArgumentsException;
import mk.ukim.finki.backend.model.User;
import mk.ukim.finki.backend.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RestaurantService restaurantService;

    public AdminController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public String getAdminPanel(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user != null && user.isAdmin()){
            return "admin-panel";
        }
        return "home";
    }

    @PostMapping
    public String addRestaurant(@RequestParam String name, @RequestParam String suburb, @RequestParam String street,
                                @RequestParam String website, @RequestParam String phone, @RequestParam String lon,
                                @RequestParam String lat, @RequestParam String opens, @RequestParam String closes, Model model){
        try{
            restaurantService.saveRestaurant(name, suburb, street, lat, lon, opens, closes, website, phone);
            return "redirect:/home";
        } catch (InvalidArgumentsException e){
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "admin-panel";
        }
    }
}
