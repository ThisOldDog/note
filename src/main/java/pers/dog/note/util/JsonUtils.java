package pers.dog.note.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pers.dog.note.component.logger.Logger;
import pers.dog.note.component.logger.factory.LoggerFactory;

import java.util.function.BiFunction;

/**
 * @author 废柴 2021/2/26 17:24
 */
public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private JsonUtils() {
    }

    public static String parserString(Object value) {
        return parserString(value, (v, e) -> {
            logger.error("An error occurred during Json serialization, and the data source class is %s.", e, v.getClass());
            return null;
        });
    }

    public static String parserString(Object o, BiFunction<Object, JsonProcessingException, String> callbackOnException) {
        try {
            return OBJECT_MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return callbackOnException.apply(o, e);
        }
    }

    public static <T> T valueOf(String json, Class<T> jsonType) {
        return valueOf(json, jsonType, (j, e) -> {
            logger.error("An error occurred during Json deserialization, and the data source json is %s.", e, j);
            return null;
        });
    }

    public static <T> T valueOf(String json, Class<T> jsonType, BiFunction<String, JsonProcessingException, T> callbackOnException) {
        try {
            return OBJECT_MAPPER.readValue(json, jsonType);
        } catch (JsonProcessingException e) {
            return callbackOnException.apply(json, e);
        }
    }
}
