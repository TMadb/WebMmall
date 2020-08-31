<%@ page import="service.serviceImplement.ProductServiceImplement" %>
<%@ page import="entity.ProductBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html >
<%
    String oid = request.getParameter("id");
    int id = 0;
    if(oid!=null){

        id=Integer.parseInt(request.getParameter("id"));
    }
    ProductServiceImplement serviceImplement = new ProductServiceImplement();
    ProductBean product = serviceImplement.selectProductById(id);
    request.setAttribute("price", product.getPrice());
%>
<head>
    <meta charset="UTF-8">
    <title>查看商品详情</title>
    <link type="text/css" rel="stylesheet" href="css/index.css" />
    <script  src="js/jquery.js"></script>
    <script>

        $(function () {
            $("#joinCarBtn").click(function () {
                $.ajax({
                    url:"cartServlet",
                    data:{
                        opr:"joinCart",
                        user_id:$("#name").val(),
                        product_id:$("#data_goodsNo").val(),
                        quantity:$("#buyNums").val()
                    },
                    type:"get",
                    success:function (result) {
                        if (result == "true"){
                            console.log("加入成功")
                        }else{
                            console.log("加入失败")
                        }
                    }
                });
            });
        });

        function add() {
            var buyNums = document.getElementById("buyNums");
            buyNums.value = parseInt(buyNums.value)+1;
        }

        function reduce() {
            var buyNums = document.getElementById("buyNums");
            if (parseInt(buyNums.value) > 0){
                buyNums.value = parseInt(buyNums.value)-1;
            }
        }
    </script>
    <style>
        #grade{
            height:34px
        }
        #grade>ul{
            height:18px
        }
        #grade>ul>li{
            margin:0
        }

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
    </style>
</head>
<body class="index">
<div id="content"></div>
<div class="container">
    <div class="header">
            <a href="https://www.alimama.com/index.htm">电子商务平台|</a>
        <ul class="shortcut">
            <li class="first"><a href="#">个人中心</a></li>
            <li><a href="#">我的订单</a></li>
        </ul>
        <p class="loginfo">

            ${sessionScope.userName}您好，欢迎您来到小牛枸杞店购物！[<a href="register?opr=out" class="reg">安全退出</a>]

                [<a class="reg"  href="<c:url value="/login.jsp" />">登录</a>
                <a class="reg" href="<c:url value="/register.jsp" />">免费注册</a>]

        </p>
    </div>
    <div class="navbar" >
        <ul>
            <li><a href="<c:url value="/index.jsp" />">首页</a></li>
            <li><a href="<c:url value="/classification.html" />">枸杞分类</a></li>
        </ul>
        <div class="mycart">
            <dl>
                <dt>
                    <a href="<c:url value="/filter/cart.jsp" />">购物车<b name="mycart_count">12</b>件
                    </a>
                </dt>
                <dd>
                    <a href="<c:url value="/filter/cart.jsp" />" >去结算</a>
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
                            <b class="block">1200 </b> <input class="del" type="button"
                                                              value="删除" onclick="del('')">
                        </div>
                    </dd>
                    <dd class="static">
							<span>共<b name="mycart_count">12</b>件商品
							</span>金额总计：<b name="mycart_sum">1200.00</b>
                    </dd>
                    <dd class="static">
                        <label class="btn_orange"><input type="button"
                                                         value="去购物车结算" onclick="location.href='<c:url value="/filter/cart.jsp" />'"></label>
                    </dd>
                </dl>
            </div>
        </div>
    </div>

    <div class="searchbar" >
        <div class="allsort">
            <a href="javascript:void(0)" style="text-decoration: none ">全部商品分类</a>
            <div class="fication_con">
                <a  href="<c:url value="sortproduct.jsp" />">--------黑枸杞--------</a><br />
                <a  href="<c:url value="sortproduct1.jsp" />">--------红枸杞--------</a>
            </div>
        </div>

        <div class="searchbox">
            <form method='get'>
                <input class="text" type="text" name='q' autocomplete="off"
                       placeholder="输入关键字..." /> <input class="btn" type="submit"
                                                        value="商品搜索" />
            </form>

        </div>
        <div class="hotwords">
            热门搜索：<a href="#">百瑞源</a>
        </div>
    </div>
    <div class="wrapper clearfix">
        <div class="summary">
            <h2 id="name"><%out.println(product.getName());%></h2>
            <!--基本信息区域-->
            <ul>
                <li>
                    <span >商品Id：
                    <label id="data_goodsNo" >
                    <%out.println(product.getId());%>
                    </label>
                    </span>
                </li>
                <li>
                    <span  style="display: block">商品编号：
                    <label>
                      <%out.println(product.getCategory_id());%>
                    </label>
                    </span>
                </li>
                <li id="priceLi">销售价：<%out.println(product.getPrice());%><b class="price red2"><span
                        class="f30" id="real_price" ></span></b></li>
                <li>市场价：998</li>
                <li>上架时间：<%out.println(product.getCreate_time());%></li>
                <li>修改时间：<%out.println(product.getUpdate_time());%></li>
                <li>库存：现货<span>(<label id="data_storeNums"><%out.println(product.getStock());%></label>)</span>
                </li>
                <li>顾客评分：<span id="grade"></span>&nbsp;(已有<span>1</span>人评价)
                </li>
            </ul>
            <div class="current">
                <dl class="m_10 clearfix">
                    <dt>购买数量：</dt>
                    <dd>
                        <input class="gray_t f_l" type="text" id="buyNums" value="1"
                               maxlength="5" name="buyNums"/>
                        <div class="resize">
                            <a class="add" id="add" onclick="add()"></a> <a class="reduce" id="reduce" onclick="reduce()"></a>
                        </div>
                    </dd>
                </dl>
                <input class="submit_buy" type="button" id="buyNowBtn" value="立即购买" />
                <div class="shop_cart" style="z-index: 1">
                        <input class="submit_join" type="button" id="joinCarBtn"
                               value="加入购物车" />

<%--                    <div class="shopping" id="product_myCart" style='display: none'>--%>
<%--                        <dl class="cart_stats">--%>
<%--                            <dt class="gray f14 bold">--%>
<%--                                <a class="close_2 f_r" href="javascript:closeCartDiv();"--%>
<%--                                   title="关闭">关闭</a> <img src="images/front/right_s.gif"--%>
<%--                                                          width="24" height="24" alt="" />成功加入购物车--%>
<%--                            </dt>--%>
<%--                            <dd>--%>
<%--                                <a  class="btn_blue bold">进入购物车</a><a--%>
<%--                                    class="btn_blue bold" href="javascript:void(0)"--%>
<%--                                    onclick="closeCartDiv();">继续购物>></a>--%>
<%--                            </dd>--%>
<%--                        </dl>--%>
<%--                    </div>--%>
                </div>
            </div>
        </div>

        <div>
            <div class="pic_show"
                 style="width: 335px; height: 335px; position: relative; z-index: 5; padding-bottom: 5px;">
                <img src="/productsImage/<%out.println(product.getMain_image());%>" style="border: none; width: 335px; height: 335px" />
            </div>
        </div>
    </div>

    <div class="wrapper clearfix container_2">
        <!--左边栏-->
<%--        <div class="sidebar f_l">--%>
<%--            <div class="box m_10">--%>
<%--                <div class="title">热卖商品</div>--%>
<%--                <div class="content">--%>
<%--                    <ul class="ranklist">--%>
<%--                        <li class="current"><a><img--%>
<%--                                width="58px" height="58px" alt=""  /></a> <a class="p_name">图书名</a> <b >0</b></li>--%>
<%--                    </ul>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>

        <!--滑动面tab标签-->
<%--        <div class="main f_r" style="overflow: hidden">--%>
<%--            <div class="uc_title" name="showButton">--%>
<%--                <label class="current"><span>商品详情</span></label> <label><span>顾客评价()--%>
<%--					</span></label>--%>
<%--            </div>--%>
<%--            <div name="showBox">--%>
<%--                <!-- 商品详情 start -->--%>
<%--                <div >详情</div>--%>
<%--                <!-- 商品详情 end -->--%>

<%--                <!-- 顾客评论 start -->--%>
<%--                <div class="hidden comment_list box">--%>
<%--                    <div id='commentBox'></div>--%>
<%--                    <script type='text/html' id='commentTpl'>--%>
<%--                        {{each data as remark}}--%>
<%--                        <div class="item">--%>
<%--                            <div class="user">--%>
<%--                                <div class="ico">--%>
<%--                                    <a href="javascript:void(0)">--%>
<%--                                        <img src="{{remark.user.avatar}}" width="70px" height="70px" />--%>
<%--                                    </a>--%>
<%--                                </div>--%>
<%--                                <span class="blue">{{remark.user.account}}</span>--%>
<%--                            </div>--%>
<%--                            <dl class="desc">--%>
<%--                                <p class="clearfix">--%>
<%--                                    <b>评分：</b>--%>
<%--                                    <span class="grade1" layer-data={{remark.score}}></i></span>--%>
<%--                                    <span class="light_gray">{{remark.remarkTime}}</span><label></label>--%>
<%--                                </p>--%>
<%--                                <hr />--%>
<%--                                <p><b>评价：</b><span class="gray">{{remark.content}}</span></p>--%>
<%--                            </dl>--%>
<%--                            <div class="corner b"></div>--%>
<%--                        </div>--%>
<%--                        {{/each}}--%>
<%--                        <hr />--%>
<%--                        <div class="pages_bar">--%>
<%--                            <a href="javascript:void(0);" onclick="comment_ajax(1)">首页</a>--%>
<%--                            <a class="current_page" href="javascript:void(0);" onclick="comment_ajax(1)">1</a>--%>
<%--                            <a href="javascript:void(0);" onclick="comment_ajax(1)">尾页</a>--%>
<%--                            <span>当前第1页/共1页</span>--%>
<%--                        </div>--%>
<%--                    </script>--%>
<%--                </div>--%>
<%--                <!-- 顾客评论 end -->--%>
<%--            </div>--%>
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
            <a href="">关于我们</a>|<a href="">常见问题</a>|<a href="">安全交易</a>|<a
                href="">购买流程</a>|<a href="">如何付款</a>|<a href="">联系我们</a>|<a href="">合作提案</a>
        </p>
        Copyright © 2015-2021 <a class="copyys" target="_blank" href="">蜀ICP备01000010号</a>
    </div>
</div>
</body>
</html>