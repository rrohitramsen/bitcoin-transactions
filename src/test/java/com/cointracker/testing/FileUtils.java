package com.cointracker.testing;

import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;

import static com.cointracker.testing.ObjectMapperUtils.objectMapper;

public class FileUtils {

    private static final String BASE_PATH = "src/test/resources/json/";

    /**
     * Convert input file into json of given class type.
     * @param type
     * @param fileName
     * @param <T> class type
     * @return JSON representation of the object data from file.
     */
    public static <T> String readFileIntoJson(Class<T> type, String fileName) {

        try {
            return objectMapper().writeValueAsString(objectMapper().readValue(new File(BASE_PATH + fileName), type));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * Convert input file into json of given class type.
     * @param type
     * @param fileName
     * @param <T>TypeReference type
     * @return JSON representation of the object data from file.
     */

    public static <T> String readFileIntoJson(TypeReference<T> type, String fileName) {

        try {
            return objectMapper().writeValueAsString(objectMapper().readValue(new File(BASE_PATH + fileName), type));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * Convert Json to Object of given class type.
     * @param type
     * @param fileName
     * @param <T> class type
     * @return object of type T
     */
    public static <T> T readObjectFromJsonFile(Class<T> type, String fileName) {

        try {
            return objectMapper().readValue(new File(BASE_PATH + fileName), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert Json to Object of given TypeReference type.
     * @param type
     * @param fileName
     * @param <T> class type
     * @return object of type T
     */
    public static <T> T readObjectFromJsonFile(TypeReference<T> type, String fileName) {

        try {
            return objectMapper().readValue(new File(BASE_PATH + fileName), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
