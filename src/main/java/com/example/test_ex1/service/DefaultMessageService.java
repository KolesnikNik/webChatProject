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

    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private UserRepo userRepo;

    public String getString(Map<String, Object> model,Map<String, Object> modelUsers) {
        Iterable<Message> messages = messageRepo.findAll();
        Iterable<User> users = userRepo.findAll();
        modelUsers.put("users",users);
        model.put("messages", messages);
        return "main";
    }


}
