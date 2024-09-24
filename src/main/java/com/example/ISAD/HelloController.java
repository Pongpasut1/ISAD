package com.example.ISAD;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("ISADPROJECT")
    public String hello() {
        System.out.println("ISAD");
        return "ISADDDDD";
    }

}
