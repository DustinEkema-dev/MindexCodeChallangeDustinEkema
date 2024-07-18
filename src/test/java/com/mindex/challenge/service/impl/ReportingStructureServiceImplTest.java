package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ReportingStructureServiceImplTest {

    @Autowired
    ReportingStructureServiceImpl reportingStructureService;

    @Mock
    EmployeeServiceImpl employeeService;

    @Test
    public void testReportingStructureServiceTest(){
        String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        ReportingStructure expected = new ReportingStructure();
        expected.setEmployee(createEmployeeObject());
        expected.setNumberOfReports(2);
        when(employeeService.read(any())).thenReturn(createEmployeeObject());

        ReportingStructure actual = reportingStructureService.computeReport(employeeId);

        assertEquals(actual.getEmployee().getEmployeeId(),expected.getEmployee().getEmployeeId());
        assertEquals(actual.getEmployee().getDepartment(),expected.getEmployee().getDepartment());
        assertEquals(actual.getEmployee().getPosition(),expected.getEmployee().getPosition());
        assertEquals(actual.getEmployee().getFirstName(),expected.getEmployee().getFirstName());
        assertEquals(actual.getEmployee().getLastName(),expected.getEmployee().getLastName());
    }

    private Employee createEmployeeObject(){
        Employee testEmployee = new Employee();
        testEmployee.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Lennon");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Development Manager");
        return testEmployee;
    }
}
