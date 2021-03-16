package br.com.victorleitecosta.customers.rest;

import br.com.victorleitecosta.customers.model.entity.Client;
import br.com.victorleitecosta.customers.model.repository.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class ClientController {

    private final ClientRepository repository;

    @GetMapping
    public List<Client> getAll() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Client saveClient(@RequestBody @Valid Client client) {
        return repository.save(client);
    }

    @GetMapping("{id}")
    public Client findClientById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Cliente não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteClient(@PathVariable Integer id) {
        repository.findById(id)
                .map(client -> {
                    repository.delete(client);
                    return Void.TYPE;
                })
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateClient(@RequestBody Client updatedClient, @PathVariable Integer id) {
        repository.findById(id)
                .map(client -> {
                    updatedClient.setId(client.getId());
                    client.setName(updatedClient.getName());
                    client.setCpf(updatedClient.getCpf());
                    return repository.save(updatedClient);
                })
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Cliente não encontrado"));
    }
}
