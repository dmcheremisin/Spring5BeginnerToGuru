package info.cheremisin.mvc.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.cheremisin.mvc.rest.advice.NotFoundExceptionHandler;
import info.cheremisin.mvc.rest.api.v1.model.CustomerDTO;
import info.cheremisin.mvc.rest.exceptions.ResourceNotFoundException;
import info.cheremisin.mvc.rest.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    public static final String CUSTOMERS_URL = "/api/v1/customers";
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    CustomerDTO customerDTO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new NotFoundExceptionHandler())
                .build();

        customerDTO = new CustomerDTO();
        customerDTO.setId(123L);
        customerDTO.setFirstName("Chandler");
        customerDTO.setLastName("Bing");
    }

    @Test
    public void getAllCustomers() throws Exception {
        List<CustomerDTO> customers = Arrays.asList(new CustomerDTO(), new CustomerDTO(), new CustomerDTO());

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get(CUSTOMERS_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void getCustomerById() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        mockMvc.perform(get(CUSTOMERS_URL + "/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(customerDTO.getId().intValue())))
                .andExpect(jsonPath("$.firstName", equalTo(customerDTO.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(customerDTO.getLastName())));
    }

    @Test
    public void getCustomerByLastName() throws Exception {
        when(customerService.getCustomerByLastName(anyString())).thenReturn(customerDTO);

        mockMvc.perform(get(CUSTOMERS_URL + "/aaa/lastName").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(customerDTO.getId().intValue())))
                .andExpect(jsonPath("$.firstName", equalTo(customerDTO.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(customerDTO.getLastName())));

    }

    @Test
    public void createCustomerTest() throws Exception {
        when(customerService.saveCustomer(any())).thenReturn(customerDTO);

        mockMvc.perform(post(CUSTOMERS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(customerDTO.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(customerDTO.getLastName())));
    }

    @Test
    public void updateCustomerTest() throws Exception {
        when(customerService.saveCustomer(any())).thenReturn(customerDTO);

        mockMvc.perform(put(CUSTOMERS_URL + "/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(customerDTO.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalTo(customerDTO.getLastName())));
    }

    @Test
    public void deleteCustomer() throws Exception {

        mockMvc.perform(delete(CUSTOMERS_URL + "/123"))
                .andExpect(status().isOk());

        verify(customerService, times(1)).deleteCustomer(anyLong());
    }

    @Test
    public void testNotFoundCustomer() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenThrow(new ResourceNotFoundException());

        mockMvc.perform(get(CUSTOMERS_URL + "/1234").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }
}