package info.cheremisin.didemo.controllers;

import info.cheremisin.didemo.service.impl.GreetingServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConstructorInjectedControllerTest {

    private ConstructorInjectedController constructorInjectedController;

    @Before
    public void setUp() throws Exception {
        constructorInjectedController = new ConstructorInjectedController(new GreetingServiceImpl());
    }

    @Test
    public void sayHello() throws Exception {
        assertNotEquals("hello", constructorInjectedController.sayHello());
        assertEquals("greeting", constructorInjectedController.sayHello());
    }

}