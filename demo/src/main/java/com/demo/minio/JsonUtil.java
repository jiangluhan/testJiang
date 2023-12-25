package com.demo.minio;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wang
 * @des 基于 Gson 生成的工具类
 * @date 2021/7/29 9:59
 */
public class JsonUtil {

    /**
     * 对象转换成Json
     *
     * @param object    对象
     * @param formatter 时间格式
     * @return
     */
    public static String toJson(Object object, String formatter) {
        if (object == null) {
            return "";
        }
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                    @Override
                    public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
                        return new JsonPrimitive(localDateTime.format(DateTimeFormatter.ofPattern(formatter)));
                    }
                })
                .serializeNulls()
                .create();
        return gson.toJson(object);
    }

    /**
     * 对象转换成Json
     *
     * @param object 对象
     * @return
     */
    public static String toJson(Object object) {
        if (object == null) {
            return "";
        }
        Gson gson = new GsonBuilder().create();
        return gson.toJson(object);
    }

    /**
     * 字符串转类
     *
     * @param string 字符串
     * @param clazz  类
     * @param <T>    t
     * @return t
     */
    public static <T> T stringToObject(String string, Class<T> clazz, String formatter) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer() {
                    @Override
                    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern(formatter));
                    }
                })
                .create();
        return gson.fromJson(string, clazz);
    }

    /**
     * 字符串转类
     *
     * @param string 字符串
     * @param clazz  类
     * @return t
     */
    public static <T> T stringToObject(String string, Class<T> clazz) {
        Gson gson = new GsonBuilder().serializeNulls()
                .create();
        return gson.fromJson(string, clazz);
    }

    /**
     * 转成list
     * 泛型在编译期类型被擦除导致报错
     *
     * @param jsonStr
     * @param cls
     * @return
     */
    public static <T> List<T> gsonToList(String jsonStr, Class<T> cls) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.fromJson(jsonStr, new TypeToken<List<T>>() {
        }.getType());
    }

    /**
     * json字符串转成list
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, cls));
        }
        return list;
    }
}
