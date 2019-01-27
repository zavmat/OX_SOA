package me.mzavarzin.escience;

import com.google.gson.reflect.TypeToken;
import com.microsoft.azure.servicebus.*;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import static java.nio.charset.StandardCharsets.*;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;

import org.apache.commons.cli.*;







public class RequestQueue {
    

    static final Gson GSON = new Gson();

    public void run(String ConnectionString, String Queue, String request, String id) throws Exception {

        

        // Create a QueueClient instance for sending and then asynchronously send messages.
        // Close the sender once the send operation is complete.
        QueueClient sendClient = new QueueClient(new ConnectionStringBuilder(ConnectionString, Queue), ReceiveMode.PEEKLOCK);
        this.sendMessagesAsync(sendClient, request, id).thenRunAsync(() -> sendClient.closeAsync());

       
       
    }


    CompletableFuture<Void> sendMessagesAsync(QueueClient sendClient, String request, String id) {
        
        Message message = new Message(request.getBytes(UTF_8));
        message.setContentType("application/json");
        message.setLabel("Request");
        message.setMessageId(id);
        message.setTimeToLive(Duration.ofMinutes(2));
        System.out.printf("\nMessage sending: Id = %s", id);
        return sendClient.sendAsync(message).thenRunAsync(() -> {
            System.out.printf("\n\tMessage acknowledged: Id = %s", message.getMessageId());

        });
    }
}