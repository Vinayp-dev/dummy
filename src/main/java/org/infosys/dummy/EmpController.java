package org.infosys.dummy;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*") // Allow requests from any origin during development
@RestController
public class EmpController {
    private static final Logger logger = LoggerFactory.getLogger(EmpController.class);
    // This is called dependency injection, where the controller depends on the service layer to perform operations related to employee management. The service layer is responsible for handling the business logic and data access, while the controller focuses on handling HTTP requests and responses.
    // We are not going to make object for service layer in controller, we will use spring to inject the service layer object into the controller, this is called dependency injection, it helps to decouple the controller and service layer, making the code more modular and easier to test.
    
    @Autowired
    EmployeeService empService;
    
    @GetMapping("employees")
    public ResponseEntity<java.util.List<Employee>> getAllEmployees(){
        try{
            java.util.List<Employee> list = empService.getAllEmployees();
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch(Exception e){
            logger.error("Error fetching employees", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   @GetMapping("employees/{id}")
   public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
       try{
           Employee emp = empService.readEmployee(id);
           if(emp==null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           return new ResponseEntity<>(emp, HttpStatus.OK);
       }catch(Exception e){
           logger.error("Error reading employee {}", id, e);
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }
   
    @PostMapping("employees")
    public ResponseEntity<String> addEmployee(@RequestBody Employee emp){
        try{
            logger.info("Adding employee: {}", emp.getName());
            String resp = empService.createEmployee(emp);
            return new ResponseEntity<>(resp, HttpStatus.CREATED);
        }catch(Exception e){
            logger.error("Error adding employee", e);
            return new ResponseEntity<>("Failed to add employee", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("employees/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable int id, @RequestBody Employee emp){
        try{
            logger.info("Updating employee {}", id);
            String resp = empService.updateEmployee(id, emp);
            if(resp != null && resp.toLowerCase().contains("not found")){
                return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(resp, HttpStatus.OK);
        }catch(Exception e){
            logger.error("Error updating employee {}", id, e);
            return new ResponseEntity<>("Failed to update employee", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id){
        try{
            boolean ok = empService.deleteEmployee(id);
            if(ok) return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        }catch(Exception e){
            logger.error("Error deleting employee {}", id, e);
            return new ResponseEntity<>("Failed to delete employee", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("Departments")
    public ResponseEntity<List<Department>> getAllDepartments(){
        logger.info("Hey dude fetching all departments");
        try {
            List<Department> departments = empService.getAllDepartments();
            return new ResponseEntity<>(departments, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching departments", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("Departments")
    public ResponseEntity<String> addDepartment(@RequestBody Department dept) {
        try {
            logger.info("Adding department: {}", dept.getDeptName());
            String resp = empService.createDepartment(dept);
            return new ResponseEntity<>(resp, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error adding department", e);
            return new ResponseEntity<>("Failed to add department", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("Departments/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable int id, @RequestBody Department dept) {
        try {
            logger.info("Updating department {}", id);
            String resp = empService.updateDepartment(id, dept);
            if(resp != null && resp.toLowerCase().contains("not found")){
                return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(resp, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating department {}", id, e);
            return new ResponseEntity<>("Failed to update department", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


