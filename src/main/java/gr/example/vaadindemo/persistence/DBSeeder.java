package gr.example.vaadindemo.persistence;

import gr.example.vaadindemo.domain.Customer;
import gr.example.vaadindemo.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * DBSeeder sets the database with the accounts and atm tables
 * since it starts the first in the spring context, it inits the database even before the
 * whole spring context is fully up and running
 *
 * @author gmavrommatis
 * @created 1/11/2022
 */
@Component
@Slf4j
public class DBSeeder implements CommandLineRunner {

    // bankAccountRepo
    private EmployeeRepository employeeRepository;
    private CustomerRepository customerRepository;

    /**
     * Constructor
     * @param employeeRepository  the first parameter dependency
     */
    @Autowired
    public DBSeeder(CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
    }

    /**
     *
     * @param args arguments
     * @throws Exception
     */
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // this runs first on the spring context

        // Clean up database tables
        employeeRepository.deleteAllInBatch();

        // Create employees
        List<Employee> employees = Arrays.asList(
                Employee.builder().firstName("Bill").lastName("Gates").build(),
                Employee.builder().firstName("Mark").lastName("Zuckerberg").build(),
                Employee.builder().firstName("Sundar").lastName("Pichai").build(),
                Employee.builder().firstName("Jeff").lastName("Bezos").build()
        );

        // save employees
        employeeRepository.saveAll(employees);

        //save customers
        saveCustomers();
    }

    private void saveCustomers() {
        // save a couple of customers
        customerRepository.save(new Customer("Jack", "Bauer"));
        customerRepository.save(new Customer("Chloe", "O'Brian"));
        customerRepository.save(new Customer("Kim", "Bauer"));
        customerRepository.save(new Customer("David", "Palmer"));
        customerRepository.save(new Customer("Michelle", "Dessler"));

        // fetch all customers
        log.info("Customers found with findAll():");
        log.info("-------------------------------");
        for (Customer customer : customerRepository.findAll()) {
            log.info(customer.toString());
        }
        log.info("");

        // fetch an individual customer by ID
        Customer customer = customerRepository.findById(1L).get();
        log.info("Customer found with findOne(1L):");
        log.info("--------------------------------");
        log.info(customer.toString());
        log.info("");

        // fetch customers by last name
        log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
        log.info("--------------------------------------------");
        for (Customer bauer : customerRepository
                .findByLastNameStartsWithIgnoreCase("Bauer")) {
            log.info(bauer.toString());
        }
        log.info("");
    }
}
