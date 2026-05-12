package org.infosys.dummy;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// This is a simple REST controller for managing Employee data ,acts like a Manager for Employee records, allowing clients to retrieve and add employees through HTTP requests.
// It uses an in-memory list to store employee data, which is not suitable for production but serves well for demonstration purposes. The controller provides endpoints to get all employees and add a new employee.
// We need service layer to store data in database and perform business logic
@RestController
public class EmpController {
    // This is called dependency injection, where the controller depends on the service layer to perform operations related to employee management. The service layer is responsible for handling the business logic and data access, while the controller focuses on handling HTTP requests and responses.
    // We are not going to make object for service layer in controller, we will use spring to inject the service layer object into the controller, this is called dependency injection, it helps to decouple the controller and service layer, making the code more modular and easier to test.
    @Autowired
    EmployeeService empService;
    @GetMapping("employees")
    public List<Employee> getAllEmployees(){
        return empService.getAllEmployees();
    }
   
    @PostMapping("employees")
    public String addEmployee(@RequestBody Employee emp){
        return empService.createEmployee(emp);
    }

    @PutMapping("employees/{id}")
    public String updateEmployee(@PathVariable int id, @RequestBody Employee emp){
        empService.updateEmployee(id, emp);
        return "Employee updated successfully";
    }

    @DeleteMapping("employees/{id}")
    public String deleteEmployee(@PathVariable int id){
        if(empService.deleteEmployee(id)){
            return "Employee deleted successfully";
        }
        return "Employee not found";
    }
}
