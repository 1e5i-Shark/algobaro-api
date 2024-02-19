package ei.algobaroapi.global.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.List;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        try {
            return objectMapper.writeValueAsString(strings);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e); // TODO: 예외 추가
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String data) {
        try {
            return objectMapper.readValue(data, List.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e); // TODO: 예외 추가
        }
    }
}
