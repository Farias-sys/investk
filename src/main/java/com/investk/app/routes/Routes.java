package com.investk.app.routes;

import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Primary
@CrossOrigin(origins = "*")
public class Routes {
    @GetMapping(value = "/")
    public String informVersion() {
        return "InvestK API Version 1.0 by Farias-sys";
    }
}
