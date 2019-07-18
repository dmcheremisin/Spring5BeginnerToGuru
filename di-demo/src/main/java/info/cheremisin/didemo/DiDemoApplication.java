package info.cheremisin.didemo;

import info.cheremisin.didemo.controllers.ConstructorInjectedController;
import info.cheremisin.didemo.controllers.HelloController;
import info.cheremisin.didemo.controllers.PropertyInjectedController;
import info.cheremisin.didemo.controllers.SetterInjectedController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DiDemoApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DiDemoApplication.class, args);

		HelloController controller = (HelloController) ctx.getBean("helloController");
		controller.hello();

		System.out.println(ctx.getBean(ConstructorInjectedController.class).sayHello());
		System.out.println(ctx.getBean(PropertyInjectedController.class).sayHello());
		System.out.println(ctx.getBean(SetterInjectedController.class).sayHello());
	}

}
