package info.cheremisin;

//import org.apache.activemq.artemis.core.config.impl.ConfigurationImpl;
//import org.apache.activemq.artemis.core.server.ActiveMQServer;
//import org.apache.activemq.artemis.core.server.ActiveMQServers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JmsApplication {

    public static void main(String[] args) throws Exception {

//        Springboot auto configures next lines, base on dependencies: artemis-server and artemis-jms-server.
//        Commented out for standalone ActiveMQ Artemis server
//
//        ActiveMQServer server = ActiveMQServers.newActiveMQServer(
//                new ConfigurationImpl()
//                        .setPersistenceEnabled(false)
//                        .setJournalDirectory("target/data/journal")
//                        .setSecurityEnabled(false)
//                        .addAcceptorConfiguration("invm", "vm://0")
//        );
//        server.start();

        SpringApplication.run(JmsApplication.class, args);
    }

}
