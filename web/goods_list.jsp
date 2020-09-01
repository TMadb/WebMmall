<%@ page import="entity.ProductBean" %>
<%@ page import="java.util.List" %>
<%@ page import="service.serviceImplement.ProductServiceImplement" %><%--
  Created by IntelliJ IDEA.
  User: 80664
  Date: 2020/8/8
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    ProductServiceImplement productServiceImplement = new ProductServiceImplement();
    List<ProductBean> products = productServiceImplement.selectAllProducts();
%>
<head>
    <title>goods_list</title>
    <link type="text/css" rel="stylesheet" href="css/index.css" >
    <script type="text/javascript" src="js/jquery.js"></script>

    <script>
        var t;
        $(function () {
            t = joinCart_list(productId);
        });
        function joinCart_list(productId) {
            location.href = "addCart?productId"+productId;
        }
    </script>
</head>
<body class="index">
<div class="container">
    <div ></div>
    <div ></div>
    <div ></div>
    <div class="wrapper clearfix container_2">
        <div class="sidebar f_l">
            <!--销售排行-->
            <div class="box m_10">
                <div class="title">销售排行榜</div>
                <div class="content">
                    <ul class="ranklist" id='ranklist'>
                        <li><span>1</span><a class="p_name" href="" target="_blank">图书名</a></li>
                    </ul>
                </div>
            </div>
            <!--销售排行-->
        </div>

        <div class="main f_r" style="margin-top: 200px">
            <ul class="display_list clearfix m_10">

                <%       for(ProductBean product:products){ %>
                <li class="clearfix win">
                    <div class="pic">
                        <a href="" target="_blank"><img src="productsImage\<%=product.getMain_image()%>" width="200" height="200" /></a>
                    </div>
                    <h3 class="title">
                        <a class="p_name" href="" target="_blank"><%=product.getSubtitle()%></a>
                        <span>总销量：0(0人评论
                                    )</span><span class="grade" lay-data=""><i style="width: 56px"></i></span>
                    </h3>
                    <div class="handle">
                        <label class="btn_gray_m"><img src="images/front/ucenter/shopping.gif" width="15"
                                                       height="15" /><input id="addToCar" type="button" value="加入购物车"
                                                                            onclick="joinCart_list(<%=product.getId()%>);" /></label>
                    </div>
                    <div class="price">
                        ￥0<s>￥><%=product.getPrice()%></s>
                    </div>
                </li>

                <%}%>
            </ul>
            <div class='pages_bar'>
                <a href='javascript:void(0)' onclick="goPage(1)">首页</a>
                <a>1</a>
                <a href='javascript:void(0)'>尾页</a>
                <span>当前第1页/共1页</span>
            </div>
        </div>
    </div>
    <div ></div>
    <div></div>
</div>
</body>
</html>
