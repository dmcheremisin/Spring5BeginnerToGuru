package info.cheremisin.didemo.service.impl;

import info.cheremisin.didemo.service.GreetingService;
import org.springframework.stereotype.Service;

@Service
public class ConstructorGreetingServiceImpl implements GreetingService {

    @Override
    public String sayGreeting() {
        return "greeting from constructor greeting service";
    }
}
