package rw.app.urugendo.day.models.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse <T> {
    private T data;
    private String message;
    private String code;
    private HttpStatus status;
    private String timeStamp = LocalDateTime.now().toString();
    public ApiResponse (T data, String message,String code,HttpStatus status){
        this.data = data;
        this.message = message;
        this.code = code;
        this.status = status;
    }
}
