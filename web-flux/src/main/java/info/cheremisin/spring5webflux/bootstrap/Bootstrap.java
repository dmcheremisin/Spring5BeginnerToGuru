package info.cheremisin.spring5webflux.bootstrap;

import info.cheremisin.spring5webflux.domain.Category;
import info.cheremisin.spring5webflux.domain.Customer;
import info.cheremisin.spring5webflux.repositories.CategoryRepository;
import info.cheremisin.spring5webflux.repositories.CustomerRepository;
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
        if(categoryRepository.count().block() == 0) {
            loadCategories();
        }
        if(customerRepository.count().block() == 0) {
            loadCustomers();
        }
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

        categoryRepository.save(fruits).block();
        categoryRepository.save(dried).block();
        categoryRepository.save(fresh).block();
        categoryRepository.save(exotic).block();
        categoryRepository.save(nuts).block();

        log.info("Categories loaded: " + categoryRepository.count().block());
    }

    private void loadCustomers() {
        Customer joe = new Customer();
        joe.setFirstName("Joe");
        joe.setLastName("Tribiani");

        Customer ros = new Customer();
        ros.setFirstName("Ros");
        ros.setLastName("Geller");

        Customer monika = new Customer();
        monika.setFirstName("Monika");
        monika.setLastName("Geller");

        Customer fibie = new Customer();
        fibie.setFirstName("Fibie");
        fibie.setLastName("Buffe");

        customerRepository.save(joe).block();
        customerRepository.save(ros).block();
        customerRepository.save(monika).block();
        customerRepository.save(fibie).block();

        log.info("Customers loaded: " + customerRepository.count().block());
    }
}
