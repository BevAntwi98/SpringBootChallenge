package RESTful2.Rest2.Restaurants;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class RestaurantController {
    private final RestaurantRepository repository;
    private final RestaurantModelAssembler assembler;


    RestaurantController(RestaurantRepository repository, RestaurantModelAssembler assembler){
        this.repository=repository;
        this.assembler=assembler;
    }

    //    read
    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    CollectionModel<EntityModel<Restaurant>> all(){
//another SpringHATEOAS container is aimed at encapsulating collections of resources
//-->encapsulates collections of restaurant resources
//------> Java 8 streams returns a list of Restaurant Objects

        List<EntityModel<Restaurant>> restaurants = repository.findAll().stream()
                        .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(restaurants, linkTo(methodOn(RestaurantController.class).all()).withSelfRel());
    }

    //    read single item
    @RequestMapping(value ="/restaurants/{id}", method = RequestMethod.GET)
    EntityModel<Restaurant> one(@PathVariable int id){

        Restaurant restaurant = repository.findById(id)
                .orElseThrow(()-> new RestaurantNotFoundException(id));

        return assembler.toModel(restaurant);
    }

    //    create
    @RequestMapping(value ="/restaurants", method = RequestMethod.POST)
    Restaurant newRestaurant(@RequestBody Restaurant newRestaurant){
        return repository.save(newRestaurant);
    }

    //    update item
    @RequestMapping(value ="/restaurants/{id}", method = RequestMethod.PUT)
    Restaurant replaceRestaurant(@RequestBody Restaurant newRestaurant, @PathVariable int id){
        return repository.findById(id)
                .map(restaurant -> {
                    restaurant.setName(newRestaurant.getName());
                    restaurant.setImage(newRestaurant.getImage());
                    return repository.save(restaurant);
                })
                .orElseGet(()->{
                    newRestaurant.setId(id);
                    return repository.save(newRestaurant);
                });
    }

    //    delete
    @RequestMapping(value ="/restaurants/{id}", method = RequestMethod.DELETE)
    void deleteRestaurant(@PathVariable int id){
        repository.deleteById(id);
    }
}

