package BitlabBootcampCourse.bootcampspring.controllers;

import BitlabBootcampCourse.bootcampspring.db.DBManager;
import BitlabBootcampCourse.bootcampspring.model.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String indexPage(Model model) {
        ArrayList<Item> items = DBManager.getItems();
        model.addAttribute("items", items);
        return "index";
    }

    @GetMapping(value = "/addItem")
    public String addItemGet() {
        return "addItem";
    }

    @PostMapping(value = "/addItem")
    public String addItemPost(@RequestParam(name = "item_title") String name,
                              @RequestParam(name = "item_price") double price,
                              @RequestParam(name = "item_description") String description) {
        Item item = new Item();
        item.setName(name);
        item.setPrice(price);
        item.setDescription(description);
        DBManager.addItem(item);
        return "redirect:/";

    }

    @GetMapping(value = "/details")
    public String details(@RequestParam(name = "id") Long id,
            Model model) {
        Item item = DBManager.getItem(id);
        System.out.println(id);
        model.addAttribute("item", item);
        return "details";
    }

}
