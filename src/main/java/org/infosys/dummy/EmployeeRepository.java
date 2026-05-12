package org.infosys.dummy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * This is repository interface from table employee to perform crud operations and entity class is only
 for mapping the table ,service class is for business logic and controller class is for handling the request and response
 Repository interface extendas a class called JpaRepository which is provided by Spring Data JPA, it provides various methods for performing CRUD operations on the database, such as save(), findById(), findAll(), deleteById() etc.
  By extending JpaRepository, we can use these methods without having to write any implementation code, Spring Data JPA will automatically generate the implementation for us at runtime.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    
}
