package controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    public ArrayList<Message> getMessagesForId(String id) {
        String result = HTTPController.getUrl("/ids/" + id + "/messages");
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

    public Message postMessage(Message messageToSend) {
        try {
            String messageToSendAsString = objectMapper.writeValueAsString(messageToSend);
            String result = HTTPController.postURL("/ids/" + messageToSend.getFromId() + "/messages", messageToSendAsString);
            return objectMapper.readValue(result, Message.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
 
}