<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>活动设置</title>
    <link rel="stylesheet" href="../css/pintuer.css">
    <link rel="stylesheet" href="../css/admin.css">
    <script src="../js/jquery.min.js"></script>
    <style type="text/css">
        .field{
            margin-top: 20px;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/shop/getShopActivity.do",
                method : "post",
                dataType: "json",
                data : {
                    shopId : "${sessionScope.user.shopId}"
                },
                success: function (data) {
                    if(data.length>0){
                        var obj = data[0];
                        $("#lowPrice").val(obj.type1);
                        $("#cutPrice").val(obj.type2);
                        $("#discount").val(obj.discount);
                        $("#send").val(obj.type3);
                    }
                },
                error:function(data){
                    alert(data);
                }
            });

            $("#activity").click(function(){
                var typeOne = $("#lowPrice").val()
                var typeTwo = $("#cutPrice").val()
                var typeThree = $("#send").val()
                var discount = $("#discount").val()
                $.ajax({
                    url: "${pageContext.request.contextPath}/shop/insertShopActivity.do",
                    method : "post",
                    dataType: "json",
                    data : {
                        shopId : "${sessionScope.user.shopId}",
                        typeOne : typeOne,
                        typeTwo : typeTwo,
                        typeThree : typeThree,
                        discount : discount
                    },
                    success : function (data) {
                        //关闭弹出层
                        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        parent.layer.close(index);
                    },
                    error : function (data) {
                    }
                });
            });

        });


    </script>
</head>
<body>
<div style="margin-left: auto;margin-right: auto;padding-top: 20px;padding-left: 20px;font-size: 12pt;">
    <div>
        <div style="float: left">满：<input type="number" id="lowPrice" name="lowPrice" value="0" style="width: 40px">元,</div><div>减<input style="width: 40px" value="0" type="number" name="cutPrice" id="cutPrice">元</div>
    </div>
    <div class="field">
        折扣:<input type="number" name="discount" id="discount" value="0" style="width: 60px">
    </div>
    <div class="field">
        购满:<input type="number" name="send" id="send" style="width: 60px" value="0">元免邮
    </div>
    <div class="field">
        <button id="activity" class="button bg-main icon-check-square-o" style="margin-left: 30px;background-color:#F7CC4F;border-color:#F7CC4F" type="button"> 确认</button>
    </div>
</div>
</body>
</html>