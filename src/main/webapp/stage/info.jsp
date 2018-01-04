<%@ page import="cn.web.takeout.model.User" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/1/4
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script src="../js/pintuer.js"></script>
    <script type="text/javascript">
        $(function(){
            //判断session中的sex值 性别为男或女
            var userSex = ${sessionScope.user.sex};
            if(userSex == 1){//女
                $("#radioWoman").attr("checked","checked");
            }else{//男
                $("#radioMan").attr("checked","checked");
            }
        });
    </script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 网站信息</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" action="">
            <div class="form-group">
                <div class="label">
                    <label>用户名：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="userName" value="${sessionScope.user.userName}" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>头像：</label>
                </div>
                <div class="field">
                    <input type="text" id="url1" name="slogo" class="input tips" style="width:25%; float:left;" value="${sessionScope.user.headPic }" data-toggle="hover" data-place="right" data-image=""  />
                    <input type="button" class="button bg-blue margin-left" id="image1" value="+ 浏览上传" >
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>密码：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="surl" value="${sessionScope.user.password }" />
                </div>
            </div>
            <!--
            <div class="form-group" style="display:none">
                <div class="label">
                    <label>副加标题：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="sentitle" value="" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>网站关键字：</label>
                </div>
                <div class="field">
                    <textarea class="input" name="skeywords" style="height:80px"></textarea>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>网站描述：</label>
                </div>
                <div class="field">
                    <textarea class="input" name="sdescription"></textarea>
                    <div class="tips"></div>
                </div>
            </div>-->
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
                    <input type="text" class="input" name="s_phone" value="${sessionScope.user.phone }" />
                    <div class="tips"></div>
                </div>
            </div>
            <!--
            <div class="form-group">
                <div class="label">
                    <label>电话：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="s_tel" value="" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group" style="display:none;">
                <div class="label">
                    <label>400电话：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="s_400" value="" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>传真：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="s_fax" value="" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>QQ：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="s_qq" value="" />
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group" style="display:none">
                <div class="label">
                    <label>QQ群：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="s_qqu" value="" />
                    <div class="tips"></div>
                </div>
            </div>-->

            <div class="form-group">
                <div class="label">
                    <label>Email：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="s_email" value="${sessionScope.user.email }" />
                    <div class="tips"></div>
                </div>
            </div>
            <!--
            <div class="form-group">
                <div class="label">
                    <label>地址：</label>
                </div>
                <div class="field">
                    <input type="text" class="input" name="s_address" value="" />
                    <div class="tips"></div>
                </div>
            </div>-->

            <div class="form-group">
                <div class="label">
                    <label>创建时间：</label>
                </div>
                <div class="field">
                    <!--<textarea name="scopyright" class="input" style="height:120px;"></textarea>
                    <div class="tips"></div>-->
                    <label class="input">${sessionScope.user.createTime }</label>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body></html>
