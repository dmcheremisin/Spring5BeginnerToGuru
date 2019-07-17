package info.cheremisin.didemo.controllers;

import org.springframework.stereotype.Controller;

/**
 * Created by Dmitrii on 17.07.2019.
 */
@Controller
public class HelloController {

    public void hello() {
        System.out.println("Hello Controller!");
    }
}
