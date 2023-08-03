package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAllByNameContaining(String name);
    List<Restaurant> findAllBySuburbContaining(String suburb);
    List<Restaurant> findAllByStreet(String street);
    List<Restaurant> findAllByNameContainingAndSuburbContaining(String name,String suburb);
}
