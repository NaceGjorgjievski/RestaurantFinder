package mk.ukim.finki.backend.web;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.backend.model.Restaurant;
import mk.ukim.finki.backend.service.RestaurantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = { "/", "/home" })
public class HomeController {

    private final RestaurantService restaurantService;

    public HomeController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public String getHomePage() {
        return "home";
    }

    @PostMapping("/restaurants")
    public String getRestaurants(HttpServletRequest request, Model model) {
        List<Restaurant> restaurants;
        if (request.getParameter("ime").equals("") && request.getParameter("naselba").equals("")) {
            restaurants = restaurantService.findAll();
        } else if (request.getParameter("ime").equals("") && !request.getParameter("naselba").equals("")) {
            restaurants = restaurantService.findBySuburb(request.getParameter("naselba"));
        } else if (!request.getParameter("ime").equals("") && request.getParameter("naselba").equals("")) {
            restaurants = restaurantService.findByName(request.getParameter("ime"));
        } else {
            restaurants = restaurantService.findAllByNameAndSuburb(request.getParameter("ime"),
                    request.getParameter("naselba"));
        }
        model.addAttribute("restaurants", restaurants);
        return "home";
    }

    @GetMapping("/nearest")
    public String getNearest(HttpServletRequest request, Model model) {
        List<Restaurant> restaurants = restaurantService.findAll();
        double lon = Double.parseDouble(request.getParameter("lon"));
        double lat = Double.parseDouble(request.getParameter("lat"));
        List<Double> distances = new ArrayList<>();
        for (int i = 0; i < restaurants.size(); i++) {
            distances.add(Math.sqrt(
                    Math.pow(lon - restaurants.get(i).getLon(), 2) + Math.pow(lat - restaurants.get(i).getLat(), 2)));
        }
        List<Restaurant> nearestRestourants = new ArrayList<>();
        List<Double> sortedDistances = distances.stream().sorted().collect(Collectors.toList());
        sortedDistances = sortedDistances.stream().limit(5).collect(Collectors.toList());
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < distances.size(); j++) {
                if (sortedDistances.get(i) == distances.get(j)) {
                    nearestRestourants.add(restaurants.get(j));
                }
            }
        }
        model.addAttribute("restaurants", nearestRestourants);
        System.out.println(distances);
        return "home";
    }
}
