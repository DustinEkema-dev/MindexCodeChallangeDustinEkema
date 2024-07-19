package com.mindex.challenge.controller;

import com.mindex.challenge.data.ApiError;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReportingStructureControllerTest {

    @Mock
    ReportingStructureService reportingStructureService;

    @InjectMocks
    ReportingStructureController reportingStructureController;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetDirectReportsController() throws Exception {
        when(reportingStructureService.computeReport(any())).thenReturn(createReportingStructure());

        mockMvc.perform(get("/direct-reports/16a596ae-edd3-4847-99fe-c4518e82c86f")).andExpect(status().isOk());
    }

    @Test
    public void testCompensationGetNotFound() {
        when(reportingStructureService.computeReport(any())).thenThrow(new RuntimeException("Error"));
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,"400","Error");
        ResponseEntity<Object> actual = reportingStructureController.read("123");
        assertEquals(apiError.getStatus(),actual.getStatusCode());
    }

    private ReportingStructure createReportingStructure(){
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");
        int numberOfReports = 2;
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(testEmployee);
        reportingStructure.setNumberOfReports(numberOfReports);
        return reportingStructure;
    }
}
