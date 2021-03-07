package com.gorlov;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ConverterJSON {

    private final static String baseFile = "resources/user.json";

    public static void toJSON(JsonData jsonData) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(baseFile), jsonData);
    }

    public static JsonData toJavaObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(baseFile), JsonData.class);
    }

}
