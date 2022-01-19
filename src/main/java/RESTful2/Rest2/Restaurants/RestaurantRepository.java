package RESTful2.Rest2.Restaurants;

import org.springframework.data.jpa.repository.JpaRepository;

interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}

