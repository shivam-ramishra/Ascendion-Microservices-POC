package base.exception;

import lombok.*;

@AllArgsConstructor@NoArgsConstructor
@Getter@Setter
@ToString
public class ErrorMessages {
    private int errorCode;
    private String errorMessage;
}
