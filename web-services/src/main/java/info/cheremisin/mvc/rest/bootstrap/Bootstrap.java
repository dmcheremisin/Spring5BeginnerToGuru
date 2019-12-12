package info.cheremisin.mvc.rest.bootstrap;

import info.cheremisin.mvc.rest.domain.Category;
import info.cheremisin.mvc.rest.domain.Customer;
import info.cheremisin.mvc.rest.repositories.CategoryRepository;
import info.cheremisin.mvc.rest.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setFirstName("Joe");
        customer1.setLastName("Tribiani");

        Customer customer2 = new Customer();
        customer2.setFirstName("Ros");
        customer2.setLastName("Geller");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        log.info("Categories loaded: " + categoryRepository.count());
    }
}
