package org.infosys.dummy;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository empRepo;

    @Override
    public String createEmployee(Employee emp) {
        EmployeeEntity empEntity = new EmployeeEntity();
        BeanUtils.copyProperties(emp, empEntity);
        empEntity.setId(0);  // Ensure new employee has no ID (auto-generated)
        empRepo.save(empEntity);
        return "Employee added successfully";
    }

    @Override
    public Employee readEmployee(int id) {
        EmployeeEntity empEntity = empRepo.findById(id).orElse(null);
        if(empEntity == null){
            return null; // or throw an exception
        }
        Employee emp = new Employee();
        BeanUtils.copyProperties(empEntity, emp);
        return emp;
    }
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> emp_e = empRepo.findAll();
        List<Employee> emplist = new ArrayList<>();
        for(EmployeeEntity empEntity:emp_e){
            Employee emp = new Employee();
            BeanUtils.copyProperties(empEntity, emp);  
            emplist.add(emp);
        }
        return emplist;
    }
    @Override
    public boolean deleteEmployee(int id) {
       EmployeeEntity empEntity = empRepo.findById(id).orElse(null);
         if(empEntity == null){
              return false;
        }
        empRepo.delete(empEntity);
        return true;
    }

    

    @Override
    public String updateEmployee(int id, Employee emp) {
        EmployeeEntity empEntity = empRepo.findById(id).orElse(null);
        if(empEntity == null){
            return "Employee not found";
        }
        BeanUtils.copyProperties(emp, empEntity);
        empRepo.save(empEntity);
        return "Employee updated successfully";
}
}
