package com.example.test_ex1.repos;

import com.example.test_ex1.domain.Message;
import com.example.test_ex1.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {
   List<Message> findByTag(String tag);
   List<Message> findByAuthor(User author);
   @Query("SELECT m FROM Message m WHERE m.tag= :tag AND m.author.username=:author")
   List<Message> findByQueryTagAndAuthor(@Param("tag") String tag,@Param("author")  String author);
   @Query("SELECT m FROM Message m WHERE m.messageDate= :Date")
   List<Message> findByQueryDate(@Param("Date") String date);
}