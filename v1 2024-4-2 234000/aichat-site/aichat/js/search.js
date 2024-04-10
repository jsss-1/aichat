$(document).ready(function () {

    $(".add-btn").hide();
    $("#search-input").hide();
    $(".add-button").show();
    $(".search-btn").show();
    
    hideSearchBox();

});

function showSearchBox() {
    
    // 隐藏添加新对话按钮和搜索按钮
    $(".add-button").hide();
    $(".search-btn").hide();

    // 显示搜索框
    $(".add-btn").show();
    $("#search-input").show();

}

function hideSearchBox() {
    // 点击其他地方隐藏搜索框
    $(document).on("click", function (e) {
        if (!$(e.target).closest("#search-input").length && !$(e.target).closest(".search-btn").length) {
            $(".add-btn").hide();
            $("#search-input").hide();
            $(".add-button").show();
            $(".search-btn").show();
         }
    });
}