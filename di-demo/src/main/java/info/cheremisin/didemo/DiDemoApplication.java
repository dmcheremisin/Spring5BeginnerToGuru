package info.cheremisin.didemo;

import info.cheremisin.didemo.controllers.ConstructorInjectedController;
import info.cheremisin.didemo.controllers.HelloController;
import info.cheremisin.didemo.controllers.PropertyInjectedController;
import info.cheremisin.didemo.controllers.SetterInjectedController;
import info.cheremisin.didemo.examplebeans.FakeDataSource;
import info.cheremisin.didemo.examplebeans.FakeJmsBroker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"info.cheremisin"})
public class DiDemoApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DiDemoApplication.class, args);

		HelloController controller = (HelloController) ctx.getBean("helloController");
		controller.hello();

		System.out.println(ctx.getBean(ConstructorInjectedController.class).sayHello());
		System.out.println(ctx.getBean(PropertyInjectedController.class).sayHello());
		System.out.println(ctx.getBean(SetterInjectedController.class).sayHello());

		FakeDataSource fakeDataSource = ctx.getBean(FakeDataSource.class);
		System.out.println("Data Source Property: " + fakeDataSource.getUser());
		System.out.println("Environment Property: " + fakeDataSource.getEnvironmentProperty());

		FakeJmsBroker fakeJmsBroker = ctx.getBean(FakeJmsBroker.class);
		System.out.println("JMS Property: " + fakeJmsBroker.getUser());
	}

	//--> LifeCycleBean Constructor
	//--> LifeCycleBean setBeanName
	//--> LifeCycleBean setBeanFactory
	//--> LifeCycleBean setApplicationContext
	//--> LifeCycleBean beforeInit
	//--> LifeCycleBean postConstruct
	//--> LifeCycleBean afterPropertiesSet
	//--> LifeCycleBean afterInit
	//	Hello Controller!
	//	Всем привет!
	//	greeting
	//	greeting
	//	greeting
	//	Data Source Property: dmitrii
	//	Environment Property: /springapp/logs
	//	JMS Property: jms
	//--> LifeCycleBean preDestroy
	//--> LifeCycleBean destroy


}
