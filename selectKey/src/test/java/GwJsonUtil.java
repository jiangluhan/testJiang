import com.google.common.reflect.TypeToken;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GwJsonUtil {
    public GwJsonUtil() {
    }

    public static String toJson(Object object, final String formatter) {
        if (object == null) {
            return "";
        } else {
            Gson gson = (new GsonBuilder()).registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
                    return new JsonPrimitive(localDateTime.format(DateTimeFormatter.ofPattern(formatter)));
                }
            }).serializeNulls().create();
            return gson.toJson(object);
        }
    }

    public static String toJson(Object object) {
        if (object == null) {
            return "";
        } else {
            Gson gson = (new GsonBuilder()).create();
            return gson.toJson(object);
        }
    }

    public static <T> T stringToObject(String string, Class<T> clazz, final String formatter) {
        Gson gson = (new GsonBuilder()).registerTypeAdapter(LocalDateTime.class, new JsonDeserializer() {
            public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern(formatter));
            }
        }).create();
        return gson.fromJson(string, clazz);
    }

    public static <T> T stringToObject(String string, Class<T> clazz) {
        Gson gson = (new GsonBuilder()).serializeNulls().create();
        return gson.fromJson(string, clazz);
    }

    public static <T> List<T> gsonToList(String jsonStr, Class<T> cls) {
        Gson gson = (new GsonBuilder()).serializeNulls().create();
        return (List)gson.fromJson(jsonStr, (new TypeToken<List<T>>() {
        }).getType());
    }

    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList();
        JsonArray array = (new JsonParser()).parse(json).getAsJsonArray();
        Iterator var5 = array.iterator();

        while(var5.hasNext()) {
            JsonElement elem = (JsonElement)var5.next();
            list.add(gson.fromJson(elem, cls));
        }

        return list;
    }
}
