package com.example.test_ex1.service;

import com.example.test_ex1.domain.Message;
import com.example.test_ex1.domain.User;
import com.example.test_ex1.repos.MessageRepo;
import com.example.test_ex1.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DefaultMessageService {

    private final MessageRepo messageRepo;

    public DefaultMessageService(MessageRepo messageRepo, UserRepo userRepo) {
        this.messageRepo = messageRepo;
    }

    public String getString(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }


}
