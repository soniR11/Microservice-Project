package com.prj.OrderService.exeption.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prj.OrderService.exception.ErrorResponse;
import com.prj.OrderService.exception.OrderServiceCustomException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;

public class CustomDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
            return new OrderServiceCustomException(errorResponse.getExceptionMessage(), errorResponse.getErrorCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new OrderServiceCustomException("ServiceInternalException", "SERVICE_EXCEPTION");

    }
}
