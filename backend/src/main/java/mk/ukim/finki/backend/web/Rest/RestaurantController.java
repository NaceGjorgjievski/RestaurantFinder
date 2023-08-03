package mk.ukim.finki.backend.web.Rest;

import mk.ukim.finki.backend.model.Restaurant;
import mk.ukim.finki.backend.service.RestaurantService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/RestaurantsInSkopje")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> findAll(){
        return restaurantService.findAll();
    }
}
