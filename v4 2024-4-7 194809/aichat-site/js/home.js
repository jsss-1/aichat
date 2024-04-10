$(document).ready(function () {

    //登录跳转后，可能这个还未获取到相关数据，所以需要延迟发送

    setTimeout(function() {
        var userId=sessionStorage.getItem("id");
        var account=sessionStorage.getItem("doctorAccount");
        var url = account ? "doctorList" : "list";

        $.ajax({
            type: "GET",
            url: SERVER_PATH + "/fullcalendar/"+url,
            data:{
                userId: userId
            },
            xhrFields: {withCredentials: true},
            success: function (result) {
                 if (result.status) {
                    alertBox(result.data.message);
                    return false;
                }
    
                result.data.forEach(function(e) {
                    calendar.addEvent(e);//外部的全局变量
                    events.push(e);
                });
                    
                // console.log(events);
            }
        });

    }, 100); // 100毫秒



});


// 函数用于添加新事件对象到事件数组中
function addCalendarEvent(calendar,newEvent) {
    var userId=sessionStorage.getItem("id");

    $.ajax({
        type: "POST",
        url: SERVER_PATH + "/fullcalendar/add",
        data: {
            userId: userId,
            title: newEvent.title,
            allDay: newEvent.allDay,
            start: newEvent.start,
            end: newEvent.end
        },
        xhrFields: {withCredentials: true},
        success: function (result) {
            if (result.status) {
                alertBox(result.data.message);
                return false;
            }
                
            calendar.addEvent(newEvent);
            events.push(newEvent);
            // console.log(events);

        }
    });


}



// 函数用于删除新事件对象到事件数组中
function removeCalendarEvent(arg,e) {
    var userId=sessionStorage.getItem("id");

    let remove = {
        title: e.title,
        allDay: e.allDay,
        start: e.start,
        end: e.end
    };

    $.ajax({
        type: "POST",
        url: SERVER_PATH + "/fullcalendar/remove",
        data: {
            userId: userId,
            title: remove.title,
            allDay: remove.allDay,
            start: remove.start,
            end: remove.end
        },
        xhrFields: {withCredentials: true},
        success: function (result) {
            if (result.status) {
                alertBox(result.data.message);
                return false;
            }

            arg.event.remove();
            events = events.filter(event => event == e);
            // console.log(events);

        }
    });

  

}