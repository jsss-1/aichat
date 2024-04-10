$(document).ready(function () {

    tagList();

    $("#editBlock").hide();

    $(".add-button").on("click", function() {
        addTag();
    });



});

function tagSearch(data) {
    var data=$("#search-input").val();
         
    if(!data){
        //没有数据搜索全部
        tagList();
        return false;
    }

    $.ajax({
        type: "GET",
        url: SERVER_PATH + "/tag/search",
        data: {
            data: data
        },
        xhrFields: {withCredentials: true},
        success: function (result) {
            if (result.status) {
                alertBox(result.data.message);
                return false;
            }
            set_tags(result.data);
        }
    });
}

function addTag() {
    $.ajax({
        type: "POST",
        url: SERVER_PATH + "/tag/addTag",
        xhrFields: {withCredentials: true},
        success: function (result) {
            if (result.status) {
                alertBox(result.data.message);
                return false;
            }
            tagList();
        }
    });
}


function tagList() {
    $.ajax({
        type: "GET",
        url: SERVER_PATH + "/tag/tagList",
        xhrFields: {withCredentials: true},
        success: function (result) {
            if (result.status) {
                alertBox(result.data.message);
                return false;
            }
            set_tags(result.data);
        }
    });
}

function set_tags(tags) {
    if (!tags) {
        return false;
    }

    $(".tag-list").empty();

    $.each(tags, function (i, tag) {
        var btnClass = tag.top === 0 ? "top-btn" : "notop-btn";
        var topClass = tag.top === 0 ? "ptTag" : "topTag";

        var tagDiv = `
            <div class="tag">
                <img class="tag-btn ${topClass}"></img>
                <span class="show-btn" data-id="${tag.id}">${tag.tagName}</span>
                <div class="button-group">
                    <img class="icon-btn ${btnClass}" data-id="${tag.id}"></img>
                    <img class="icon-btn modify-btn" data-id="${tag.id}" data-name="${tag.tagName}"></img>
                    <img class="icon-btn delete-btn" data-id="${tag.id}"></img>
                </div>
            </div>`;
        
        $(".tag-list").append(tagDiv);
    });
    


    $(".show-btn").on("click", function() {
        var tagId = $(this).data('id');
        window.location.href="aichat.html?tagId="+tagId;
    });

    $(".notop-btn").on("click", function() {
        var tagId = $(this).data('id');
        var newTop=0;
        topTag(tagId,newTop);
    });

    $(".top-btn").on("click", function() {
        var tagId = $(this).data('id');
        var newTop=top=1;
        topTag(tagId,newTop);
    });

    $(".modify-btn").on("click", function() {
        var tagId = $(this).data('id');
        var tagName = $(this).data('name'); // 获取标签名称
    
        // 将标签名称填充到输入框中
        $("#newName").val(tagName);
    
        // 显示编辑界面块
        $("#editBlock").show();
    
        // 保存按钮点击事件
        $("#saveBtn").off("click").on("click", function() {
            var newName = $("#newName").val();

            if(!newName){
                alertBox("请输入新名字");
                return false;
            }

            modify(tagId,newName);

            // 关闭编辑界面块
            $("#editBlock").hide();
        });

        $("#cancelBtn").on("click", function() {
            $("#editBlock").hide();
        });
    });

    $(".delete-btn").on("click", function() {
        var tagId = $(this).data('id');
       
        // 弹出确认删除的提示框
        var confirmDelete = confirm("确定要删除这个标签吗？");
    
        // 如果用户点击确定删除，则执行删除操作
        if (confirmDelete) {
            deleteTag(tagId);
        } 

    });

}

function topTag(tagId,newTop){

    $.ajax({
        type: "POST",
        url: SERVER_PATH + "/tag/top",
        data: {
            tagId: tagId,
            top: newTop
        },
        xhrFields: {withCredentials: true},
        success: function (result) {
            if (result.status) {
                alertBox(result.data.message);
                return false;
            }
            tagList();
        }
    });
}

function modify(tagId,newName){

    $.ajax({
        type: "POST",
        url: SERVER_PATH + "/tag/modify",
        data: {
            tagId: tagId,
            tagName: newName
        },
        xhrFields: {withCredentials: true},
        success: function (result) {
            if (result.status) {
                alertBox(result.data.message);
                return false;
            }
            tagList();
        }
    });
}

function deleteTag(tagId){

    $.ajax({
        type: "GET",
        url: SERVER_PATH + "/tag/delete",
        data: {
            tagId: tagId
        },
        xhrFields: {withCredentials: true},
        success: function (result) {
            if (result.status) {
                alertBox(result.data.message);
                return false;
            }
            tagList();
        }
    });
}


