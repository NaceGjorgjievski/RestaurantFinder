package mk.ukim.finki.backend.bootsrap;
import jakarta.annotation.PostConstruct;
import mk.ukim.finki.backend.model.Restaurant;
import mk.ukim.finki.backend.repository.RestaurantRepository;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class DataHolder {
    private final RestaurantRepository restaurantRepository;

    public DataHolder(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @PostConstruct
    public void fillDatabaseOnlyOnceLocally() throws IOException {
        if (restaurantRepository.count() == 0) {
            File file = new File("database/RestourantsInSkopje.csv");
            if (file.exists()) {
                BufferedReader bf = new BufferedReader(new FileReader(file));
                String line = bf.readLine();
                while ((line = bf.readLine()) != null) {
                    String[] parts = line.split(",");
                    String name = parts[0];
                    String suburb = parts[1];
                    String street = parts[2];
                    Double lon = Double.parseDouble(parts[3]);
                    Double lat = Double.parseDouble(parts[4]);
                    String opens = parts[5];
                    String closes = parts[6];
                    String website = parts[7];
                    String phone = parts[8];
                    Restaurant r = new Restaurant(name, suburb, street, lat, lon, opens, closes, website, phone);
                    restaurantRepository.save(r);
                }
            }
        }
    }
}