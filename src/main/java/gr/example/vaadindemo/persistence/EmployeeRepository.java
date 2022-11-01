package gr.example.vaadindemo.persistence;

import gr.example.vaadindemo.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA repository for the Employee entity
 *
 * @author gmavrommatis
 * @created 1/11/2022
 */
public interface  EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByLastNameStartsWithIgnoreCase(String lastName);
}
