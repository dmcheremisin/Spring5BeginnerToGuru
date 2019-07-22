package info.cheremisin.didemo.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class LifecycleDemoBean implements
        BeanNameAware, // setBeanName()
        BeanFactoryAware, // setBeanFactory()
        ApplicationContextAware, // setApplicationContext()
        InitializingBean, // afterPropertiesSet()
        DisposableBean // destroy()
{

    public LifecycleDemoBean() {
        System.out.println("--> LifeCycleBean Constructor");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("--> LifeCycleBean setBeanName");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("--> LifeCycleBean setBeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("--> LifeCycleBean setApplicationContext");
    }

    public void beforeInit() {
        System.out.println("--> LifeCycleBean beforeInit");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("--> LifeCycleBean postConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("--> LifeCycleBean afterPropertiesSet");
    }

    public void afterInit() {
        System.out.println("--> LifeCycleBean afterInit");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("--> LifeCycleBean preDestroy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("--> LifeCycleBean destroy");
    }
}
//--> LifeCycleBean Constructor
//--> LifeCycleBean setBeanName
//--> LifeCycleBean setBeanFactory
//--> LifeCycleBean setApplicationContext
//--> LifeCycleBean beforeInit
//--> LifeCycleBean postConstruct
//--> LifeCycleBean afterPropertiesSet
//--> LifeCycleBean afterInit
//--> LifeCycleBean preDestroy
//--> LifeCycleBean destroy

