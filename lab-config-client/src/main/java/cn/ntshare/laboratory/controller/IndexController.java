package cn.ntshare.laboratory.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping
public class IndexController {

    @Value("${foo}")
    String foo;

    @GetMapping("/hi")
    public String hi() {
        return foo;
    }
}
