package com.example.test_ex1.service;

import com.example.test_ex1.domain.Message;
import com.example.test_ex1.repos.MessageRepo;
import com.example.test_ex1.repos.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepo messageRepo;
    private final UserRepo userRepo;

    Iterable<Message> messages;

    public MessageServiceImpl(MessageRepo messageRepo, UserRepo userRepo) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
    }

    @Override
    public String findMessageByDate(
            String date,
            Map<String,
            Object> model) {

        if (date != null && !date.isEmpty()) {
            messages = messageRepo.findByQueryDate(date);
        } else {
            messages = messageRepo.findAll();
        }
        model.put("messages",messages);
        return "main";
    }
    @Override
    public String findMessageByTag(
            String tag,
            Map<String, Object> model) {

        if (tag != null && !tag.isEmpty()) {
            messages = messageRepo.findByTag(tag);
        } else {
            messages = messageRepo.findAll();
        }
        model.put("messages",messages);
        return "main";
    }
    @Override
    public String findMessageByAuthor(
            String author,
            Map<String, Object> model) {

        if (author != null && !author.isEmpty()) {
            messages = messageRepo.findByAuthor(userRepo.findByUsername(author));
        } else {
            messages = messageRepo.findAll();
        }
        model.put("messages",messages);
        return "main";
    }
    @Override
    public String findMessageByAuthorAndTag(
            @RequestParam String author,
            @RequestParam String tag,
            Map<String, Object> model) {

        if (tag != null
                && !tag.isEmpty()
                && author != null
                && !author.isEmpty()) {
            messages = messageRepo.findByQueryTagAndAuthor(tag,author);
        } else {
            messages = messageRepo.findAll();
        }

        model.put("messages",messages);
        return "main";
    }
}
