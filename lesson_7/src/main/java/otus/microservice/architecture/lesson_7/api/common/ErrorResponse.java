package otus.microservice.architecture.lesson_7.api.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

/**
 * The response with an error should be returned by the application if an exception occurs
 * as a result of the request execution.
 *
 * <p>If an exception occurs as a result of executing the request, the response will be:
 *
 * <pre>{@code
 *  {
 *      "code"    : "Code to identify the cause of the error",
 *      "message" : "Handled exception information message"
 *  }
 * }
 * </pre>
 *
 * @author Dmitry Prigozhaev
 * on 23.10.2022
 */
@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ErrorResponse extends AbstractResponse {

  private final Integer code;
  private final String message;

  /**
   * Constructor for creating a response object for a failed request.
   *
   * @param code    code to identify the cause of the error
   * @param message a message to be sent to the client containing information
   *                about why the request failed
   */
  public ErrorResponse(Integer code, String message) {
    this.code = code;
    this.message = message;
  }
}
