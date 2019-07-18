package info.cheremisin.didemo.controllers;

import info.cheremisin.didemo.service.impl.GreetingServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SetterInjectedControllerTest {

    private SetterInjectedController setterInjectedController;

    @Before
    public void setUp() throws Exception {
        setterInjectedController = new SetterInjectedController();
        setterInjectedController.setGreetingService(new GreetingServiceImpl());
    }

    @Test
    public void sayHello() throws Exception {
        assertNotEquals("hello", setterInjectedController.sayHello());
        assertEquals("greeting", setterInjectedController.sayHello());
    }

}