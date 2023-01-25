package tacos.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "taco.orders")
@Data
@Validated
public class OrderProps {
    
    private static final String VALID_MESSAGE = "must be between 5 and 25";

    @Min(value = 5, message = VALID_MESSAGE)
    @Max(value = 25, message = VALID_MESSAGE)
    private int pageSize = 20;
}
