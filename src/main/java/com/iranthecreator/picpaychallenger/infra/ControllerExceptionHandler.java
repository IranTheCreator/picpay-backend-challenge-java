package com.iranthecreator.picpaychallenger.infra;

import com.iranthecreator.picpaychallenger.dto.ExceptionsDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception) {
        ExceptionsDTO exceptionsDTO = new ExceptionsDTO("user exists", "400");
        return ResponseEntity.badRequest().body(exceptionsDTO);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threatNotFound(EntityNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity theatGeneralExcepts(Exception exception){
        ExceptionsDTO exceptionsDTO = new ExceptionsDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionsDTO);
    }
}
