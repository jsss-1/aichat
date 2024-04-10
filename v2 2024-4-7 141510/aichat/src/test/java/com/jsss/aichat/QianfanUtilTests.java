package com.jsss.aichat;


import com.jsss.qianfan.util.QianfanUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class QianfanUtilTests {


    @Autowired
    QianfanUtil qianfanUtil;


    @Test
    void testAddMessage() {
        String content="你好";
        String res = qianfanUtil.addMessage(content);
        System.out.println(res);

    }

    @Test
    void testExecuteStream() {
        String content="你好";
        qianfanUtil.executeStream(content);

    }



}
