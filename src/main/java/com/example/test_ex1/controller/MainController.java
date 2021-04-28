package com.example.test_ex1.controller;

import com.example.test_ex1.domain.User;
import com.example.test_ex1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

@Controller
public class MainController {

    private final AddMessageService addMessageService;
    private final DefaultMessageService defaultMessageService;
    private final MessageService messageService;

    public MainController(AddMessageService addMessageService, DefaultMessageService defaultMessageService, MessageService messageService) {
        this.addMessageService = addMessageService;
        this.defaultMessageService = defaultMessageService;
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model,Map<String, Object> modelUsers) {
        return defaultMessageService.getString(model,modelUsers);
    }

    @PostMapping("/add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model) {
        return addMessageService.addMessage(user, text, tag, model);
    }

    @PostMapping("/filterTag")
    public String filterTag(
            @RequestParam String filterTag,
            Map<String, Object> model) {
        return messageService.findMessageByTag(filterTag, model);
    }

    @PostMapping("/filterAuthor")
    public String filterAuthor(
            @RequestParam String filterAuthor,
            Map<String, Object> model) {
        return messageService.findMessageByAuthor(filterAuthor, model);
    }

    @PostMapping("/filterDate")
    public String filterDate(
            @RequestParam String filterDate,
            Map<String, Object> model) {
        return messageService.findMessageByDate(filterDate, model);
    }

    @PostMapping("/filterTagAuthor")
    public String filterTagAuthor(
            @RequestParam String authorName,
            @RequestParam String tagName,
            Map<String, Object> model) {

        return messageService.findMessageByAuthorAndTag(authorName, tagName, model);
    }

}