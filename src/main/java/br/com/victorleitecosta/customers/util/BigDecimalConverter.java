package br.com.victorleitecosta.customers.util;

import org.springframework.stereotype.Component;

import javax.swing.plaf.basic.BasicViewportUI;
import java.math.BigDecimal;

@Component
public class BigDecimalConverter {

    public BigDecimal converter(String value) {
        if (value == null) {
            return null;
        }

        value = value.replace(".", "").replace(",", ".");
        return new BigDecimal(value);
    }
}
