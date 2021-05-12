package com.example.test_ex1.service;

import com.example.test_ex1.domain.Message;
import com.example.test_ex1.domain.User;
import com.example.test_ex1.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Service
public class AddMessageService {

    private final MessageRepo messageRepo;

    public AddMessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public String addMessage(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model){
        Message message = new Message(text, tag, user);
        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages",messages);
    return "main";
    }

}
