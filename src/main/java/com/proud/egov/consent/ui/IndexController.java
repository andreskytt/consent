package com.proud.egov.consent.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @GetMapping("/ui")
//    public String home(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
    public String homePage(Model model){
        model.addAttribute("name", "Nipitiriamus");
        return "index";
    }

}