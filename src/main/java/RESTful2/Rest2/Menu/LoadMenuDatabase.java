package RESTful2.Rest2.Menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadMenuDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadMenuDatabase.class);

    @Bean
    CommandLineRunner initMenuDatabase(MenuRepository repository){
        return args ->{
            log.info("Preloading" + repository.save(new Menu("Breakfast")
            ));
            log.info("Preloading" + repository.save(new Menu("Lunch Menu")
            ));
            log.info("Preloading" + repository.save(new Menu("Dinner Menu")
            ));

        };
    }


}

