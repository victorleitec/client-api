package br.com.victorleitecosta.customers.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ProvidedServiceDTO {
    @NotEmpty(message = "{field.description.required}")
    private String description;

    @NotEmpty(message = "{field.value.required}")
    private String value;

    @NotEmpty(message = "{field.date.required}")
    private String date;

    @NotNull(message = "{field.client.required}")
    private Integer clientId;
}
