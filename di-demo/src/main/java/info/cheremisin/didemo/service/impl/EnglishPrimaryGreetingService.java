package info.cheremisin.didemo.service.impl;

import info.cheremisin.didemo.service.GreetingService;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Dmitrii on 19.07.2019.
 */
@Service
@Profile("en")
@Primary
public class EnglishPrimaryGreetingService implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Hello from en service";
    }
}
