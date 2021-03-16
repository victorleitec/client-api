package br.com.victorleitecosta.customers.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String message) {
        this.errors = asList(message);
    }
}
