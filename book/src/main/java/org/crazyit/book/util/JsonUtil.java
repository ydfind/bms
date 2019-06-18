package org.crazyit.book.util;

import java.util.List;

import org.crazyit.book.entity.SaleItem;
import org.crazyit.book.entity.StoreItem;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    /**
     * 将JSON转换为集合
     */
    public static List fromJson(String json, Class itemClass) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JavaType javaType = mapper.getTypeFactory()
                    .constructParametricType(List.class, itemClass);
            return mapper.readValue(json, javaType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
