package com.sum.poc.server.aspects;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gwt.dev.util.collect.HashSet;
import com.sum.poc.server.exception.BusinessException;
import com.sum.poc.server.exception.BusinessValidationException;
import com.sum.poc.server.response.Errors;
import com.sum.poc.server.response.Field;
import com.sum.poc.server.response.Message;
import com.sum.poc.server.response.Response;

@ControllerAdvice
public class GlobalBusinessExceptionHandler {

@ExceptionHandler(BusinessValidationException.class)
public @ResponseBody
Response<Errors> handleCustomException(BusinessValidationException ex) {

    Set<ConstraintViolation<Object>> constraintViolations = ex
        .getConstraintViolations();
        Response<Errors> response = new Response<Errors>();
            Errors errors = new Errors();
            Set<Field> erroSet = new HashSet<Field>();
                for (ConstraintViolation<Object> constraintViolation : constraintViolations) {

                    Field field = new Field();
                    field.setId(constraintViolation.getPropertyPath() + "");
                    field.setMsg(constraintViolation.getMessage());
                    erroSet.add(field);
                    }
                    errors.setField(erroSet);
                    response.setT(errors);
                    return response;
                    }

                    @ExceptionHandler(BusinessException.class)
                    public @ResponseBody
                    Response<Message> handleHrException(BusinessException ex) {

                        Response<Message> response = new Response<Message>();
                            Message message = new Message();
                            message.setText(ex.getMessage());
                            response.setMessage(message);
                            return response;
                            }
                            }
