package base.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatusCode;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class ApplicationException extends RuntimeException {

    private final HttpStatusCode statusCode;
    private final String message;

}
