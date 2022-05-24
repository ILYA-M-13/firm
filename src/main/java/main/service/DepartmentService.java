package main.service;

import main.DAO.model.Department;
import main.api.request.DepartmentRequest;
import main.api.response.EmployeeWithSalaryResponse;
import main.api.response.ErrorResponse;
import main.api.response.SalaryProjection;

import java.util.List;

public interface DepartmentService {

    List<Department> getAll();

    ErrorResponse addDepartment(DepartmentRequest dp);

    List<SalaryProjection> getDepartmentWithAVGSalary();

    List<EmployeeWithSalaryResponse> getEmployeeWithSalaryByDepart();


}
