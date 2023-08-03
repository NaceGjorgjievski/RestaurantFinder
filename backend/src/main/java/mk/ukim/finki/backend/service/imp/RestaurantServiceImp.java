package mk.ukim.finki.backend.service.imp;

import mk.ukim.finki.backend.model.Exceptions.InvalidArgumentsException;
import mk.ukim.finki.backend.model.Restaurant;
import mk.ukim.finki.backend.repository.RestaurantRepository;
import mk.ukim.finki.backend.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImp implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImp(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> findByName(String name) {
        return restaurantRepository.findAllByNameContaining(name);
    }

    @Override
    public List<Restaurant> findBySuburb(String suburb) {
        return restaurantRepository.findAllBySuburbContaining(suburb);
    }

    @Override
    public List<Restaurant> findByStreet(String street) {
        return restaurantRepository.findAllByStreet(street);
    }

    @Override
    public List<Restaurant> findAllByNameAndSuburb(String name, String suburb) {
        return restaurantRepository.findAllByNameContainingAndSuburbContaining(name,suburb);
    }

    @Override
    public Restaurant saveRestaurant(String name, String suburb, String street, String lat, String lon, String opens,
                                     String closes, String website, String phone) {
        for(int i=0;i<lon.length();i++){
            if(Character.isLetter(lon.charAt(i))) {
                throw new InvalidArgumentsException("lat and lon must only contain digits");
            }
        }
        for(int i=0;i<lat.length();i++){
            if(Character.isLetter(lat.charAt(i))) {
                throw new InvalidArgumentsException("lat and lon must only contain digits");
            }
        }
        if(!opens.contains(":") || !closes.contains(":")){
            throw new InvalidArgumentsException("opens and closes must contain character ':', example 08:00");
        }
        for(int i=0;i<opens.length();i++){
            if(Character.isLetter(opens.charAt(i))) {
                throw new InvalidArgumentsException("opens and closes must only contain digits and ':'");
            }
        }
        for(int i=0;i<closes.length();i++){
            if(Character.isLetter(closes.charAt(i))) {
                throw new InvalidArgumentsException("opens and closes must only contain digits and ':'");
            }
        }
        for(int i=0;i<phone.length();i++){
            if(Character.isLetter(phone.charAt(i))) {
                throw new InvalidArgumentsException("phone must only contain digits");
            }
        }
        if(!website.contains("http://") && !website.contains("https://")){
            throw new InvalidArgumentsException("website must contain http:// or https://");
        }
        Restaurant restaurant = new Restaurant(name, suburb, street, Double.valueOf(lat), Double.valueOf(lon), opens,
                closes, website, phone);
        return restaurantRepository.save(restaurant);
    }
}
