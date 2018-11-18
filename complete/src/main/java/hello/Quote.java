package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public final class Quote {

    @NotNull(message = "Type must not be null")
    @Size(min = 3, max = 10)
    @Pattern(regexp = "TYPE1|TYPE2", message = "Type must be one of TYPE1, TYPE2")
    private String type;

    @NotNull(message = "Value must not be null")
    @Valid
    private Value value;

    private String cron;

    public Quote() {
        // empty constructor
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "type='" + type + '\'' +
                ", value=" + value +
                ", cron='" + cron + '\'' +
                '}';
    }
}
