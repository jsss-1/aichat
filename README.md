# AI对话

# 博客



[AI 对话完善【人工智能】](https://jsss-1.blog.csdn.net/article/details/137464589)



# v0：基础

简单聊天



# v1：对话

新建对话，以及AI回复

可以置顶删除修改添加对话，以及AI回复

![v1](https://gitee.com/jsss-1/aichat/blob/master/image/v1.png)



# v2：回复中

用户发送问题之后，显示回复中，得到回复后显示。

前端发送请求之后，先会得到“回复中”；
之后，去轮询获取最新回复。

后端接受请求之后，先存入到数据库中一个未回复请求。
然后异步得到回复之后，再去更新数据库。



![v2](https://gitee.com/jsss-1/aichat/blob/master/image/v2.png)



# v3版本：流式输出



流式输出，终止生成。

前端实现，让消息一个字符一个字符显示



![v3](https://gitee.com/jsss-1/aichat/blob/master/image/v3.png)

# v4版本：多轮对话



实现上下文有关的对话

多次调用qianfan.addMessage().addMessage()



![v4](https://gitee.com/jsss-1/aichat/blob/master/image/v4.png)



# v5版本：修改



前端样式：跳转到最后一个消息



前端样式：Message保留空白符



前端样式：最新回复保留空白符
