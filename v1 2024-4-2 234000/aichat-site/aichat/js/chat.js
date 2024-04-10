$(document).ready(function () {

    var tagId=$.getUrlParam("tagId");;

    if(!tagId){
        return false;
    }

    info(tagId);
    list(tagId);

    onload();


});

function info(tagId) {
    $.ajax({
        type: "GET",
        url: SERVER_PATH + "/chat/info",
        data: {
            tagId: tagId
        },
        xhrFields: {withCredentials: true},
        success: function (result) {
            if (result.status) {
                alertBox(result.data.message);
                return false;
            }
            $("#tagName").text(result.data.tagName);
        }
    });
}

function list(tagId) {
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
            set_conversations(result.data);
        }
    });
}

function set_conversations(conversations) {
    if (!conversations) {
        return false;
    }

    $(".conversation-list").empty();

    $.each(conversations, function (i, conversation) {
        var questionDiv = '<div class="question-container">' +
            '<table class="question">' +
            '<td>' +
            '<span>' + conversation.createTime + '</span>' +
            '<div class="user-message">' + conversation.userMessage + '</div>' +
            '</td>' +
            '<td type="text">' + conversation.username + '</td>' +
            '</table>' +
            '</div>';

        var answerDiv = '<div class="answer-container">' +
            '<table class="answer">' +
            '<td type="text">AI</td>' +
            '<td>' +
            '<span>' + conversation.createTime + '</span>' +
            '<div class="bot-message">' + conversation.botMessage + '</div>' +
            '</td>' +
            '</table>' +
            '</div>';

        $(".conversation-list").append(questionDiv);
        $(".conversation-list").append(answerDiv);
    });
}



