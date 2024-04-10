package com.jsss.qianfan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tag {
    private Integer id;

    private Integer userId;

    private String tagName;

    private Integer top;


}
