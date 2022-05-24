package main.service;

import lombok.AllArgsConstructor;
import main.DAO.Repository.DepartmentRepository;
import main.DAO.Repository.EmployeeRepository;
import main.DAO.model.Department;
import main.DAO.model.Employee;
import main.api.request.EmployeeRequest;
import main.api.response.ErrorResponse;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository er;
    private final DepartmentRepository dr;


    @Override
    public List<Employee> getAll() {
        return StreamSupport.stream(er.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public ErrorResponse addEmployee(EmployeeRequest e) {
        ErrorResponse errorResponse = new ErrorResponse();
        Optional<Department> od = dr.findByName(e.getDepartment());
        if (od.isPresent()) {
            Employee employee = new Employee();
            employee.setBirthday(e.getBirthday());
            employee.setName(e.getName());
            employee.setSalary(e.getSalary());
            er.save(employee);
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("department", "Такого отдела не существует");
            errorResponse.setErrors(errors);
            return errorResponse;
        }
        errorResponse.setResult(true);
        return errorResponse;
    }

    @Override
    public Employee getEmployee(int id) {
        return er.findById(id).orElseThrow(() -> new NoSuchElementException("user not found"));
    }

    @Override
    public ErrorResponse deleteEmployee(int id) {
        ErrorResponse errorResponse = new ErrorResponse();
        if (checkEmployee(id)) {
            er.deleteById(id);
            errorResponse.setResult(true);
            return errorResponse;
        }
        Map<String, String> errors = new HashMap<>();
        errors.put("employee", "Такого работника не существует");
        errorResponse.setErrors(errors);
        return errorResponse;
    }

    @Override
    public Employee refactorEmployee(int id, String name, String salary, String birthday) {
        Employee oe = er.findById(id).orElseThrow(() -> new NoSuchElementException("user not found"));
        oe.setSalary(salary == null ? oe.getSalary() : Integer.parseInt(salary));
        oe.setName(name == null ? oe.getName() : name);
        return er.save(oe);
    }


    private boolean checkEmployee(int id) {
        return er.findById(id).isPresent();
    }

    @Override
    public List<Employee> getAllByDate(String dateFrom, String dateTo) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatter.parse(dateFrom);
        if (dateTo == null) {
            return er.getEmployeeByBirthday(startDate);
        } else {
            Date endDate = formatter.parse(dateTo);
            return er.getEmployeeByBirthdayBetween(startDate, endDate);
        }
    }


}
