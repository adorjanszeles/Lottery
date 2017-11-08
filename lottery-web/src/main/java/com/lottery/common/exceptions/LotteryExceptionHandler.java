package com.lottery.common.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Kivételek kezelése
 */
@ControllerAdvice
public class LotteryExceptionHandler {

    protected static final Logger LOGGER = LoggerFactory.getLogger(LotteryExceptionHandler.class);

    /**
     * Validációhoz szükséges kivétel kezelés
     *
     * @param ex MethodArgumentNotValidException, ha a user rossz input adatot adott meg
     * @return ResponseEntity object, ami tartalmazza az input adatok miatt keletkezett hibákat
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();

        List<ApiFieldError> apiFieldErrors = bindingResult.getFieldErrors()
                                                          .stream()
                                                          .map(fieldError -> new ApiFieldError(fieldError.getField(),
                                                                                               fieldError.getCode(),
                                                                                               fieldError.getRejectedValue(),
                                                                                               fieldError.getDefaultMessage()))
                                                          .collect(toList());

        List<ApiGlobalError> apiGlobalErrors = bindingResult.getGlobalErrors()
                                                            .stream()
                                                            .map(globalError -> new ApiGlobalError(
                                                                    globalError.getCode()))
                                                            .collect(toList());

        LotteryExceptionHandler.LOGGER.debug("Validáció során hiba keletkezett: " + apiFieldErrors.toString());
        ApiErrorsView apiErrorsView = new ApiErrorsView(apiFieldErrors, apiGlobalErrors);

        return new ResponseEntity<>(apiErrorsView, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Globális exception kezelés
     *
     * @param ex Exception
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex) {
        LotteryExceptionHandler.LOGGER.debug("Kivétel keletkezett.", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
