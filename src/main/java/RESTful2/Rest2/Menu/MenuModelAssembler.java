package RESTful2.Rest2.Menu;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class MenuModelAssembler implements RepresentationModelAssembler<Menu, EntityModel<Menu>> {

    @Override
    public EntityModel<Menu> toModel(Menu restaurant) {

        return EntityModel.of(restaurant, //
                linkTo(methodOn(MenuController.class).one(restaurant.getMenu_id())).withSelfRel(),
                linkTo(methodOn(MenuController.class).all()).withRel("menus"));
    }
}
//    converting a non model object (Menu) into a model-based object(EntityModel<Menu>