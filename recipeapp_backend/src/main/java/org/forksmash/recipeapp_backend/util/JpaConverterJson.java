package org.forksmash.recipeapp_backend.util;

import java.io.IOException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Source: https://stackoverflow.com/questions/25738569/how-to-map-a-map-json-column-to-java-object-with-jpa

@Converter(autoApply = true)
public class JpaConverterJson implements AttributeConverter<Object, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public String convertToDatabaseColumn(Object meta) {
        try {
            return objectMapper.writeValueAsString(meta);
          } catch (JsonProcessingException e) {
            return null;
          }
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
      // System.out.println("****** DB DATA ********\n\n");
      // System.out.println(dbData);
      // System.out.println("\n\n******         ********\n\n");
      try {
        return objectMapper.readValue(dbData, Object.class);
      } catch (IOException e) {
        return null;
      }
    }
}
