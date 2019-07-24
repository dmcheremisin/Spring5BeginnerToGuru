package info.cheremisin.didemo.config;

import info.cheremisin.didemo.examplebeans.ApplicationPropertiesSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationPropertiesConfig {

    @Value("${global.application.greeting}")
    String greeting;

    @Value("${global.application.speed}")
    String speed;

    @Bean
    public ApplicationPropertiesSource getApplicationPropSource() {
        ApplicationPropertiesSource source = new ApplicationPropertiesSource();
        source.setGreeting(greeting);
        source.setSpeed(speed);
        return source;
    }

}
