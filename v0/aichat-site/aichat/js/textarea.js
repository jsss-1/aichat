var textarea = document.getElementById("messageInput");

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
    
    
            $.ajax({
                type: "POST",
                url: SERVER_PATH+"/chat/chat",
                data:{
                    "content":message
                },
                xhrFields: {withCredentials: true},
                success:function(result){
                    if (result.status) {
                        alertBox(result.data.message);
                        return false;
                    }
                    list();
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