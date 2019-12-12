package info.cheremisin.mvc.rest.api.v1.mapper;

import info.cheremisin.mvc.rest.api.v1.model.CustomerDTO;
import info.cheremisin.mvc.rest.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    public static final String FIRST_NAME = "JOE";
    public static final String LAST_NAME = "SMITH";
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDtoMapperTest(){
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTOMapper(customer);

        assertEquals(customer.getId(), customerDTO.getId());
        assertEquals(customer.getFirstName(), customerDTO.getFirstName());
        assertEquals(customer.getLastName(), customerDTO.getLastName());
    }

    @Test
    public void customerDtoToCustomerTest() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(1L);
        customerDTO.setFirstName(FIRST_NAME);
        customerDTO.setLastName(LAST_NAME);

        Customer customer = customerMapper.customerDTOToCustomerMapper(customerDTO);

        assertEquals(customerDTO.getId(), customer.getId());
        assertEquals(customerDTO.getFirstName(), customer.getFirstName());
        assertEquals(customerDTO.getLastName(), customer.getLastName());
    }
}