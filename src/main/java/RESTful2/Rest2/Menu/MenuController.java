package RESTful2.Rest2.Menu;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class MenuController {
    private final MenuRepository repository;
    private final MenuModelAssembler assembler;


    MenuController(MenuRepository repository, MenuModelAssembler assembler){
        this.repository=repository;
        this.assembler=assembler;
    }

    //    read
    @GetMapping("/menu")
    CollectionModel<EntityModel<Menu>> all(){
//another SpringHATEOAS container is aimed at encapsulating collections of resources
//-->encapsulates collections of restaurant resources
//------> Java 8 streams returns a list of Restaurant Objects

        List<EntityModel<Menu>> menus = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(menus, linkTo(methodOn(MenuController.class).all()).withSelfRel());
    }

    //    read single item
    @GetMapping("/menu/{menu_id}")
    EntityModel<Menu> one(@PathVariable int menu_id){

        Menu menu = repository.findById(menu_id)
                .orElseThrow(()-> new MenuNotFoundException(menu_id));

        return assembler.toModel(menu);
    }

    //    create
    @PostMapping("/menu")
    Menu newMenu(@RequestBody Menu newMenu){
        return repository.save(newMenu);
    }

    //    update item
    @PutMapping("/menu/{menu_id}")
    Menu replaceMenu(@RequestBody Menu newMenu, @PathVariable int menu_id){
        return repository.findById(menu_id)
                .map(menu -> {
                    menu.setTitle(newMenu.getTitle());
                    return repository.save(menu);
                })
                .orElseGet(()->{
                    newMenu.setMenu_id(menu_id);
                    return repository.save(newMenu);
                });
    }

    //    delete
    @DeleteMapping("/menu/{menu_id}")
    void deleteRestaurant(@PathVariable int menu_id){
        repository.deleteById(menu_id);
    }
}

