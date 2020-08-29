<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="JavaBean.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: 80664
  Date: 2020/8/4
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>首页_小牛枸杞店</title>
  <link type="text/css" rel="stylesheet" href="css/index.css" />
  <script src="js/jquery.js"></script>
  <script>
    var test;
    var text=document.title;
    var timerId;
    $(function () {
      $.ajax({url:"showProducts",
                type:"get",
                data:{
                  opr:"show"
                },
                dataType:"json",
                success:function (result) {
                  for(var i = 0;i<8;i++){
                    var product = result[i];
                    var html = "<li style=\"overflow: hidden\"><a href=\"\" target=\"_blank\"><img src=\"productsImage/"+product.main_image+"\" width=\"175\"\n" +
                            "\t\t\t\t\t\t\t\t\t\theight=\"175\" /></a>\n" +
                            "\t\t\t\t\t\t\t\t<p class=\"pro_title\">\n" +
                            "\t\t\t\t\t\t\t\t\t<a title=\"\" href=\"\" text=\"\">"+product.subtitle+"</a>\n" +
                            "\t\t\t\t\t\t\t\t</p>\n" +
                            "\t\t\t\t\t\t\t\t<p class=\"brown\">\n" +
                            "\t\t\t\t\t\t\t\t\t惊喜价：￥<b>￥"+product.price+"</b>\n" +
                            "\t\t\t\t\t\t\t\t</p>\n" +
                            "\t\t\t\t\t\t\t\t<p class=\"light_gray\">\n" +
                            "\t\t\t\t\t\t\t\t\t市场价： ￥<del>￥998</del>\n" +
                            "\t\t\t\t\t\t\t\t</p>\n" +
                            "\t\t\t\t\t\t\t</li>";

                    $("#show").append(html);
                  }
                  for(var j = 8;j<12;j++){
                      var product = result[j];
                      var html = "<li><a href=\"\"><img src=\"productsImage/"+product.main_image+"\" width=\"85\" height=\"85\" /></a>\n" +
                          "                  <p class=\"pro_title\">\n" +
                          "                      <a href=\"\">"+product.name+"</a>\n" +
                          "                  </p>\n" +
                          "                  <p class=\"brown\">\n" +
                          "                      ￥<b>"+product.price+"</b>\n" +
                          "                  </p>\n" +
                          "                  </li>";
                      $("#hotShopping").append(html);
                  }
                }
              }
      )
        test = setTimeout("showGg()",10000);
        newtext();
      $("#dj").click(function(){
        $(".divtwo").animate({bottom:"-200px"});
      });
    });

    function showGg(){
      // 广告效果
      $(".divtwo").animate({bottom:"0px"});
    }

    function newtext() {
      clearTimeout(timerId)
      document.title=text.substring(1,text.length)+text.substring(0,1)
      text=document.title.substring(0,text.length)
      timerId = setTimeout("newtext()", 800)
    }

  </script>
  <style>
    .allsort:hover .fication_con{
      display:block;
    }

    .fication_con{
      display: none;
      position: absolute;
      background-color: #f9f9f9;
      min-width: 160px;
      box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
      padding: 12px 16px;
    }
    .divtwo{
      width: 200px;
      height: 200px;
      background-color: rgb(248,117,26);
      overflow: hidden;
      position: fixed;
      right: 0px;
      bottom: -200px;
      color: white;
      font-size: 14px;
    }
  </style>
</head>

<body class="index">
<div class="container">
  <div class="header">
    <h1 class="logo">
      <a title="" style="background:url(css/images/logo.gif)" href="">电子商务平台</a>
    </h1>
    <ul class="shortcut">
      <li class="first"><a href="">个人中心</a></li>
      <li><a href="">我的订单</a></li>
    </ul>
    <p class="loginfo">
      ${sessionScope.userName}！您好，欢迎您来到购物！[<a href="register?opr=out" class="reg">安全退出</a>]

      [<a href="login.html">登录</a>
      <a class="reg" href="register.html">免费注册</a>]
    </p>
  </div>
  <div class="navbar">
    <ul>
      <li><a href="index.jsp">首页</a></li>
    </ul>
      <ul>
          <li>
              <a href="classification.html">枸杞小百科</a>

          </li>
      </ul>

    <div class="mycart">
      <dl>
        <dt>
          <a href="cart.html">购物车<b name="mycart_count">12</b>件
          </a>
        </dt>
        <dd>
          <a href="cart.jsp">去结算</a>
        </dd>
      </dl>
      <!--购物车浮动div 开始-->
      <div class="shopping" id="div_mycart" style="display: none;">
        <dl class="cartlist" id="shopcarDiv">

          <dd id="site_cart_dd_0">
            <div class="pic f_l">
              <img width="55" height="55" src="">
            </div>
            <h3 class="title f_l">
              <a href="">111</a>
            </h3>
            <div class="price f_r t_r">
              <b class="block">1200 </b> <input class="del" type="button" value="删除"
                                                onclick="del('')">
            </div>
          </dd>
          <dd class="static">
							<span>共<b name="mycart_count">12</b>件商品
							</span>金额总计：<b name="mycart_sum">1200.00</b>
          </dd>
          <dd class="static">
              <a href="cart.jsp"><label class="btn_orange"><input type="button" value="去购物车结算" ></label></a>
          </dd>
        </dl>
      </div>
    </div>
  </div>

  <div class="searchbar">
    <div class="allsort">
      <a href="javascript:void(0)" style="text-decoration: none ">全部商品分类</a>
        <div class="fication_con">
            <a  href="<c:url value="sortproduct.jsp" />">--------黑枸杞--------</a><br />
            <a  href="<c:url value="sortproduct1.jsp" />">--------红枸杞--------</a>
        </div>
    </div>

    <div class="searchbox">
      <form method='get' action=''>
        <input class="text" type="text" name='q' autocomplete="off" placeholder="输入关键字..." /> <input
              class="btn" type="submit" value="商品搜索" />
      </form>

    </div>
    <div class="hotwords">
      热门搜索：<a href="">百瑞源</a>
    </div>
  </div>

  <div class="wrapper clearfix">
    <div class="sidebar f_r">
      <!--热卖商品-->
      <div class="hot box m_10">
        <div class="title">
          <h2>热卖商品</h2>
        </div>
          <div style="margin-left: 45px" class="cont clearfix">
              <ul id="hotShopping" class="prolist">
<%--                  <li><a href=""><img src="" width="85" height="85" /></a>--%>
<%--                  <p class="pro_title">--%>
<%--                      <a href="">图书名</a>--%>
<%--                  </p>--%>
<%--                  <p class="brown">--%>
<%--                      ￥<b>53.7</b>--%>
<%--                  </p>--%>
<%--                  </li>--%>
              </ul>
          </div>
      </div>
      <!--热卖商品-->
    </div>
    <div class="main f_l">
      <!--最新商品-->
      <div class="box yellow m_10">
        <div class="title title3">
          <h2>
            <img src="css/images/hot.png" alt="最新商品" width="160" height="36" />
          </h2>
          <a class="more" href="goods_list.html">更多商品...</a>
        </div>
        <div class="cont clearfix">
          <ul id="show" class="prolist">
            <!--							<li style="overflow: hidden"><a href="" target="_blank"><img src="productsImage/p1.jpg" width="175"-->
            <!--										height="175" /></a>-->
            <!--								<p class="pro_title">-->
            <!--									<a title="" href="" text=""></a>-->
            <!--								</p>-->
            <!--								<p class="brown">-->
            <!--									惊喜价：￥<b>￥999</b>-->
            <!--								</p>-->
            <!--								<p class="light_gray">-->
            <!--									市场价： ￥<del>￥1599</del>-->
            <!--								</p>-->
            <!--							</li>-->
          </ul>
        </div>
      </div>
    </div>
  </div>
  <div class="help m_10">
    <div class="cont clearfix">
      <dl>
        <dt>
          <a href="">购物指南</a>
        </dt>
        <dd>
          <a href="">订单状态</a>
        </dd>
        <dd>
          <a href="">购物流程</a>
        </dd>
      </dl>
      <dl>
        <dt>
          <a href="">支付帮助</a>
        </dt>
        <dd>
          <a href="">支付帮助</a>
        </dd>
        <dd>
          <a href="">在线支付</a>
        </dd>
        <dd>
          <a href="">货到付款</a>
        </dd>
      </dl>
      <dl>
        <dt>
          <a href="">配送帮助</a>
        </dt>
        <dd>
          <a href="">EMS/邮政普包</a>
        </dd>
        <dd>
          <a href="">商品验货与签收</a>
        </dd>
        <dd>
          <a href="">配送范围及运费</a>
        </dd>
      </dl>
      <dl>
        <dt>
          <a href="">售后服务</a>
        </dt>
        <dd>
          <a href="">售后服务</a>
        </dd>
        <dd>
          <a href="">发票制度</a>
        </dd>
        <dd>
          <a href="">退货说明</a>
        </dd>
        <dd>
          <a href="">换货说明</a>
        </dd>
      </dl>
      <dl>
        <dt>
          <a href="">帮助信息</a>
        </dt>
        <dd>
          <a href="">友情链接</a>
        </dd>
        <dd>
          <a href="">联系客服</a>
        </dd>
        <dd>
          <a href="">找回密码</a>
        </dd>
        <dd>
          <a href="">关于我们</a>
        </dd>
      </dl>
    </div>
  </div>
  <div class="footer">
    <p class="links">
      <a href="">关于我们</a>|<a href="">常见问题</a>|<a href="">安全交易</a>|<a href="">购买流程</a>|<a href="">如何付款</a>|<a
            href="">联系我们</a>|<a href="">合作提案</a>
    </p>
    Copyright © 2015-2021 <a class="copyys" target="_blank" href="">蜀ICP备01000010号</a>
  </div>
</div>
   <div class="divtwo" >
       <div style="float: left;margin:0px"><p style="float: left;margin:0px">四川肛肠科医院</p></div>
       <div style="float: right;margin:0px"><input style="margin-left:5px" id="dj" type="button" value="X"  /></div>
       <div style="margin-top:70px"><p style="float: left;margin:0px">四川肛肠科医院,历史悠久,刀工精湛,竭诚为您服务!!!!</p></div>
       <div style="float:left;margin-top:55px;padding:0px"><a href="https://app.tanwan.com/htmlcode/60747.html">贪玩蓝月</a></div>
       <div style="float:right;margin-top:55px;padding:0px"><a href="https://ada.baidu.com/site/cdgtgck.com/xyl?imid=89d1cd90788dbc7669b97f218a912066#back1598431622772" >点我咨询</a></div>
    </div>
</body>

</html>