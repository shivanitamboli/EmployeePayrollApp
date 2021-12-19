package com.bridgelabz.employeepayrollapp.controller;


        import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
        import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
        import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
        import com.bridgelabz.employeepayrollapp.services.IEmployeePayrollService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;



        import java.util.List;


@RestController
@RequestMapping("/employeePayrollservice")
public class EmployeePayrollController {

    @Autowired
    private IEmployeePayrollService employeePayrollService;

    @RequestMapping(value= {"","/","/get"})
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(){
        List<EmployeePayrollData> empDataList = null;
        empDataList = employeePayrollService.getEmployeePayrollData();
        ResponseDTO respDTO = new ResponseDTO("Get Call Success",empDataList);
        return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
    }

    /**
     * localhost:8080/employeePayrollservice/get/1
     * @param empId
     * @return
     */
    @GetMapping("/get/{empId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empId") int empId){
        EmployeePayrollData employeePayrollData = employeePayrollService.getEmployeePayrollDataById(empId);
        ResponseDTO respDTO = new ResponseDTO("Get call for ID Successful:",employeePayrollData);
        return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
    }

    /**
     * localhost:8080/employeePayrollservice/create
     * @param empPayrollDTO
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addEmployeePayrollData(@RequestBody EmployeePayrollDTO empPayrollDTO){
        EmployeePayrollData employeePayrollData = employeePayrollService.createEmployeePayrollData(empPayrollDTO);
        ResponseDTO respDTO = new ResponseDTO("Create Employee PayrollData:",employeePayrollData);
        return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
    }
    @PutMapping("/update/{empId}")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") int empId,@RequestBody EmployeePayrollDTO empPayrollDTO){
        EmployeePayrollData empData= null;
        empData = employeePayrollService.updateEmployeePayrollData(empId, empPayrollDTO);
        ResponseDTO respDTO = new ResponseDTO("Update Employee PayrollData Successful:",empData);
        return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);

    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") int empId){
        employeePayrollService.deleteEmployeePayrollData(empId);
        ResponseDTO respDTO = new ResponseDTO("Deleted Successful,Deleted Id:",empId);
        return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
    }
}