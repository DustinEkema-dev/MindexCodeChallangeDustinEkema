package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CompensationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    CompensationService compensationService;

    @Test
    public void testCompensationCreate() throws Exception {
        String json = "{\"employeeId\":\"16a596ae-edd3-4847-99fe-c4518e82c86f\",\"salary\":1200.00,\"effectiveDate\":\"2015-03-04\"}";
        when(compensationService.create(any())).thenReturn(createCompensation());
        mockMvc.perform(post("/compensations/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                    .andExpect(status().isOk());
    }

    @Test
    public void testCompensationGet() throws Exception {
        when(compensationService.read(any())).thenReturn(createCompensation());
        mockMvc.perform(get("/compensations/16a596ae-edd3-4847-99fe-c4518e82c86f")).andExpect(status().isOk());

    }

    private Compensation createCompensation(){
        Compensation compensation = new Compensation();
        compensation.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        compensation.setSalary(100.00);
        compensation.setEffectiveDate(LocalDate.of(2023, 2,22));
        return compensation;
    }


}
