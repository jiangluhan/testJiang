package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class test {
    @Controller
    @RequestMapping("/locale")
    public class LocaleController {

        @GetMapping("/date")
        @ResponseBody
        public String locale(Locale locale){
            System.out.println("Controller is running !");
            return "Hello！IDEA！" ;
        }
    }
}

