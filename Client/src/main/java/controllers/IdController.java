package controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;

public class IdController {
    private HashMap<String, Id> allIds;
    private ObjectMapper objectMapper = new ObjectMapper();
    Id myId;

    public ArrayList<Id> getIds() {
        String result = HTTPController.getUrl("/ids");
        try {
            ArrayList<Id> listOfIds = objectMapper.readValue(result, new TypeReference<ArrayList<Id>>() {});
            listOfIds.sort(Comparator.comparing(Id::getName));
            return listOfIds;
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Id postId(Id id) {
        String body = "";
        String result = "";
        try {
            body = objectMapper.writeValueAsString(id);
            result = HTTPController.postURL("/ids", body);
            return objectMapper.readValue(result, Id.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Id putId(Id id) {
        String body = "";
        String result = "";
        try {
            body = objectMapper.writeValueAsString(id);
            result = HTTPController.putURL("/ids", body);
            return objectMapper.readValue(result, Id.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
 
}