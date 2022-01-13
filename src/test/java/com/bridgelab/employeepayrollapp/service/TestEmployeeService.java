package com.bridgelab.employeepayrollapp.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.bridgelab.employeepayrollapp.model.Employee;
import com.bridgelab.employeepayrollapp.repository.EmployeeRepository;
import com.bridgelab.employeepayrollapp.services.EmployeeService;

@SpringBootTest
public class TestEmployeeService {

    @Mock
    private EmployeeRepository empRepository;

    @InjectMocks
    private EmployeeService service;

    Employee emp = null;
    Employee emp1 = null;
    List<Employee> empList= null;

    @BeforeEach
    public void setUp() {
        List<String> department = new ArrayList<String>();
        department.add("HR");
        department.add("Sales");
        Employee emp = new Employee(1, "shivani", "FeMale", 89000.0, LocalDate.now(), "this is note", "this pic ",
                department);
        Employee emp1 = new Employee(2, "Anuja", "Female", 97000, LocalDate.now(), "notes", "pic", department);
        empList = new ArrayList<>();
        empList.add(emp1);
        empList.add(emp);
    }

    @Test
    public void testGetAllEmp() {
        when(empRepository.findAll()).thenReturn(empList);
        assertThat(empList).isEqualTo(service.getAllEmp());
    }

    @Test
    public void testSave() {
        when(empRepository.save(emp)).thenReturn(emp);
        Employee savedEmp = service.save(emp);
        assertThat(emp).isEqualTo(savedEmp);
    }

    @Test
    public void testFindEmpById() {
        doReturn(emp).when(empRepository.findById(1));
        //when(empRepository.findById(1)).thenReturn(Optional.of(emp));
        assertThat(emp).isEqualTo(service.getEmpById(1));
    }

    @Test
    public void testDeleteById() {
        when(empRepository.existsById(1)).thenReturn(true);
        assertThat(true).isEqualTo(empRepository.existsById(1));

    }
}