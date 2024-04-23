package com.example.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    //isMEssageValid service method
    public boolean userExists(int postedBy){
        return !messageRepository.findByPostedBy(postedBy).isEmpty();
    }

    //MessageController - getMessageById and deleteMessageById- service
    public boolean doesMessageExist(int messageId){
        if(messageRepository.findById(messageId).isPresent()){
            System.out.println("Service true");
            return true;
        } else {
            System.out.println("Service false");
            return false;
        }
    }

    public List<Message> getMessagesByAccountId(Integer accountId) {
        System.out.println("Service account print: " + messageRepository.findByPostedBy(accountId));
        return messageRepository.findByPostedBy(accountId);
    }

    //Message controller - createMessage - service
    public Message createMessage(Message message){
        return messageRepository.save(message);
    }

    //Message controller - getAllMessages - service
    public List<Message> listAllMessages(Message message){
        return messageRepository.findAll();
    }

    //Message controller - createMessage - service
    public boolean isMessageValid(Message message){
        return message != null && !message.getMessage_text().isEmpty() &&
            message.getMessage_text().length() <= 255 && userExists(message.getPosted_by());
    }

    //MessageController - getMessageById - service
    public Message returnMessageText(Integer message){
        Optional<Message> optionalMessage = messageRepository.findMessageById(message);
        System.out.println("Message in service class: " + optionalMessage);
        if(optionalMessage.isPresent()){
            System.out.println("Service class optional message: " + optionalMessage.get());
            return optionalMessage.get();
        } else {
            System.out.println("Service class conditional failed optional message");
            return null;
        }
    }

    // public List<Message> returnMessageText(Integer accountId) {
    //     System.out.println("Service account print: " + messageRepository.findMessageByIdAgain(accountId));
    //     return messageRepository.findMessageByIdAgain(accountId);
    // }

    public String messageById(Message message){
        if(messageRepository.findById(message.getMessage_id()) != null){
            return message.getMessage_text();
        } else {
            return null;
        }
    }

    //MessageController - updateMessageById - service
    public Integer updateMessage(Integer messageId, String messageText){
        Optional<Message> optionalMessage = messageRepository.findMessageById(messageId);
        if(optionalMessage.isPresent() && messageText.length() > 0 && messageText.length() < 255){
            Message messageTag = optionalMessage.get();
            messageTag.setMessage_text(messageText);
            messageRepository.save(messageTag);
            return 1;
        } else {
            return 0;
        }
    }

    public Integer removeMessageById(Message message){
        Optional<Message> optionalMessage = messageRepository.findById(message.getMessage_id());
        if(optionalMessage.isPresent()){
            Message messageDelete = optionalMessage.get();
            messageRepository.delete(messageDelete);
            return 1;
        } else if (!optionalMessage.isPresent()){
            // Message messageDelete = optionalMessage.get();
            // messageRepository.delete(messageDelete);
            return 2;
        } else {
            return 0;
        }
    }
}
