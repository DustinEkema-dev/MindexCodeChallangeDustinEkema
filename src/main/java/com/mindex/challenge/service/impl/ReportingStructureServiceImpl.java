package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {

    @Autowired
    EmployeeService employeeService;

    @Override
    public ReportingStructure computeReport(String id){
        ArrayList<Employee> employees = new ArrayList<>();
        Employee employee = employeeService.read(id);
        employees.add(employee);

        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setEmployee(employee);
        reportingStructure.setNumberOfReports(calculateDirectReporters(employees,0));
        return  reportingStructure;
    }

    private int calculateDirectReporters(ArrayList<Employee>employees, int totalReporters){
        Employee directReporter = employeeService.read(employees.get(0).getEmployeeId());
        if (directReporter.getDirectReports() != null) {
            employees.addAll(directReporter.getDirectReports());
            totalReporters = totalReporters + directReporter.getDirectReports().size();
        }
        employees.remove(0);
        if(employees.isEmpty()){
            return totalReporters;
        }
        return calculateDirectReporters(employees,totalReporters);
    }
}
