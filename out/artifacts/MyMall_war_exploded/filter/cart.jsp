<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="entity.ProductBean" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 80664
  Date: 2020/8/8
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title ${sessionScope.userName}>购物车</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="/css/index.css"/>" />
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script>
        //获取项目的根路径
        function getPath(){
            var path = window.location.pathname;
            var index = path.indexOf("/",1);
            var rootPath = path.substring(0,index);
            return rootPath;
        }
        $(function (){
            $.ajax({
                url:getPath()+"/cartServlet",
                data:{
                    opr:"showCart"
                },
                success:function (result){
                    if(result != null){
                        console.log(result);
                    }else{
                        console.log("no");
                    }
                }
            },"json");
        });
    </script>
</head>
<body class="second">
<div class="brand_list container_2">
    <div ></div>
    <div class="wrapper clearfix">
        <div class="position mt_10">
            <span>您当前的位置：</span> <a > 首页</a> » 购物车
        </div>
        <div class="myshopping m_10">
            <ul class="order_step">
                <li class="current"><span class="first">1、查看购物车</span></li>
                <li><span>2、填写核对订单信息</span></li>
                <li class="last"><span>3、成功提交订单</span></li>
            </ul>
        </div>
        <form id="form1" method="post">
            <input type="hidden" name="opr" value="initadd" />
            <table width="100%" class="cart_table m_10">
                <col width="65px" />
                <col width="115px" />
                <col width="400px" />
                <col width="80px" />
                <col width="80px" />
                <col width="80px" />
                <caption>查看购物车</caption>
                <thead>
                <tr>
                    <th><input type="checkbox" id="selAll">&nbsp;全选</th>
                    <th>图片</th>
                    <th>商品名称</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>小计</th>
                    <th class="last">操作</th>
                </tr>
                </thead>
                <tbody id="bookList">

                <%
                    List<ProductBean> productList = null;
                    if(session.getAttribute("products") != null){
                        productList = (List<ProductBean>)session.getAttribute("products");
                    }
                %>

                <%
                    if(productList != null){

                        for(ProductBean p:productList){
                %>
                <tr>
                    <td><input type="checkbox"
                    /></td>
                    <td><img  src="productsImage\<%=p.getMain_image()%>" width="66px"
                              height="66px"  /></td>
                    <td class="t_l"><a
                            class="blue" ><%=p.getSubtitle()%></a></td>
                    <td>￥<b
                    >1</b></td>
                    <td>
                        <div class="num">
                            <a class="reduce" href="javascript:void(0)" >-</a> <input
                                class="tiny"
                                type="text"  value="<%=p.getCount()%>"> <a
                                class="add" href="javascript:void(0)"
                        >+</a>
                        </div>
                    </td>
                    <td>￥<b class="red2"
                    ><%=p.getPrice()%></b></td>
                    <td><a href="javascript:void(0)"
                    >删除</a></td>
                </tr>

                <%}}%>
                <tr class="stats">
                    <td colspan="8">金额总计（不含运费）：￥<b class="orange"
                                                   id='totalmoney'></b></td>
                </tr>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="2" class="t_l"></td>
                    <td colspan="6" class="t_r"><a class="btn_continue" href="">继续购物</a>
                        <a class="btn_pay" href="javascript:finish();">去结算</a></td>
                </tr>
                </tfoot>
            </table>
        </form>
<%--        <div class="box">--%>
<%--            <div class="title">热门商品推荐</div>--%>
<%--        </div>--%>
<%--        <ul id="scrollpic" class="prolist">--%>
<%--            <li target="_blank"> <img width="98px" height="106px">--%>
<%--                </a>--%>
<%--                <p class="pro_title">--%>
<%--                    <a>图书</a>--%>
<%--                </p>--%>
<%--                <p class="brown">--%>
<%--                    ￥<b>0</b>--%>
<%--                </p></li>--%>
<%--        </ul>--%>
    </div>
    <div ></div>
</div>
</body>
</html>