package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    @Autowired
    CompensationServiceImpl compensationService;

    @Mock
    CompensationRepository compensationRepository;

    @Test
    public void  testCreateCompensation(){
        Compensation expected = createCompensation();
        when(compensationRepository.insert(createCompensation())).thenReturn(createCompensation());

        Compensation actual = compensationService.create(createCompensation());

        assertEquals(actual.getEmployeeId(),expected.getEmployeeId());
        assertEquals(actual.getSalary(),expected.getSalary());
        assertEquals(actual.getEffectiveDate(),expected.getEffectiveDate());
    }

    @Test
    public void testGetCompensation(){
        String employeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        Compensation expected = createCompensation();
        when(compensationRepository.findCompensationByEmployeeId(employeeId)).thenReturn(createCompensation());

        Compensation actual = compensationService.read(employeeId);

        assertEquals(actual.getEmployeeId(),expected.getEmployeeId());
        assertEquals(actual.getSalary(),expected.getSalary());
        assertEquals(actual.getEffectiveDate(),expected.getEffectiveDate());
    }

    private Compensation createCompensation(){
        Compensation compensation = new Compensation();
        compensation.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        compensation.setSalary(100.00);
        compensation.setEffectiveDate(LocalDate.of(2023, 2,22));
        return compensation;
    }
}
