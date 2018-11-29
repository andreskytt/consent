package com.proud.egov.consent.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/ui")
    public String homePage(Model model){
        return "index";
    }

}