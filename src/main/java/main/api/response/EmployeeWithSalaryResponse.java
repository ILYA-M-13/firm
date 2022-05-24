package main.api.response;


import lombok.Data;

@Data
public class EmployeeWithSalaryResponse {

    private String departmentName;
    private String name;
    private int salary;
}
