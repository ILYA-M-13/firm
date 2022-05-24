package main.DAO.Repository;

import main.DAO.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    List<Employee>getEmployeeByBirthdayBetween(@Param("startDate") Date startDate, @Param("endDate")Date endDate);

    List<Employee> getEmployeeByBirthday(@Param("birthday") Date birthday);


}
