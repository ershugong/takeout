$(function(){
    var ctx = getRootPath();
    $("#bt_login").click(function () {
        var userId = $("#userId").val();
        var password = $("#password").val();
        $.post(ctx + "/user/checkUser.do",{id:userId,password:password},function (data){
            if(data == 0){//登录失败
                alert("登录失败");
                $("#userId").val("");
                $("#password").val("");
            }else{
                window.location=ctx + "/stage/success.jsp";
            }

        });
    });

    //获取根目录
    function getRootPath(){
        //pathName:--->   mbuy/user/login.action
        var pathName = window.location.pathname.substring(1);
        //webName:--->mbuy
        var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
        //return:--->http://localhost:9999/mbuy/
        return window.location.protocol + '//' + window.location.host + '/'+ webName;
    }
});