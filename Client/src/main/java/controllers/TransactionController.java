package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Id;
import models.Message;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private HttpClient client = HttpClient.newHttpClient();


    public TransactionController() {

    }

    public String getIdsString() { //public List<Id> getIds() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(rootURL + "/ids"))
                    .GET()
                    .build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return "" + response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Id> getIds() {
        String idsstring = this.getIdsString();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Id>>() {
        }.getType();
        List<Id> idList = new Gson().fromJson(idsstring, listType);
        return idList;
    }

    public List<Message> getMessages() {
        String msgstring = this.getMessagesString();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Message>>() {
        }.getType();
        List<Message> msgList = new Gson().fromJson(msgstring, listType);
        return msgList;
    }

    public String getMessagesString() { //public List<Id> getIds() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(rootURL + "/messages"))
                    .GET()
                    .build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return "" + response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Id postId(String name, String ghname) {
        try {
            Id newID = new Id(name, ghname);
            Gson gson = new Gson();

            String toSendId = gson.toJson(newID);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(rootURL + "/ids"))
                    .POST(HttpRequest.BodyPublishers.ofString(toSendId))
                    .setHeader("Content-type", "application/json")
                    .build();
            HttpResponse response = null;
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("After POST " + response.body());

                newID = gson.fromJson("" + response.body(), Id.class);
                return newID;
            } else {
                System.out.println("Failure of POST" + response.statusCode());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return new Id("failllll!!!", "codeNoWork!!!!");
    }

    public Message postMessage(String toHandle, String fromHandle, String messageBody) {
        // well no, you do need to do *some* coding.
        try {
            Message newMsg = new Message(messageBody, fromHandle, toHandle);
            Gson gson = new Gson();

            String toSendId = gson.toJson(newMsg);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(rootURL + "/ids/" + fromHandle + "/messages"))
                    .POST(HttpRequest.BodyPublishers.ofString(toSendId))
                    .setHeader("Content-type", "application/json")
                    .build();
            HttpResponse response = null;
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("After POST " + response.body());

                newMsg = gson.fromJson("" + response.body(), Message.class);
                return newMsg;
            } else {
                System.out.println("Failure of POST" + response.statusCode());
                System.out.println("Error: " + response.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return new Message("huh?", "what?", "zyzzy!!");
    }

    public ArrayList<Message> getMessageForId(String idName) {
        ArrayList<Message> allMessages = (ArrayList<Message>) getMessages();
        int arrLIstIdx = 0 ;
        while (arrLIstIdx < allMessages.size()) {
            Message temp = allMessages.get(arrLIstIdx);
            if (!temp.getToid().equals(idName)) {
                allMessages.remove(temp);
            }else {
                arrLIstIdx++;
            }
        }
        return allMessages;}
  /*      try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(new URI(rootURL + "/ids" + idName) + "/messages"))
                    .GET()
                    .build();

            HttpRequest response = client.send(request, HttpResponse.BodyHandlers.ofString()).request();

            Type listType = new TypeToken<List<Message>>() {
            }.getType();
            return gson.fromJSON(response.body(), listType);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }*/
}
