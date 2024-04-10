$(document).ready(function () {
    // 设置用户登录状态
    set_login_status();
    // 注销按钮单击事件
    $("#btn-logout").click(function (e) { 
        logout();        
    });
});

// 监听jQuery的ajax事件
// $(document).ajaxSend(function(event, jqXHR, ajaxOptions){
//     ajaxOptions.url += ajaxOptions.url.match(/\?/) ? "&" : "?";
//     ajaxOptions.url += "token=" + window.localStorage.getItem("token");
// });

function set_login_status() {
    var $A = $(".user-name");
    if (!$A) return false;

    $.ajax({
        type: "GET",
        url: SERVER_PATH + "/user/status",
        xhrFields: {withCredentials: true},
        success: function (result) {
            if (result.status == "0" && result.data) {
                $A.text(result.data.nickname);
                $("#user-info").show();
                $("#center").show();
                $("#ai").show();
                $("#register").hide();
                $("#login").hide();

                window.sessionStorage.setItem("id", result.data.userId);
        

                // 根据用户的 userGroup 来设置跳转路径
                var centerLink;
                if (result.data.userGroup === "管理员") {
                    centerLink = "./admin/center.html";
                } else if (result.data.userGroup === "医生") {
                    centerLink = "./doctor/center.html";
                } else if(result.data.userGroup === "老人家属"){
                    centerLink = "./family/center.html"; 
                } else {
                    centerLink = "./user/center.html"; // 默认路径
                }

                $("#center").attr("href", centerLink); // 设置跳转路径

            } else {
                $("#user-info").hide();
                $("#center").hide();
                $("#ai").hide();
                $("#register").show();
                $("#login").show();
            }
        }
    });    
}


function logout() {
    $.ajax({
        type: "GET",
        url: SERVER_PATH + "/user/logout",
        xhrFields: {withCredentials: true},
        success: function (result) {
            if (result.status) {
                alertBox(result.data.message);
                return false;
            }
            alertBox("已经注销！", function(){
                sessionStorage.removeItem("token");
                sessionStorage.removeItem("id");
                sessionStorage.removeItem("familyAccount");
                sessionStorage.removeItem("doctorAccount");

                window.location.href = "../login.html"
            });
        }
    });    
}

function onload(){
    var currentURL = window.location.href;
    // console.log("当前页面路径是：" + currentURL);
    // console.log(userRole);

    var adminMatch = currentURL.match(/\/admin\//);
    if (adminMatch&&userRole!='admin'){
        alertBox("你不是管理员账户",function(){
            window.location.href="../home.html";
        });
    } 

    var doctorMatch = currentURL.match(/\/doctor\//);
    if (doctorMatch&&userRole!='doctor'){
        alertBox("你不是医生账户",function(){
            window.location.href="../home.html";
        });
    } 

    var familyMatch = currentURL.match(/\/family\//);
    if (familyMatch&&userRole!='family') {
        alertBox("你不是家属账户",function(){
            window.location.href="../home.html";
        });
        
    } 

}
