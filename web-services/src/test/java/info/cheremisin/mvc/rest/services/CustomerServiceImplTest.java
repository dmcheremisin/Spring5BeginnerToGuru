package info.cheremisin.mvc.rest.services;

import info.cheremisin.mvc.rest.api.v1.model.CustomerDTO;
import info.cheremisin.mvc.rest.domain.Customer;
import info.cheremisin.mvc.rest.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    public static final String FIRST_NAME = "Joe";
    public static final String LAST_NAME = "Tribiani";
    public static final long ID = 1L;

    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;

    Customer customer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository);

        customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);
    }

    @Test
    public void getAllCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer());
        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> allCustomers = customerService.getAllCustomers();

        assertNotNull(allCustomers);
        assertEquals(allCustomers.size(), customers.size());
    }

    @Test
    public void getCustomerById() {
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

        CustomerDTO customerById = customerService.getCustomerById(ID);

        assertNotNull(customerById);
        assertEquals(customer.getId(), customerById.getId());
        assertEquals(customer.getFirstName(), customerById.getFirstName());
        assertEquals(customer.getLastName(), customerById.getLastName());
    }

    @Test
    public void getCustomerByLastName() {
        when(customerRepository.findCustomerByLastName(anyString())).thenReturn(customer);

        CustomerDTO customerByLastName = customerService.getCustomerByLastName("abc");

        assertNotNull(customerByLastName);
        assertEquals(customer.getId(), customerByLastName.getId());
        assertEquals(customer.getFirstName(), customerByLastName.getFirstName());
        assertEquals(customer.getLastName(), customerByLastName.getLastName());
    }

    @Test
    public void createCustomerTest() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(ID);
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        when(customerRepository.save(any())).thenReturn(customer);

        CustomerDTO customer = customerService.saveCustomer(customerDTO);

        assertNotNull(customer);
        assertEquals(customer.getId(), customerDTO.getId());
        assertEquals(customer.getFirstName(), customerDTO.getFirstName());
        assertEquals(customer.getLastName(), customerDTO.getLastName());
    }

    @Test
    public void deleteCustomer() {
        customerService.deleteCustomer(1L);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}