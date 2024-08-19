package rw.app.urugendo.day.Exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
@Getter
@Setter
public class UnauthorizedException extends Exception{
    private LocalDateTime timeStamp;
   public UnauthorizedException(String message){
       super(message);
   }
}
