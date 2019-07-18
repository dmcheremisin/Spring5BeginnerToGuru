package info.cheremisin.didemo.controllers;

import info.cheremisin.didemo.service.GreetingService;

public class PropertyInjectedController {

    public GreetingService greetingService;

    public String sayHello() {
        return greetingService.sayGreeting();
    }
}
