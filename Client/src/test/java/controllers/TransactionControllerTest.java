package controllers;

import models.Id;
import models.Message;

import java.util.List;

import static org.junit.Assert.*;

public class TransactionControllerTest {

    @org.junit.Test
    public void testGetIds() {
        TransactionController tctrl = new TransactionController(null, null);
        String result = tctrl.getIdsString();
        System.out.println(result);
    }

    @org.junit.Test
    public void testGetIdsObjs() {
        TransactionController tctrl = new TransactionController(null, null);
        List<Id> list = tctrl.getIds();
        System.out.println(list);
    }

    @org.junit.Test
    public void testGetMessages() {
        TransactionController tctrl = new TransactionController(null, null);
        String result = tctrl.getMessagesString();
        System.out.println(result);
    }

    @org.junit.Test
    public void testGetMessageObjs() {
        TransactionController tctrl = new TransactionController(null, null);
        List<Message> list = tctrl.getMessages();
        System.out.println(list);
    }

    @org.junit.Test
    public void testPostId() {
        TransactionController tctrl = new TransactionController(null, null);
        Id result = tctrl.postId("Bilbo", "TheBilboBaggins");
        System.out.println(result.toString());
    }

    @org.junit.Test
    public void testPostMessage() {
        TransactionController tctrl = new TransactionController(null, null);
        Message result = tctrl.postMessage("torvalds", "xt0fer", "Can you hear me now?!");
        System.out.println(result.toString());
    }


//    @org.junit.Test
//    public void postId() {
//    }
}
