var textarea = document.getElementById("messageInput");

var isSendingMessage = false; // 添加一个变量用于标识是否正在发送消息

textarea.addEventListener("keydown", function(event) {
    if (event.key === "Enter" && !event.shiftKey) {
        event.preventDefault();


        if (isSendingMessage) {
            // 如果正在发送消息，则在文本框中添加换行符
            textarea.value += "\n";
        } else{
            var message = textarea.value.trim();
            textarea.value = "";
    
            if(!message){
                alertBox("输入内容不能为空！");
                return false;
            }
    
    
            var tagId=$.getUrlParam("tagId");;
    
            if(!tagId){
                alertBox("没有对应的参数");
                return false;
            }

            isSendingMessage = true; // 设置为true表示正在发送消息
    
            $.ajax({
                type: "POST",
                url: SERVER_PATH+"/chat/chat",
                data:{
                    "tagId": tagId,
                    "content":message
                },
                xhrFields: {withCredentials: true},
                success:function(result){
                    isSendingMessage = false; // 发送完成后设置为false
                    if (result.status) {
                        alertBox(result.data.message);
                        return false;
                    }

                    //请求成功之后
                    list(tagId);
                    
                    getChat(result.data.id);
                   
                }
            });
      
        }

        
    }
});

textarea.addEventListener("keydown", function(event) {
    if (event.key === "Enter" && event.shiftKey) {
        // 在 Shift+Enter 情况下允许换行
        textarea.value += "\n";
        event.preventDefault();
    }
});

function getChat(chatId){
    var tagId=$.getUrlParam("tagId");;
    
    $.ajax({
        type: "GET",
        url: SERVER_PATH+"/chat/getChat",
        data:{
            "id": chatId,
        },
        xhrFields: {withCredentials: true},
        success:function(result){
            isSendingMessage = false; // 发送完成后设置为false
            if (result.status) {
                alertBox(result.data.message);
                return false;
            }

            if (result.data.botMessage == "回复中...") {
                // 继续轮询
                setTimeout(function() {
                    getChat(chatId);
                }, 2500);
            } else {
                // 获取到最终回复
                // 处理回复逻辑
                listLastReply(tagId);
            }
           
        }
    });
}

