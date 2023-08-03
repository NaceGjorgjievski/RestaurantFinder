package mk.ukim.finki.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String suburb;

    private String street;

    private Double lat;

    private Double lon;

    private String opens;

    private String closes;

    private String website;

    private String phone;

    public Restaurant(String name, String suburb, String street, Double lat, Double lon, String opens, String closes, String website, String phone) {
        this.name = name;
        this.suburb = suburb;
        this.street = street;
        this.lat = lat;
        this.lon = lon;
        this.opens = opens;
        this.closes = closes;
        this.website = website;
        this.phone = phone;
    }

    public Restaurant() {
    }
}
