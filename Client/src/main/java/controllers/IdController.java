package controllers;

import java.util.ArrayList;
import java.util.HashMap;

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
            return objectMapper.readValue(result, new TypeReference<ArrayList<Id>>() {});
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Id postId(Id id) {
        // create json from id
        // call server, get json result Or error
        // result json to Id obj

        return null;
    }

    public Id putId(Id id) {
        return null;
    }
 
}