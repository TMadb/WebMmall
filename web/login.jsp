<%--
  Created by IntelliJ IDEA.
  User: 80664
  Date: 2020/8/27
  Time: 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title >用户登录</title>

    <link type="text/css" rel="stylesheet" href="css/style.css" />
    <link type="text/css" rel="stylesheet" href="css/demo.css" />
    <link type="text/css" rel="stylesheet" href="css/index.css" />
    <script src="js/jquery.js"></script>
    <script src="js/md5.js"></script>
    <script src="js/Validform_v5.3.2.js"></script>
    <script>
        $(function () {
            $("#loginForm").submit(function () {
                var password  = $("#password").val();

                if(password.trim()!=""&&password.length>6){
                    password = md5(password);
                    $("#password").val(password);
                }
            });
        });
    </script>

    <script type="text/javascript">
        function changeImg() {
            document.getElementById("verificationImg").
                src="<c:url value='/Verification?date'/>"+ new Date();
        }

    </script>
    <script type="text/javascript">
        $(function(){

            $("#loginForm").Validform({
                tiptype:function(msg,o,cssctl){
                    //msg：提示信息;
                    //o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
                    //cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;

                    if(!o.obj.is("form")){//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
                        var objtip=o.obj.parents("td").next().find(".Validform_checktip");
                        cssctl(objtip,o.type);
                        objtip.text(msg);

                        var infoObj=o.obj.parents("td").next().find(".info");
                        if(o.type==2){
                            infoObj.fadeOut(200);
                        }else{
                            if(infoObj.is(":visible")){return;}
                            var left=o.obj.offset().left,
                                top=o.obj.offset().top;

                            infoObj.css({
                                left:left+170,
                                top:top-45
                            }).show().animate({
                                top:top-35
                            },200);
                        }

                    }
                }
            });
        })
    </script>

    <script>
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
            (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','js/analytics.js','ga');
        ga('create', 'UA-81001026-1', 'auto');
        ga('send', 'pageview');
    </script>

    <style type="text/css">
        td{line-height:38px;}
        .Validform_checktip{margin-left:0;}
        .info{
            border:1px solid #ccc;
            padding:2px 20px 2px 5px;
            color:#666;
            position:absolute;
            display:none;
            line-height:20px;
            background-color:#fff;
        }
        .dec {
            bottom: -8px;
            display: block;
            height: 8px;
            overflow: hidden;
            position: absolute;
            left: 10px;
            width: 17px;
        }
        .dec s {
            font-family: simsun;
            font-size: 16px;
            height: 19px;
            left: 0;
            line-height: 21px;
            position: absolute;
            text-decoration: none;
            top: -9px;
            width: 17px;
        }
        .dec .dec1 {
            color: #ccc;
        }
        .dec .dec2 {
            color: #fff;
            top: -10px;
        }
    </style>
</head>
<body>
<div class="brand_list container_2">
    <div></div>

    <div class="wrapper clearfix">
        <div class="wrap_box">
            <h3 class="notice">已注册用户，请登录</h3>
            <p class="tips">欢迎来到我们的网站，如果您已是本站会员请登录</p>
            <div class="box login_box clearfix">
                <form action='register' method="post" id="loginForm" >
                    <input type="hidden" name="opr" value="login" />
                    <table width="515" class="form_table f_l">
                        <col width="120px" />
                        <col />
                        <tr>
                            <th>错误信息:</th>
                            <td>
                                ${login}
                            </td>

                        </tr>
                        <tr>
                            <th>用户名：</th>
                            <td><input class="gray" type="text" name="account"
                                       id="login_info" placeholder="请输入用户名"  datatype="s6-18"/></td>
                            <td>
                                <div class="info"><span class="Validform_checktip">昵称至少6个字符,最多18个字符</span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>

                            </td>
                        </tr>
                        <tr>
                            <th>密码：</th>
                            <td><input class="gray" type="password" id="password" datatype="*6-32"
                                       name="password" placeholder="请输入6-32位长度的密码"  value=""/></td>

                            <td>
                                <div class="info"><span class="Validform_checktip">请输入6-20位长度的密码</span><span class="dec"><s class="dec1">&#9670;</s><s class="dec2">&#9670;</s></span></div>

                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><input class="submit_login" type="submit" value="登录" /></td>
                        </tr>
                    </table>
                </form>

                <!--正常登录时-->
                <table width="360px" class="form_table prompt_3 f_l">
                    <col width="75px" />
                    <col />
                    <tr>
                        <th></th>
                        <td>
                            <p class="mt_10">
                                <strong class="f14">您还不是xxx店</span>用户
                                </strong>
                            </p>
                            <p>
                                现在免费注册成为xxx的用户，便能立即享受便宜又放心的购物乐趣。<a class="blue" href="index.jsp">网站首页>></a>
                            </p>
                            <p class="mt_10">
                                <a class="reg_btn" href="register.html">注册新用户</a>
                            </p>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div></div>
</div>
</body>
</html>
