package views;

import models.Id;
import models.Message;

public class MessageTextView {
    private Message msg;
    public MessageTextView(Message msgToDisplay) {
        this.msg = msgToDisplay;
    }

    public String toString() {
        return this.msg.toString();
    } 
}