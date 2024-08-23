package rw.app.urugendo.day.Exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Getter
@Setter
public class BadRequestException extends Exception{
    private String errorCode;
    private LocalDateTime timeStamp;
    public BadRequestException(String message){
        super(message);
        this.errorCode = errorCode;
        this.timeStamp = LocalDateTime.now();
    }
}
