package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.springframework.stereotype.Service;

@Service
public interface CompensationService {
    Compensation create(Compensation compensation);
    Compensation read(String id);
}
