var lastId;
var interval;

$(document).ready(function () {
    
    
    $("#stopButton").on("click", function() {
        var latestReply = $(".latest-reply");
        var latestReplyText = latestReply.text();
        clearInterval(interval); // 停止字符流输出
        $("#stopButton").hide();
        updateStop(lastId,latestReplyText);
    });

    

});

function listLastReply(tagId) {
    $.ajax({
        type: "GET",
        url: SERVER_PATH + "/chat/list",
        data: {
            tagId: tagId
        },
        xhrFields: {withCredentials: true},
        success: function (result) {
            if (result.status) {
                alertBox(result.data.message);
                return false;
            }
            set_conversations_last(result.data);
        }
    });
}

function set_conversations_last(conversations) {
    if (!conversations) {
        return false;
    }

    $(".conversation-list").empty();

    var len=conversations.length;

    $.each(conversations, function (i, conversation) {
        var questionDiv = '<div class="question-container">' +
            '<table class="question">' +
            '<td>' +
            '<span>' + conversation.createTime + '</span>' +
            '<div class="user-message">' + savePre(conversation.userMessage) + '</div>' +
            '</td>' +
            '<td type="text">' + conversation.username + '</td>' +
            '</table>' +
            '</div>';

        var answerDiv = '<div class="answer-container">' +
            '<table class="answer">' +
            '<td type="text">AI</td>' +
            '<td>' +
            '<span>' + conversation.createTime + '</span>' +
            '<div class="bot-message">' + savePre(conversation.botMessage) + '</div>' +
            '</td>' +
            '</table>' +
            '</div>';

        if(i!=len-1){
            $(".conversation-list").append(questionDiv);
            $(".conversation-list").append(answerDiv);
        }

    });

    // 获取最新对话的回复
    var lastConversation = conversations[len - 1];
    lastId=lastConversation.id;

    var questionDiv = 
        '<div class="question-container">' +
            '<table class="question">' +
                '<td>' +
                    '<span>' + lastConversation.createTime + '</span>' +
                    '<div class="user-message">' + savePre(lastConversation.userMessage) + '</div>' +
                '</td>' +
                '<td type="text">' + lastConversation.username + '</td>' +
            '</table>' +
        '</div>';

    $(".conversation-list").append(questionDiv);

    var botMessageWithBrAndSpace = lastConversation.botMessage.replace(/\n/g, "\\n");

    var answerDiv = 
    '<div class="answer-container">' +
        '<table class="answer">' +
            '<td type="text">AI</td>' +
            '<td>' +
                '<span>' + lastConversation.createTime + '</span>' +
                '<div class="bot-message latest-reply">' + botMessageWithBrAndSpace + '</div>' +
            '</td>' +
        '</table>' +
    '</div>';

    $(".conversation-list").append(answerDiv);

  
    // 逐字显示最新回复
    var latestReply = $(".latest-reply");
    var latestReplyText = botMessageWithBrAndSpace;

    latestReply.empty();
    var index = 0;
    interval = setInterval(function() {
        if (index < latestReplyText.length) {
            $("#stopButton").show();
            if (latestReplyText.charAt(index) === "\\") {
                if (latestReplyText.charAt(index + 1) === "n") {
                    latestReply.append("<br>");
                    index++; // 跳过"n"
                } else {
                    latestReply.append(latestReplyText.charAt(index));
                }
            } else if(latestReplyText.charAt(index)===' '){
                latestReply.append("&nbsp;");

            }else {
                latestReply.append(latestReplyText.charAt(index));
            }
            index++;
        
        } else {
            clearInterval(interval);
            $("#stopButton").hide();
        }
    }, 25); // 逐字显示的速度，您可以根据需要调整

    
    
}

function updateStop(tagId,message){
    $.ajax({
        type: "POST",
        url: SERVER_PATH + "/chat/updateStop",
        data: {
            id: tagId,
            botMessage: message
        },
        xhrFields: {withCredentials: true},
        success: function (result) {
            if (result.status) {
                alertBox(result.data.message);
                return false;
            }
            
        }
    });
}