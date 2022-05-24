package main.service;

import main.DAO.Repository.DepartmentRepository;
import main.DAO.Repository.EmployeeRepository;
import main.DAO.model.Department;
import main.DAO.model.Employee;
import main.api.request.EmployeeRequest;
import main.api.response.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @InjectMocks
    EmployeeServiceImpl service;
    @Mock
    EmployeeRepository er;
    @Mock
    DepartmentRepository dr;

    EmployeeRequest employeeRequest;
    @BeforeEach
    void setUp() {
        employeeRequest = new EmployeeRequest();

    }
    @Test
    void addEmployeeWithIncorrectRequest(){

        Mockito.when(dr.findByName(Mockito.any())).thenReturn(Optional.empty());
        ErrorResponse errorResponse = service.addEmployee(employeeRequest);
        assertEquals(errorResponse.isResult(),false);
        assertEquals(errorResponse.getErrors().get("department"),"Такого отдела не существует");
    }

    @Test
    void addEmployeeWithCorrectRequest(){

        Mockito.when(dr.findByName(Mockito.any())).thenReturn(Optional.of(new Department()));
        ErrorResponse errorResponse = service.addEmployee(employeeRequest);
        assertEquals(errorResponse.isResult(),true);

    }


    @Test
    void deleteEmployeeWithCorrectRequest(){

        Mockito.when(er.findById(Mockito.anyInt())).thenReturn(Optional.of(new Employee()));
        ErrorResponse errorResponse = service.deleteEmployee(1);
        assertEquals(errorResponse.isResult(),true);

    }

    @Test
    void deleteEmployeeWithIncorrectRequest(){

        Mockito.when(er.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        ErrorResponse errorResponse = service.deleteEmployee(1);
        assertEquals(errorResponse.isResult(),false);
        assertEquals(errorResponse.getErrors().get("employee"),"Такого работника не существует");

    }

}