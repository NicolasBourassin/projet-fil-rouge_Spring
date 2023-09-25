package com.example.projetfilrouge_Spring.repository.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class TicketSerializer extends JsonSerializer<Ticket> {
    @Override
    public void serialize(Ticket ticket, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", ticket.getId());
        jsonGenerator.writeStringField("date", ticket.getDate().toString());
        jsonGenerator.writeStringField("eventName", ticket.getEventName());
        jsonGenerator.writeStringField("eventType", ticket.getEventType());
        jsonGenerator.writeStringField("eventCity", ticket.getEventCity());
        jsonGenerator.writeNumberField("price", ticket.getPrice());
        jsonGenerator.writeEndObject();
    }
}
