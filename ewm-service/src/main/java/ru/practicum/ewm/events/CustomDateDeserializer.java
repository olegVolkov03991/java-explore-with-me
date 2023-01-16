package ru.practicum.ewm.events;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonComponent // чтобы спринг использовал именно этот класс
public class CustomDateDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    //переопредедние абстрактного метода deserialize класса JsonDeserializer
    public LocalDateTime deserialize(JsonParser p,  // р - переданная клиентом строка
                                     DeserializationContext deserializationContext) throws IOException {
        String date = p.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }
}
