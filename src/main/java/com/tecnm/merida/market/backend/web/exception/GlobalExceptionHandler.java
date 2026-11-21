package com.tecnm.merida.market.backend.web.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Llave foránea inválida o registro con dependencias: la transacción ya hizo rollback
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrity(DataIntegrityViolationException e) {
        String detail = e.getMostSpecificCause().getMessage();
        return new ResponseEntity<>(Map.of(
                "error", "Integridad de datos: el registro referencia (o es referenciado por) otro que no existe",
                "detalle", detail == null ? "" : detail), HttpStatus.CONFLICT);
    }
}
