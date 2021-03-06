<%@ page import="cn.web.takeout.model.User" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/1/4
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>网站信息</title>
    <link rel="stylesheet" href="../css/pintuer.css">
    <link rel="stylesheet" href="../css/admin.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/jquery-form.js"></script>
    <script src="../js/pintuer.js"></script>
    <script src="../js/layer.js"></script>
    <script type="text/javascript">
        $(function(){
            //判断session中的sex值 性别为男或女
            var userSex = ${sessionScope.user.sex};
            if(userSex == 1){//女
                $("#radioWoman").attr("checked","checked");
            }else{//男
                $("#radioMan").attr("checked","checked");
            }

            //头像和文本同时上传
            $("#submit").click(function(){
                var file = $("#file").val();
                if(file == ""){//当头像没有修改，file为空
                    $("#formData").ajaxSubmit({
                        url : "${pageContext.request.contextPath}/user/updateUserNotFile.do",
                        type : "post",
                        dataType : "json",
                        success : function(data){
                            layer.msg("设置成功！");
                        },
                        error : function(data){
                            alert("error:" + data.responseText)
                        }
                    });
                }else{
                    $("#formData").ajaxSubmit({
                        url : "${pageContext.request.contextPath}/user/updateUser.do",
                        type : "post",
                        dataType : "json",
                        success : function(data) {
                            $("#headPic",window.parent.document).attr("src","../"+data.headPic);
                            layer.msg("设置成功！");
                        },
                        error : function(data) {
                            alert("error:" + data.responseText);
                        }
                    });
                }
            });

            //点击图片的输入框-----上传图片
            $("#image1").click(function () {
                $("#file").click();
            });
        });

        //改变图片
        var fileChange = function(event) {
            var files = event.target.files, file;
            if (files && files.length > 0) {
                // 获取目前上传的文件
                file = files[0];// 文件大小校验的动作
                if (file.size > 1024 * 1024 * 2) {
                    layer.msg('图片大小不能超过 2MB!');
                    return false;
                }
                // 获取 window 的 URL 工具
                var URL = window.URL || window.webkitURL;
                // 通过 file 生成目标 url
                var imgURL = URL.createObjectURL(file);
                //用attr将img的src属性改成获得的url
                $("#img").attr("src", imgURL);
                // 使用下面这句可以在内存中释放对此 url 的伺服，跑了之后那个 URL 就无效了
                // URL.revokeObjectURL(imgURL);
            }
        }
    </script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 网站信息</strong></div>
    <div class="body-content">
        <form id="formData" name = "user" method="post" class="form-x" action="" enctype="multipart/form-data">
            <div id="userId" class="form-group">
                <div class="label">
                    <label>用户名：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="id" value="${sessionScope.user.id }" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>用户名：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="userName" value="${sessionScope.user.userName }" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>头像：</label>
                </div>
                <div class="field">
                    <!--<input type="text" id="url1" name="headPic" class="input tips" style="width:25%; float:left;" value="" data-toggle="hover" data-place="right" data-image="../"  />-->
                    <!--img便签不能带中文-->
                    <img id="img" src="../${sessionScope.user.headPic} " style="width: 60px;height: 50px;">
                    <input type="text" style="display: none" name="headPic" value="${sessionScope.user.headPic}">
                    <input type="file" id="file" name = "file" style="display: none" onchange="fileChange(event);">
                    <input type="button" class="button bg-blue margin-left" id="image1" value="+ 浏览上传" >
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>密码：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="password" value="${sessionScope.user.password }" />
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>性别：</label>
                </div>
                <div class="ffield">
                    <!--<input type="text" class="input" name="s_name" value="" />
                    <div class="tips"></div>-->
                    男&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" id="radioMan"  value="0"/>
                    女&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" id="radioWoman" value="1"/>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>联系电话：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="phone" value="${sessionScope.user.phone }" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>Email：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="email" value="${sessionScope.user.email }" />
                    <div class="tips"></div>
                </div>
            </div>

            <%--<div class="form-group">--%>
                <%--<div class="label">--%>
                    <%--<label>创建时间：</label>--%>
                <%--</div>--%>
                <%--<div class="field">--%>
                    <!--<textarea name="scopyright" class="input" style="height:120px;"></textarea>
                    <div class="tips"></div>-->
                    <!--<input type="text" name="createTime" value="${sessionScope.user.createTime }" style="display: none" />补充user对象的信息（创建时间）-->
                    <input type="text" name="shopId" value="${sessionScope.user.shopId}" style="display: none" /><!--补充user对象的信息（店铺id）-->
                    <%--<label id="createTime" class="input">${sessionScope.user.createTime }</label>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button id="submit" class="button bg-main icon-check-square-o" type="button"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body></html>
