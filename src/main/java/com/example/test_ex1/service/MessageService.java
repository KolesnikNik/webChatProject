package com.example.test_ex1.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface MessageService {

     String findMessageByDate(
            String date,
            Map<String, Object> model
     );
     String findMessageByTag(
            String tag,
            Map<String, Object> model
     );
     String findMessageByAuthor(
            String author,
            Map<String, Object> model
     );
     String findMessageByAuthorAndTag(
            String author,
            String tag,
            Map<String, Object> model
     );
}
