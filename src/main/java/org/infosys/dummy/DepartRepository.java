package org.infosys.dummy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DepartRepository extends JpaRepository<DepartEntity, Integer> {
    // We can also define custom query methods here if needed, for example:
    // List<DepartEntity> findByDeptName(String deptName);
    DepartEntity findByDeptName(String deptName);


    
}
