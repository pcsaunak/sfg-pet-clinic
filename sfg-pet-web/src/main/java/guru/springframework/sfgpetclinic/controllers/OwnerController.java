package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
* More than one ways of doing things in Spring.
* */

@RequestMapping({"/owners"})
@Controller
public class OwnerController {
    @RequestMapping({"","/index","/index.html"})
    public String listOwners(){
        return "owners/index";
    }
}
