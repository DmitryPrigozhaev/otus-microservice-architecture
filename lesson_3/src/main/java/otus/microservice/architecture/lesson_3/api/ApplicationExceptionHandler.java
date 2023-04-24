package otus.microservice.architecture.lesson_3.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import otus.microservice.architecture.lesson_3.api.common.AbstractResponse;
import otus.microservice.architecture.lesson_3.api.common.ErrorResponse;
import otus.microservice.architecture.lesson_3.api.common.ResponseBuilder;
import otus.microservice.architecture.lesson_3.exception.UserNotFoundException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * The global application exception handler.
 *
 * @author Dmitry Prigozhaev
 * on 23.10.2022
 */
@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ApplicationExceptionHandler {

  private static final String LOG_PATTERN_FULL = "[anonymous] by request [{} {}] triggered an {}: {}";
  private static final String LOG_PATTERN_CROP = "[anonymous] by request [{} {}] triggered an {}";

  /**
   * The method handles all user account exceptions.
   */
  @ExceptionHandler(value = UserNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleException(UserNotFoundException e,
                                                       WebRequest request) {
    String message = e.getMessage(); // i18n in the future

    writeLog(e, request, false);

    return handleExceptionInternal(
      NOT_FOUND,
      ResponseBuilder.error(0, message)
    );
  }

  /**
   * Write exception log.
   *
   * <p>Exception logging configuration method (customize as needed).
   *
   * <p>Depending on the input argument {@code withStackTrace}, the log is enriched
   * with a stack trace. In case the reason for the exception is obvious, it is
   * necessary to transmit the {@code false} value to avoid redundant logging.
   *
   * <p>Regardless of the passed parameter, if debug mode is enabled for this
   * handler, a trace will be written to the log for any exceptions.
   *
   * @param ex             the original exception
   * @param request        the current request
   * @param withStackTrace determines whether to write a stack trace
   */
  protected void writeLog(Exception ex, WebRequest request, boolean withStackTrace) {
    if (withStackTrace || log.isDebugEnabled()) {
      log.error(LOG_PATTERN_CROP,
        ((ServletWebRequest) request).getRequest().getMethod(),
        ((ServletWebRequest) request).getRequest().getRequestURI(),
        ex.getClass().getCanonicalName(),
        ex
      );
    } else {
      log.error(LOG_PATTERN_FULL,
        ((ServletWebRequest) request).getRequest().getMethod(),
        ((ServletWebRequest) request).getRequest().getRequestURI(),
        ex.getClass().getCanonicalName(),
        ex.getMessage()
      );
    }
  }

  /**
   * A single place to customize the response body of all exception types.
   *
   * @param status the response status
   * @param body   the body of the response (extended {@link AbstractResponse})
   */
  protected <T extends AbstractResponse> ResponseEntity<T> handleExceptionInternal(HttpStatus status,
                                                                                   @Nullable T body) {
    return ResponseEntity.status(status).body(body);
  }
}