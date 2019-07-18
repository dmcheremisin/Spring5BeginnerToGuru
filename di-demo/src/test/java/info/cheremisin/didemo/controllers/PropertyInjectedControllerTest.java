package info.cheremisin.didemo.controllers;

import info.cheremisin.didemo.service.impl.GreetingServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyInjectedControllerTest {

    private PropertyInjectedController propertyInjectedController;

    @Before
    public void setUp() throws Exception {
        propertyInjectedController = new PropertyInjectedController();
        propertyInjectedController.greetingService = new GreetingServiceImpl();
    }

    @Test
    public void sayHello() throws Exception {
        assertNotEquals("hello", propertyInjectedController.sayHello());
        assertEquals("greeting", propertyInjectedController.sayHello());
    }

}