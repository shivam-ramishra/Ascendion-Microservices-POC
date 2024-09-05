package base.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorMessages {
    private int errorCode;
    private String errorMessage;
}
