package com.loeches.yugioh.DAO.Implementaciones.Adaptador;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.loeches.yugioh.Modelo.Cartas.Abstractas.ACarta;

import java.lang.reflect.Type;

// ESTO ES PARA GUARDAR Y CARGAR LAS CARTAS EN UN JSON. LAS CLASES ABSTRACTAS NO SE PUEDEN INSTANCIAR
public class CartaTypeAdapter implements JsonSerializer<ACarta>, JsonDeserializer<ACarta> {
    @Override
    public JsonElement serialize(ACarta src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", src.getClass().getName());
        jsonObject.add("data", context.serialize(src));
        return jsonObject;
    }

    @Override
    public ACarta deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();

            // Verificar si el campo "type" existe y no es nulo
            if (jsonObject.has("type") && !jsonObject.get("type").isJsonNull()) {
                String type = jsonObject.get("type").getAsString();

                // Verificar si el campo "data" existe y no es nulo
                if (jsonObject.has("data") && !jsonObject.get("data").isJsonNull()) {
                    JsonElement element = jsonObject.get("data");

                    try {
                        Class<?> clazz = Class.forName(type);
                        return context.deserialize(element, clazz);
                    } catch (ClassNotFoundException e) {
                        throw new JsonParseException("Unknown element type: " + type, e);
                    }
                } else {
                    throw new JsonParseException("Missing or null 'data' field in JSON for type: " + type);
                }
            } else {
                throw new JsonParseException("Missing or null 'type' field in JSON");
            }
        } else {
            throw new JsonParseException("Expected JsonObject, but got something else: " + json.getClass());
        }
    }
}