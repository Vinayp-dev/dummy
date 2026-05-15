package org.infosys.dummy;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "dept_db")
public class DepartEntity {
    @Id
    private int deptId;
    private String deptName;
}
