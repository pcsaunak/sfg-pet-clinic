package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    // This wires up the html page with this java file
    @RequestMapping({"","/","index","index.html"})
    public String index(){
        return "index";
    }


    @RequestMapping({"/oups"})
    public String oupsHandler(Model model){
        return "notImplemented";
    }
}
