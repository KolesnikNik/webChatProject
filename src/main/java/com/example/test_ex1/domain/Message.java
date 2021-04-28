package com.example.test_ex1.domain;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

@Entity

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String text;
    private String tag;
    private String messageDate;
    private String messageTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")

    private User author;
    public Message() {}
    public Message(String text, String tag, User user) {

        this.author = user;
        this.text = text;
        this.tag = tag;
        DateFormat df = new SimpleDateFormat("yyyy.dd.MM");
        DateFormat tf = new SimpleDateFormat("HH:mm");
        this.messageDate = df.format(new GregorianCalendar().getTime());
        this.messageTime = tf.format(new GregorianCalendar().getTime());
    }

    public String getAuthorName(){return author != null ? author.getUsername() : "<none>";}
    public String getText(){return text;}
    public void setText(String text){this.text = text;}
    public String getTag(){return tag;}
    public void setTag(String tag){this.tag = tag;}
    public Integer getId(){return id;}
    public void setId(Integer id){this.id = id;}
    public User getAuthor(){return author;}
    public void setAuthor(User author){this.author = author;}
    public String getMessageDate(){return messageDate;}
    public void setMessageDate(String messageDate){this.messageDate = messageDate;}
    public String getMessageTime(){return messageTime != null ? messageTime : "<none>";}
    public void setMessageTime(String messageTime){this.messageTime = messageTime;}
}

