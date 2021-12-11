package controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Id;
import models.Message;

public class MessageController {
    private ObjectMapper objectMapper = new ObjectMapper();

    private HashSet<Message> messagesSeen;
    // why a HashSet??

    public ArrayList<Message> getMessages() {
        String result = HTTPController.getUrl("/messages");
        try {
            return objectMapper.readValue(result, new TypeReference<ArrayList<Message>>() {});
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ArrayList<Message> getMessagesForId(Id Id) {
        String result = HTTPController.getUrl("/ids/" + Id.getGithub() + "/messages/");
        try {
            return objectMapper.readValue(result, new TypeReference<ArrayList<Message>>() {});
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Message getMessageForSequence(String seq) {
        return null;
    }
    public ArrayList<Message> getMessagesFromFriend(Id myId, Id friendId) {
        return null;
    }

    public Message postMessage(Id myId, Id toId, Message msg) {
        return null;
    }
 
}