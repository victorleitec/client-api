package br.com.victorleitecosta.customers.rest;

import br.com.victorleitecosta.customers.exception.RegisteredUserException;
import br.com.victorleitecosta.customers.model.entity.User;
import br.com.victorleitecosta.customers.model.repository.UserRepository;
import br.com.victorleitecosta.customers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public void save(@RequestBody @Valid User user) {
        try{
            service.save(user);
        }catch (RegisteredUserException e) {
            throw new ResponseStatusException(BAD_REQUEST, e.getMessage());
        }
    }
}
