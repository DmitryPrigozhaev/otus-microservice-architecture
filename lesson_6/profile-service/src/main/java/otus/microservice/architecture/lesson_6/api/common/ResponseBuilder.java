package otus.microservice.architecture.lesson_6.api.common;

/**
 * Utility class for building a JSON application response.
 *
 * @author Dmitry Prigozhaev
 * on 23.10.2022
 */
public final class ResponseBuilder {

  /**
   * Utility class can't be instantiated.
   */
  private ResponseBuilder() {
    throw new IllegalStateException("Utility class");
  }

  /**
   * Method for constructing an error response with a reason message
   * and error code.
   *
   * @param code    the status code
   * @param message the message with the reason for the request failure
   * @return the {@link ErrorResponse}
   */
  public static ErrorResponse error(Integer code, String message) {
    return new ErrorResponse(code, message);
  }
}
