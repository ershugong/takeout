<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>餐厅登录后台</title>
    <meta name="keywords" content="餐厅登录后台">
    <meta name="content" content="餐厅登录后台">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <link type="text/css" rel="stylesheet" href="css/login.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript">
        /*$(function () {
            $("#tb_register").click(function(){//点击注册按钮
                var userName =
            });
        });*/
    </script>
</head>
<body class="login_bj" >

<div class="zhuce_body">
    <div class="logo"><a href="#"><img src="images/logo.png" width="114" height="54" border="0"></a></div>
    <div class="zhuce_kong">
        <div class="zc">
            <div class="bj_bai">
                <h3>欢迎注册</h3>
                <form action=<%request.getContextPath();%>user/registerUser.do method="post">
                    <input name="userName" type="text" class="kuang_txt phone" placeholder="用户名">
                    <input name="password" type="text" class="kuang_txt possword" placeholder="密码">
                    <input name="email" type="text" class="kuang_txt email" placeholder="邮箱">
                    <input name="phone" type="text" class="kuang_txt email" placeholder="手机号">
                    <input name="wxUserId" type="text" class="kuang_txt email" placeholder="微信号">
                    <p>
                        <label class="sex_label">男&nbsp;&nbsp;&nbsp;<input name="sex" type="radio" value="0"></label>
                        <label class="sex_label">女&nbsp;&nbsp;&nbsp;<input name="sex" type="radio" value="1"></label>
                    </p>
                    <!--<div>刷新 先不用
                        <div class="hui_kuang"><img src="images/zc_22.jpg" width="92" height="31"></div>
                        <div class="shuaxin"><a href="#"><img src="images/zc_25.jpg" width="13" height="14"></a></div>
                    </div>-->
                    <div>
                        <input name="" type="checkbox" value=""><span>已阅读并同意<a href="#" target="_blank"><span class="lan">《XXXXX使用协议》</span></a></span>
                    </div>
                    <input id="tb_register" name="注册" type="submit" class="btn_zhuce" value="注册">

                </form>
            </div>
            <div class="bj_right">
                <p>使用以下账号直接登录</p>
                <a href="#" class="zhuce_qq">QQ注册</a>
                <a href="#" class="zhuce_wb">微博注册</a>
                <a href="#" class="zhuce_wx">微信注册</a>
                <p>已有账号？<a href="login.html">立即登录</a></p>

            </div>
        </div>
        <!--<P>Diyhe.com&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欢迎您定制盒子模型</P>-->
    </div>

</div>

</body>
</html>
