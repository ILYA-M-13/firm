package main.controller;

import lombok.RequiredArgsConstructor;
import main.DAO.model.Department;
import main.DAO.model.Employee;
import main.api.request.DepartmentRequest;
import main.api.request.EmployeeRequest;
import main.api.response.ErrorResponse;
import main.api.response.SalaryProjection;
import main.service.DepartmentService;
import main.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiFirmController {

private final EmployeeService es;
private final DepartmentService ds;

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(es.getAll());
    }

    @PostMapping("/employee")
    public ResponseEntity<ErrorResponse> addEmployee(@RequestBody EmployeeRequest e){
        return ResponseEntity.ok(es.addEmployee(e));
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable int id){
        return ResponseEntity.ok(es.getEmployee(id));
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<ErrorResponse> delEmployee(@PathVariable int id){
        return ResponseEntity.ok(es.deleteEmployee(id));
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<?> refactorEmployee(@PathVariable int id,
                                              @RequestParam(value = "name", required = false) String name,
                                              @RequestParam(value = "salary", required = false) String salary,
                                              @RequestParam(value = "birthday", required = false) String birthday){
        return ResponseEntity.ok(es.refactorEmployee(id,name,salary,birthday));
    }


    @GetMapping("/department")
    public ResponseEntity<List<Department>> getAllDep() {
        return ResponseEntity.ok(ds.getAll());
    }

    @PostMapping("/department")
    public ResponseEntity<ErrorResponse> addDep(@RequestBody DepartmentRequest dp){
        return ResponseEntity.ok(ds.addDepartment(dp));
    }

    @GetMapping("/employee/avgSalary")
    public ResponseEntity<List<SalaryProjection>> getAllDepartmentWithAvgSalary() {
        return ResponseEntity.ok(ds.getDepartmentWithAVGSalary());
    }

    @GetMapping("/employee/byDate")
    public ResponseEntity<List<Employee>> getAllByDate( @RequestParam(value = "dateFrom") String dateFrom,
                                                        @RequestParam(value = "dateTo", required = false) String dateTo) throws ParseException {
        return ResponseEntity.ok(es.getAllByDate(dateFrom,dateTo));
    }

    @GetMapping("/employee/byDepart")
    public ResponseEntity <Map<String, Map<String, Integer>>> getEmployeeWithSalary() {
        return ResponseEntity.ok(ds.getEmployeeWithSalaryByDepart());
    }
}
