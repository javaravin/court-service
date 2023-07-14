package com.court.exception;

import com.court.utils.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CourtExceptionHandler {
    final static Logger logger = LoggerFactory.getLogger(CourtGlobalException.class);
    @Autowired
    private MessageSource messageSource;
    @ExceptionHandler(value = CourtGlobalException.class)
    public ResponseEntity exception(CourtGlobalException courtGlobalException){
        logger.error("Exception occurred due to ",courtGlobalException);
        String message= Messages.getMessageWithDefault(this.messageSource, "ERROR:500", "Internal server error");
        return ResponseEntity.internalServerError().body(message);
    }

    @ExceptionHandler(value = CourtEntityNotFoundException.class)
    public ResponseEntity exception(CourtEntityNotFoundException courtEntityNotFoundException){
        logger.error("Exception occurred due to ",courtEntityNotFoundException);
        String message= Messages.getMessageWithDefault(this.messageSource, courtEntityNotFoundException.getMessageCode(), "Entity not found");
        return ResponseEntity.internalServerError().body(message);
    }
}
