package com.mindex.challenge.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiErrorTest {

    @Test
    public void testBuildApiErrorMultipleError(){
        List<String> errors = new ArrayList<>();
        errors.add("No such employee");
        errors.add("caused RunTimeException");
        ApiError apiSingleError = new ApiError(HttpStatus.BAD_REQUEST,"Bad Request Found!",errors);
        assertEquals(apiSingleError.getStatus(),HttpStatus.BAD_REQUEST);
        assertEquals(apiSingleError.getMessage(),"Bad Request Found!");
        assertEquals(apiSingleError.getErrors(),errors);

    }

    @Test
    public void testBuildApiErrorOneError(){
        List<String> error = new ArrayList<>();
        error.add("No such employee");
        ApiError apiSingleError = new ApiError(HttpStatus.BAD_REQUEST,"Bad Request Found!","No such employee");
        assertEquals(apiSingleError.getStatus(),HttpStatus.BAD_REQUEST);
        assertEquals(apiSingleError.getMessage(),"Bad Request Found!");
        assertEquals(apiSingleError.getErrors(),error);

    }
}
