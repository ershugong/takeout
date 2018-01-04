$(function(){
    var ctx = getRootPath();
    /*$("#bt_login").click(function () {
        var userId = $("#userId").val();
        var password = $("#password").val();
        $.post(ctx + "/user/checkUser.do",{userId:userId,password:password},function (data){
            /*if(data == 0){//登录失败
                alert("登录失败");
                $("#userId").val("");
                $("#password").val("");
            }else{//跳转到主界面
                window.location=ctx + "/stage/index.jsp";
            }
            //window.location = ctx + "/stage/index.jsp";
        });
    });*/

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