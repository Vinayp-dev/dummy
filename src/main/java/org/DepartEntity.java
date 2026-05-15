package org;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
public interface DepartEntity {
    @Data
    @Entity
    @Table(name = "dept_db")
    public class DepartmentEntity {
        @Id
        private int deptId;
        private String deptName;
    }
}
