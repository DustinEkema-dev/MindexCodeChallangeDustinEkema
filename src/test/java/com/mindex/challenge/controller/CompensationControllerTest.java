package com.mindex.challenge.controller;

import com.mindex.challenge.data.ApiError;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompensationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    CompensationService compensationService;

    @InjectMocks
    CompensationController compensationController;

    @Test
    public void testCompensationCreate() throws Exception {
        String json = "{\"employee\":{\"employeeId\":\"b7839309-3348-463b-a7e3-5de1c168beb3\",\"firstName\":\"Paul\",\"lastName\":\"McCartney\",\"position\":\"DeveloperI\",\"department\":\"Engineering\"},\"salary\":1200.00,\"effectiveDate\":\"2022-02-17\"}";
        when(compensationService.create(any())).thenReturn(createCompensation());
        mockMvc.perform(post("/compensations/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                    .andExpect(status().isOk());
    }

    @Test
    public void testCompensationGet() {
        when(compensationService.read(any())).thenReturn(createCompensation());
        ResponseEntity<Object> actual = compensationController.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
        assertEquals(actual.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testCompensationGetNotFound() {
        when(compensationService.read(any())).thenThrow(new RuntimeException("Error"));
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,"400","error");
        ResponseEntity<Object> compensationObj = compensationController.read("123");
        assertEquals(apiError.getStatus(),compensationObj.getStatusCode());
    }

    private Compensation createCompensation(){
        Employee testEmployee = new Employee();
        testEmployee.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        Compensation compensation = new Compensation();
        compensation.setEmployee(testEmployee);
        compensation.setSalary(100.00);
        compensation.setEffectiveDate(LocalDate.of(2023, 2,22));
        return compensation;
    }


}
