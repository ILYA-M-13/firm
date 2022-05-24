package main.service;

import main.DAO.model.Department;
import main.api.request.DepartmentRequest;
import main.api.response.ErrorResponse;
import main.api.response.SalaryProjection;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    List<Department> getAll();

    ErrorResponse addDepartment(DepartmentRequest dp);

    List<SalaryProjection> getDepartmentWithAVGSalary();

    Map<String, Map<String, Integer>> getEmployeeWithSalaryByDepart();


}
