package org.infosys.dummy;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "emp_db")
public class EmployeeEntity {
    // @Id annotation is used to specify the primary key of the entity. It is mandatory for every entity to have a primary key.
    // we can add some annotations to the fields to add constraints like @Column(nullable = false) to make the column not null, @Column(unique = true) to make the column unique, etc.
    @Id
    private int id;
    private String name;
    private String dept;
    private double salary;
    private String email;
}
/* if we need to add another table THEN 
we can create another entity class with @Entity and @Table annotations and define the fields and their mappings to the database columns.
for example, if we want to create a table for departments, we can create a DepartmentEntity class like this:
@Data
@Entity 
@Table(name = "dept_db")
public class DepartmentEntity {
    @Id
    private int deptId;
    private String deptName;
}
    and then we can use this entity class object in our service and repository layers to perform CRUD operations on the dept_db table.
    Every record in the table dept_db will be represented as an object of DepartmentEntity class in our application.
    so every row is a object of class and every column is a field of that class.
    we can also establish relationships between entities using annotations like @OneToMany, @ManyTo
*/

