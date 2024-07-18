package com.mindex.challenge.controller;

import com.mindex.challenge.data.ApiError;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingStructureController {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    ReportingStructureService reportingStructureService;

    @GetMapping("/direct-reports/{id}")
    public ResponseEntity<Object> read(@PathVariable String id) {
        LOG.debug("Received employee direct report request for id [{}]", id);
        ReportingStructure reportingStructure;
       try{
           reportingStructure = reportingStructureService.computeReport(id);
       }
       catch(RuntimeException e){
           LOG.error("No employee with id [{}] found!",id);
           ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,"No employee with id found!","400 Bad Request!");
           return ResponseEntity.badRequest().body(apiError);
       }
        return ResponseEntity.ok().body(reportingStructure);
    }

}
