package com.mindex.challenge.controller;

import com.mindex.challenge.data.ApiError;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compensations")
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);


    @Autowired
    CompensationService compensationService;

    @PostMapping("/")
    public Compensation create(@RequestBody Compensation compensation) {
        LOG.debug("Received employee compensation create request for [{}]", compensation);

        return compensationService.create(compensation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> read(@PathVariable String id) {
        LOG.debug("Received employee compensation request for id [{}]", id);
        Compensation compensation;
        try{
            compensation = compensationService.read(id);
        }
        catch(RuntimeException e){
            LOG.error("No employee with id [{}] found!",id);
            ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST,"No employee with id found!","400 Bad Request!");
            return ResponseEntity.badRequest().body(apiError);
        }
        return ResponseEntity.ok(compensation);
    }

}
