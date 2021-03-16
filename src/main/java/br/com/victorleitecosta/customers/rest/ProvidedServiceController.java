package br.com.victorleitecosta.customers.rest;

import br.com.victorleitecosta.customers.model.entity.Client;
import br.com.victorleitecosta.customers.model.entity.ProvidedService;
import br.com.victorleitecosta.customers.model.repository.ClientRepository;
import br.com.victorleitecosta.customers.model.repository.ProvidedServiceRepository;
import br.com.victorleitecosta.customers.rest.dto.ProvidedServiceDTO;
import br.com.victorleitecosta.customers.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/api/provided-services")
@RequiredArgsConstructor
public class ProvidedServiceController {

    private final ClientRepository clientRepository;
    private final ProvidedServiceRepository repository;
    private final BigDecimalConverter bigDecimalConverter;

    @PostMapping
    public ProvidedService save(@RequestBody @Valid ProvidedServiceDTO dto) {
        LocalDate date = LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer clientId = dto.getClientId();

        Client client = clientRepository
                .findById(clientId)
                .orElseThrow(() ->
                        new ResponseStatusException(BAD_REQUEST, "Cliente inexistente."));

        ProvidedService providedService = new ProvidedService();
        providedService.setDescription(dto.getDescription());
        providedService.setDate(date);
        providedService.setClient(client);
        providedService.setValue(bigDecimalConverter.converter(dto.getValue()));

        return repository.save(providedService);
    }

    @GetMapping
    public List<ProvidedService> search(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "month", required = false) Integer month
    ) {
        return repository.findByClientNameAndMonth("%" + name + "%", month);
    }
}
