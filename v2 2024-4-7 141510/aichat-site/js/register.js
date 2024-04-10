$(document).ready(function () {
    // 立即注册单击事件
    $("#btn-register").click(function (e) { 
        register(e);
    });
});


function register(e) {


    var username = $("#username").val();
    var password = $("#password").val();
    var password_confirm = $("#password-confirm").val();

    if (!username) {
        alertBox("用户名不能为空！");
        return false;
    }

    if (!password) {
        alertBox("密码不能为空！");
        return false;
    }
    if (!password_confirm || password_confirm != password) {
        alertBox("两次输入的密码不一致！");
        return false;
    }



    $.ajax({
        type: "POST",
        url: SERVER_PATH + "/user/register",
        data: {
            "username": username,
            "password": password
        },
        xhrFields: {withCredentials: true},
        success: function (result) {
            if (result.status) {
                alert(result.data.message);
                return false;
            }
            alertBox("注册成功！", function(){
                window.location.href = "login.html";
            });
        }
    });    
}