package main.service;

import lombok.AllArgsConstructor;
import main.DAO.Repository.DepartmentRepository;
import main.DAO.Repository.EmployeeRepository;
import main.DAO.model.Department;
import main.DAO.model.Employee;
import main.api.request.DepartmentRequest;
import main.api.response.ErrorResponse;
import main.api.response.SalaryProjection;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository dr;
    private final EmployeeRepository er;

    @Override
    public List<Department> getAll() {
        return StreamSupport.stream(dr.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public ErrorResponse addDepartment(DepartmentRequest dp) {
        String name = dp.getName();
        ErrorResponse errorResponse = new ErrorResponse();
        if (name.matches("[А-Яа-яA-Za-z-]+([А-Яа-яA-Za-z-\\s]+)?")) {
            Department department = new Department();
            department.setName(name);
            dr.save(department);
            errorResponse.setResult(true);
            return errorResponse;
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("department", "Некорректное имя");
            errorResponse.setErrors(errors);
            return errorResponse;
        }
    }

    @Override
    public List<SalaryProjection> getDepartmentWithAVGSalary() {
        return dr.getDepartWithAVGSalary();
    }

    @Override
    public Map<String, Map<String, Integer>> getEmployeeWithSalaryByDepart() {
              return er.findAll().stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment().getName(),Collectors.toMap(Employee::getName,Employee::getSalary)));


    }

}