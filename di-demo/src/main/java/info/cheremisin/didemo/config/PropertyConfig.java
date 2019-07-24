package info.cheremisin.didemo.config;

import info.cheremisin.didemo.examplebeans.FakeDataSource;
import info.cheremisin.didemo.examplebeans.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
//@PropertySource({"classpath:datasource.properties", "classpath:jms.properties"})
@PropertySources({
        @PropertySource({"classpath:datasource.properties"}),
        @PropertySource({"classpath:jms.properties"})
})
public class PropertyConfig {

    @Autowired
    Environment env;

    @Value("${info.username}")
    String user;

    @Value("${info.password}")
    String password;

    @Value("${info.dburl}")
    String url;

    @Value("${info.jms.user}")
    String jmsUser;

    @Value("${info.jms.password}")
    String jmsPassword;

    @Value("${info.jms.url}")
    String jmsUrl;

    @Bean
    public FakeDataSource fakeDataSource() {
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUser(user);
        fakeDataSource.setPassword(password);
        fakeDataSource.setUrl(url);
        fakeDataSource.setEnvironmentProperty(env.getProperty("logs"));
        return fakeDataSource;
    }
    @Bean
    public FakeJmsBroker fakeJmsBroker() {
        FakeJmsBroker fakeJmsBroker = new FakeJmsBroker();
        fakeJmsBroker.setUser(jmsUser);
        fakeJmsBroker.setPassword(jmsPassword);
        fakeJmsBroker.setUrl(jmsUrl);
        fakeJmsBroker.setEnvironmentProperty(env.getProperty("logs"));
        return fakeJmsBroker;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        return configurer;
    }
}

