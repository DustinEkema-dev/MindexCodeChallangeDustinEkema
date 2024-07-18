package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;
import org.springframework.http.ResponseEntity;

public interface ReportingStructureService {
    ReportingStructure computeReport(String id);
}
