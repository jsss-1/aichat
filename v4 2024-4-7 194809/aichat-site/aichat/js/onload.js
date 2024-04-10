function onload(){
    window.onload = function() {
        // 找到消息容器
        var messageContainer = document.querySelector(".message-container");
    
        // 找到消息容器中最后一个子元素
        var lastMessage = messageContainer.lastElementChild;
    
        // 将最后一个消息元素滚动到可见区域
        lastMessage.scrollIntoView({ behavior: 'auto', block: 'end' });
    
    };
}

