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

    @Autowired
    private DepartRepository deptRepo;

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
    @Override
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
        // Avoid overwriting the primary key when copying properties from DTO
        BeanUtils.copyProperties(emp, empEntity, "id");
        empRepo.save(empEntity);
        return "Employee updated successfully";
}
@Override
public List<Department> getAllDepartments() {
    List<DepartEntity> departments = deptRepo.findAll();
    List<Department> deptList = new ArrayList<>();
    for (DepartEntity deptEntity : departments) {
        Department dept = new Department();
        BeanUtils.copyProperties(deptEntity, dept);
        deptList.add(dept);
    }
    return deptList;
}
@Override
public String updateDepartment(int id, Department dept) {
    DepartEntity deptEntity = deptRepo.findById(id).orElse(null);
    if (deptEntity == null) {
        return "Department not found";
    }
    BeanUtils.copyProperties(dept, deptEntity, "deptId");
    deptRepo.save(deptEntity);
    return "Department updated successfully";
}

@Override
public String createDepartment(Department dept) {
    DepartEntity deptEntity = new DepartEntity();
    BeanUtils.copyProperties(dept, deptEntity);
    deptRepo.save(deptEntity);
    return "Department added successfully";
}

}
