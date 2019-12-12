package info.cheremisin.spring5webflux.controllers;

import info.cheremisin.spring5webflux.domain.Customer;
import info.cheremisin.spring5webflux.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class CustomerControllerTest {

    private CustomerRepository repository;
    private CustomerController controller;
    private WebTestClient client;

    @Before
    public void setUp() throws Exception {
        repository = Mockito.mock(CustomerRepository.class);
        controller = new CustomerController(repository);
        client = WebTestClient.bindToController(controller).build();
    }

    @Test
    public void getAllCustomers() {
        BDDMockito.given(repository.findAll())
                .willReturn(Flux.just(Customer.builder().id("1").build(),
                        Customer.builder().id("2").build()));

        client.get().uri("/api/v1/customers")
                .exchange()
                .expectBodyList(Customer.class)
                .hasSize(2);
    }

    @Test
    public void getById() {
        Customer customer = Customer.builder().id("1").build();
        BDDMockito.given(repository.findById(anyString()))
                .willReturn(Mono.just(customer));

        client.get().uri("/api/v1/customers/a123")
                .exchange()
                .expectBody(Customer.class)
                .isEqualTo(customer);
    }

    @Test
    public void createCustomer() {
        Customer customer = Customer.builder().id("12321").build();

        BDDMockito.given(repository.save(any(Customer.class)))
                .willReturn(Mono.just(customer));

        Mono<Customer> body = Mono.just(customer);

        client.post()
                .uri("/api/v1/customers")
                .body(body, Customer.class)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Customer.class)
                .isEqualTo(customer);
    }

    @Test
    public void updateCustomer() {
        Customer customer = Customer.builder().id("12321").build();
        BDDMockito.given(repository.save(any(Customer.class)))
                .willReturn(Mono.just(customer));

        Mono<Customer> body = Mono.just(customer);

        client.put()
                .uri("/api/v1/customers/ffdsf")
                .body(body, Customer.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Customer.class)
                .isEqualTo(customer);
    }

}