package gr.example.vaadindemo.persistence;

import gr.example.vaadindemo.domain.Employee;
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
public class DBSeeder implements CommandLineRunner {

    // bankAccountRepo
    private EmployeeRepository employeeRepository;

    /**
     * Constructor
     * @param employeeRepository  the first parameter dependency
     */
    @Autowired
    public DBSeeder(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
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
    }
}
