package info.cheremisin.didemo.controllers;

import info.cheremisin.didemo.service.GreetingService;
import org.springframework.stereotype.Controller;

/**
 * Created by Dmitrii on 17.07.2019.
 */
@Controller
public class HelloController {

    private GreetingService greetingService;

    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public void hello() {
        System.out.println("Hello Controller!");
        System.out.println(greetingService.sayGreeting());
    }
}
