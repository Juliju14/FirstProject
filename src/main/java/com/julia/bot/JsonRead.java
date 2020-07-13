package com.julia.bot;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonRead {

    public static List<NameList> jsonRead(String langPath) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {

            return objectMapper.readValue(new File(langPath), new TypeReference<List<NameList>>() {
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}



