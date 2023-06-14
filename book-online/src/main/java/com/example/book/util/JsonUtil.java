//package com.example.book.util;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class JsonUtil {
//    public static final ObjectMapper jsonMapper = new ObjectMapper();
//
//    public static String getJsonString(Object obj){
//        try {
//            return jsonMapper.writer().writeValueAsString(obj);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static Object jsonConvertObj(String jsonStr,Class c){
//        try {
//            return jsonMapper.readerFor(c).readValue(jsonStr);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}
