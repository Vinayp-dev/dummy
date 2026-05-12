package org.infosys.dummy;

import java.util.List;

public interface  EmployeeService {
    String createEmployee(Employee emp);
    List<Employee> getAllEmployees();
    boolean deleteEmployee(int id);
    String updateEmployee(int id, Employee emp);
}
