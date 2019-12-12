package info.cheremisin.mvc.rest.services;

import info.cheremisin.mvc.rest.api.v1.mapper.CustomerMapper;
import info.cheremisin.mvc.rest.api.v1.model.CustomerDTO;
import info.cheremisin.mvc.rest.domain.Customer;
import info.cheremisin.mvc.rest.exceptions.ResourceNotFoundException;
import info.cheremisin.mvc.rest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    public static final CustomerMapper MAPPER = CustomerMapper.INSTANCE;
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> all = customerRepository.findAll();
        List<CustomerDTO> list = all.stream()
                .map(MAPPER::customerToCustomerDTOMapper)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return MAPPER.customerToCustomerDTOMapper(customer);
    }

    @Override
    public CustomerDTO getCustomerByLastName(String lastName) {
        Customer customerByLastName = customerRepository.findCustomerByLastName(lastName);
        return MAPPER.customerToCustomerDTOMapper(customerByLastName);
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        Customer customer = MAPPER.customerDTOToCustomerMapper(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO dto = MAPPER.customerToCustomerDTOMapper(savedCustomer);
        return dto;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}
