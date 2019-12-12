package info.cheremisin.spring5webflux.controllers;

import info.cheremisin.spring5webflux.domain.Category;
import info.cheremisin.spring5webflux.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class CategoryControllerTest {

    private CategoryRepository categoryRepository;
    private CategoryController categoryController;
    private WebTestClient webTestClient;

    @Before
    public void setUp() throws Exception {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryController = new CategoryController(categoryRepository);
        webTestClient = WebTestClient.bindToController(categoryController).build();
    }

    @Test
    public void getCategories() {
        BDDMockito.given(categoryRepository.findAll())
                .willReturn(Flux.just(Category.builder().name("Cat1").build(),
                        Category.builder().name("Cat2").build()));

        webTestClient.get().uri("/api/v1/categories")
                .exchange()
                .expectBodyList(Category.class)
                .hasSize(2);
    }

    @Test
    public void getById() {
        Category category = Category.builder().name("category").build();
        BDDMockito.given(categoryRepository.findById(anyString()))
                .willReturn(Mono.just(category));

        webTestClient.get().uri("/api/v1/categories/adsa")
                .exchange()
                .expectBody(Category.class)
                .isEqualTo(category);
    }

    @Test
    public void createCategory() {
        Category category = Category.builder().name("category").build();
        BDDMockito.given(categoryRepository.save(any(Category.class)))
                .willReturn(Mono.just(category));

        Mono<Category> mono = Mono.just(category);

        webTestClient.post()
                .uri("/api/v1/categories")
                .body(mono, Category.class)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(Category.class)
                .isEqualTo(category);
    }

    @Test
    public void updateCategory() {
        Category category = Category.builder().name("category").build();
        BDDMockito.given(categoryRepository.save(any(Category.class)))
                .willReturn(Mono.just(category));

        Mono<Category> mono = Mono.just(category);

        webTestClient.put()
                .uri("/api/v1/categories/sdfds")
                .body(mono, Category.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Category.class)
                .isEqualTo(category);
    }
}