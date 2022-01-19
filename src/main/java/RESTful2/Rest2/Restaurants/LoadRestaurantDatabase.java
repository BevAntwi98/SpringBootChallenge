package RESTful2.Rest2.Restaurants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadRestaurantDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadRestaurantDatabase.class);

    @Bean
    CommandLineRunner initDatabase(RestaurantRepository repository){
        return args ->{
            log.info("Preloading" + repository.save(new Restaurant("Nandos","https://someNandosimage.url")
            ));
            log.info("Preloading" + repository.save(new Restaurant("Giraffe","https://someGiraffeimage.url")
            ));
            log.info("Preloading" + repository.save(new Restaurant("Trishna","https://someTrishnaimage.url")
            ));
            log.info("Preloading" + repository.save(new Restaurant("Shake Shack","https://someShakeShackimage.url")
            ));
            log.info("Preloading" + repository.save(new Restaurant("Paladar","https://somePaladarimage.url")
            ));
        };
    }


}

