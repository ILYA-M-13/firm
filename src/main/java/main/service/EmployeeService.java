package main.service;

import main.DAO.model.Employee;
import main.api.request.EmployeeRequest;
import main.api.response.ErrorResponse;

import java.text.ParseException;
import java.util.List;

public interface EmployeeService {

    List<Employee> getAll();

    ErrorResponse addEmployee(EmployeeRequest e);

    Employee getEmployee(int id);

    ErrorResponse deleteEmployee(int id);

    Employee refactorEmployee(int id,String name,String salary,String birthday);

    List<Employee> getAllByDate(String dateFrom, String DateTo) throws ParseException;


}
