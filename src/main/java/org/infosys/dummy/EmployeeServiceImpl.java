package org.infosys.dummy;
import java.util.ArrayList;
import java.util.List;
public class EmployeeServiceImpl implements EmployeeService {
    List<Employee> empList = new ArrayList<>();
    @Override
    public String createEmployee(Employee emp) {
        empList.add(emp);
        return "Employee added successfully";
    }

    @Override
    public List<Employee> getAllEmployees() {
        return empList;
    }

    @Override
    public boolean deleteEmployee(int id) {
        return empList.removeIf(emp -> emp.getId() == id);
    }

    @Override
    public String updateEmployee(int id, Employee emp) {
       for(Employee e:empList){
           if(e.getId()==id){
               e.setName(emp.getName());
               e.setDept(emp.getDept());
               e.setSalary(emp.getSalary());
               e.setEmail(emp.getEmail());
               return "Employee updated successfully";
           }
    }
     return "Employee not found";
}
}
