package main.DAO.Repository;

import main.DAO.model.Department;
import main.api.response.SalaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Optional<Department> findByName(@Param("name") String name);

    @Query("select d.name as name, " +
            "(select avg(e.salary) from Employee e join e.department ed where d.id = ed.id ) as avg " +
            "from Department d group by d.name ")
    List<SalaryProjection> getDepartWithAVGSalary();


}
