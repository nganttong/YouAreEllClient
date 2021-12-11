package controllers;

import models.Id;
import models.Message;
import sun.misc.resources.Messages;

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

    public String postId(String idtoRegister, String githubName) {
        Id tempId = new Id(idtoRegister, githubName);
        tempId = idCtrl.postId(tempId);
        if(tempId == null) {
            return ("Id not registered, something broke.");
        }
        return ("Id registered.");
    }
}
