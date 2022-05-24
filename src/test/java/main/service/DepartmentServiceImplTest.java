package main.service;


import main.DAO.Repository.DepartmentRepository;
import main.api.request.DepartmentRequest;
import main.api.response.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @InjectMocks
    DepartmentServiceImpl service;

    @Mock
    DepartmentRepository dr;

    DepartmentRequest incorrectRequest;
    DepartmentRequest correctRequest;
    @BeforeEach
    void setUp() {
        incorrectRequest = new DepartmentRequest();
        incorrectRequest.setName("4f5g45g");

        correctRequest = new DepartmentRequest();
        correctRequest.setName("Отдел");

    }

    @Test
    void addDepartmentWithIncorrectRequest(){

        ErrorResponse errorResponse = service.addDepartment(incorrectRequest);
        assertEquals(errorResponse.isResult(),false);
        assertEquals(errorResponse.getErrors().get("department"),"Некорректное имя");
    }

    @Test
    void addDepartmentWithCorrectRequest(){

        ErrorResponse errorResponse = service.addDepartment(correctRequest);
        assertEquals(errorResponse.isResult(),true);

    }


}