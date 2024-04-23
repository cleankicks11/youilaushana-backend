package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

@RestController
@RequestMapping("messages")
public class MessagesController {
    MessageService messageService;
    AccountService accountService;

    @Autowired
    public MessagesController(MessageService messageService, AccountService accountService){
        this.messageService = messageService;
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        if(messageService.isMessageValid(message)){
            Message newMessage = messageService.createMessage(message);
            if(newMessage != null){
                return ResponseEntity.ok(newMessage);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping
    public List<Message> getAllMessages(Message messages){
            return messageService.listAllMessages(messages);
    }

    @GetMapping("{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable("message_id") Integer message_id){
        // System.out.println("message controller message id: " + message_id.getMessage_id());
        // if(messageService.doesMessageExist(message_id.getMessage_id())){
        //     Message messageData = messageService.returnMessageText(message_id);
        //     System.out.println("message controller message: " + messageData);
        //     if(messageData != null){
        //         return ResponseEntity.status(200).body(messageData);
        //     } else {
        //         return ResponseEntity.status(HttpStatus.OK).body(null);
        //     }
        // }
        // Message messageData = messageService.returnMessageText(message_id);
        // System.out.println("Controller else message data: " + messageData);
        // return ResponseEntity.status(HttpStatus.OK).body(null);
        Message messages = messageService.returnMessageText(message_id);
        if (messages == null) {
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(200).body(messages);
        }
    }

    @PatchMapping("{message_id}")
    public ResponseEntity<Integer> updateMessageById(@PathVariable Integer message_id, @RequestBody Message messageText){
        //System.out.println("Message_id: " + message_id + " and message_text: " + message_text);
        //messageText.setMessage_id(message_id);
        //Message messageToUpdate = messageService.updateMessage(message_id, messageText);
        String messageToUpdate = messageText.getMessage_text();
        Integer idToUpdate = messageService.updateMessage(message_id, messageToUpdate);
        if(idToUpdate == 1){
            return ResponseEntity.status(200).body(1);
        } else {
            return ResponseEntity.status(400).body(0);
        }
    }

    @DeleteMapping("{message_id}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable Integer message_id){
       if(messageService.doesMessageExist(message_id)){
            return ResponseEntity.status(HttpStatus.OK).body(1);
       } else {
            return ResponseEntity.status(HttpStatus.OK).body(null);
       }
    }
}
