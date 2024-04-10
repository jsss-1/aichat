package com.jsss.qianfan.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {

    private Integer id;

    private Integer tagId;

    private String username;

    private String userMessage;

    private String botMessage;

    private String createTime;

}
