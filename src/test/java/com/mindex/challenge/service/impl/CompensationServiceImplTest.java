package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompensationServiceImplTest {


    @Mock
    CompensationRepository compensationRepository;

    @InjectMocks
    CompensationServiceImpl compensationService;

    @Test
    public void  testCreateCompensation(){
        Compensation expected = createCompensation();
        when(compensationRepository.insert(createCompensation())).thenReturn(createCompensation());

        Compensation actual = compensationService.create(createCompensation());

        assertEquals(actual.getEmployee().getEmployeeId(),expected.getEmployee().getEmployeeId());
        assertEquals(actual.getEmployee().getFirstName(),expected.getEmployee().getFirstName());
        assertEquals(actual.getEmployee().getLastName(),expected.getEmployee().getLastName());
        assertEquals(actual.getEmployee().getPosition(),expected.getEmployee().getPosition());
        assertEquals(actual.getEmployee().getDepartment(),expected.getEmployee().getDepartment());
        assertEquals(actual.getEmployee().getDirectReports(),expected.getEmployee().getDirectReports());
        assertEquals(actual.getSalary(),expected.getSalary());
        assertEquals(actual.getEffectiveDate(),expected.getEffectiveDate());
    }

    @Test
    public void testGetCompensation(){
        String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        Compensation expected = createCompensation();
        when(compensationRepository.findCompensationByEmployeeEmployeeId(employeeId)).thenReturn(createCompensation());

        Compensation actual = compensationService.read(employeeId);

        assertEquals(actual.getEmployee().getEmployeeId(),expected.getEmployee().getEmployeeId());
        assertEquals(actual.getEmployee().getFirstName(),expected.getEmployee().getFirstName());
        assertEquals(actual.getEmployee().getLastName(),expected.getEmployee().getLastName());
        assertEquals(actual.getEmployee().getPosition(),expected.getEmployee().getPosition());
        assertEquals(actual.getEmployee().getDepartment(),expected.getEmployee().getDepartment());
        assertEquals(actual.getEmployee().getDirectReports(),expected.getEmployee().getDirectReports());
        assertEquals(actual.getSalary(),expected.getSalary());
        assertEquals(actual.getEffectiveDate(),expected.getEffectiveDate());
    }

    @Test(expected = RuntimeException.class)
    public void testGetCompensationEmptyCompensation(){
        Compensation compensation = new Compensation();
        String testEmployeeId = "123";
        assertEquals(compensation,compensationService.read(testEmployeeId));
    }

    private Compensation createCompensation(){
        Compensation compensation = new Compensation();
        Employee testEmployee = new Employee();
        testEmployee.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        compensation.setEmployee(testEmployee);
        compensation.setSalary(100.00);
        compensation.setEffectiveDate(LocalDate.of(2023, 2,22));
        return compensation;
    }
}
