package org.infosys.dummy;

import java.util.List;
/**
 * This is service interface for employee entity to perform business logic
 * It is a interface that defines the contract for the service layer.
 * We will have a implementation class for this interface, where we will write the actual business logic for performing CRUD operations on employee data.
 * The service layer acts as an intermediary between the controller and the repository layer, it is responsible for handling the business logic and data access, while the controller focuses on handling HTTP requests and responses.
 **/
public interface  EmployeeService {
    String createEmployee(Employee emp);
    List<Employee> getAllEmployees();
    boolean deleteEmployee(int id);
    String updateEmployee(int id, Employee emp);
}
