package rw.app.urugendo.day.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    private LocalDateTime timeStamp;
    public ResourceNotFoundException(String message){
        super(message);
        this.timeStamp = LocalDateTime.now();
    }


}
