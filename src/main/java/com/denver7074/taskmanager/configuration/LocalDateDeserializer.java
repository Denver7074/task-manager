package com.denver7074.taskmanager.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;

import static com.denver7074.taskmanager.utils.Utils.date;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {
    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String o = jsonParser.readValueAs(String.class);
        return date(o);
    }

}
