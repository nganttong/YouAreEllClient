package controllers;

import models.Id;
import models.Message;

import java.util.List;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private MessageController msgCtrl;
    private IdController idCtrl;

    public TransactionController(MessageController m, IdController j) {
        this.idCtrl = j;
        this.msgCtrl = m;
    }

    public List<Id> getIds() {
        return idCtrl.getIds();
    }

    public List<Message> getMessages() {
        return msgCtrl.getMessages();
    }

    public List<Message> getMessageForId(String githubId) {
        return msgCtrl.getMessagesForId(githubId);
    }

    public String postId(String idtoRegister, String githubName) {
        Id tempId = new Id(idtoRegister, githubName);
        tempId = idCtrl.postId(tempId);
        if(tempId == null) {
            return ("Id not registered, something broke.");
        }
        return ("Id registered.");
    }

    public Message postMessage(String myId, String toId, String message) {
        Message messageToSend = new Message(message, myId, toId);
        return msgCtrl.postMessage(messageToSend);
    }

    public Message postMessage(String myId, String message) {
        Message messageToSend = new Message(message, myId);
        return msgCtrl.postMessage(messageToSend);
    }

    public String putId (String updatedName) {
        Id updatedCredentials = new Id(idCtrl.myId.getUid(), updatedName, idCtrl.myId.getGithub());
        updatedCredentials = idCtrl.putId(updatedCredentials);
        if(updatedCredentials == null) {
            return ("Name not updated, something broke.");
        }
        return ("Name updated.");
    }
}
