<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        //商品信息
        $(function (){
            <%--$.ajax({--%>
            <%--    url:getPath()+"/cartServlet",--%>
            <%--    data:{--%>
            <%--        opr:"showCart"--%>
            <%--    },--%>
            <%--    type:"get",--%>
            <%--    dataType:"json",--%>
            <%--    success:function (result){--%>
            <%--        if(result.stutasCode == 200){--%>
            <%--            for(var i in result.data){--%>
            <%--                var key = result.data[i];--%>
            <%--                var id = key.id;--%>
            <%--                var main_image = key.main_image;--%>
            <%--                var subtitle = key.subtitle;--%>
            <%--                var price = key.price;--%>
            <%--                var quantity = key.quantity;--%>
            <%--                var totalprice = key.totalprice;--%>
            <%--                var product_id = key.product_id;--%>
            <%--                var html = "<tr>\n" +--%>
            <%--                                "      <td >\n" +--%>
            <%--                                "       <input onclick=\"radio('"+id+"')\" class=\"radio\" style=\"margin-left:5px\" type=\"checkbox\" value=\""+id+"\"/>\n" +--%>
            <%--                                "      </td>                 " +--%>
            <%--                    "                    <td>\n" +--%>
            <%--                    "                        <a class=\"blue\"  href=\""+getPath()+"/productDetail.jsp?id="+product_id+"\"><img style=\"margin-left:10px\"  src=\"<c:url value="/productsImage/"/>"+main_image+"\" width=\"66px\" height=\"66px\"  /></a>\n" +--%>
            <%--                    "                    </td>\n" +--%>
            <%--                    "                    <td class=\"t_l\">\n" +--%>
            <%--                    "                        <a style=\"margin-left:5px\"  class=\"blue\"  href=\""+getPath()+"/productDetail.jsp?id="+product_id+"\">"+subtitle+"</a>\n" +--%>
            <%--                    "                    </td>\n" +--%>
            <%--                    "                    <td >\n" +--%>
            <%--                    "                        <b style=\"margin-left:20px\">￥"+price+"</b>\n" +--%>
            <%--                    "                    </td>\n" +--%>
            <%--                    "                    <td>\n" +--%>
            <%--                    "                        <div class=\"num\">\n" +--%>
            <%--                    "                            <input style=\"margin-left:24px\" type=\"text\"  value=\""+quantity+"\" />\n" +--%>
            <%--                    "                        </div>\n" +--%>
            <%--                    "                    </td>\n" +--%>
            <%--                    "                    <td><b style=\"margin-left:20px\" class=\"red2\"\n" +--%>
            <%--                    "                    >￥"+totalprice+"</b></td>\n" +--%>
            <%--                    "                    <td>\n" +--%>
            <%--                    "                        <a style=\"margin-left:50px\" href=\""+getPath()+"/cartServlet?id="+id+"&opr=del\">删除</a>\n" +--%>
            <%--                    "                    </td>\n" +--%>
            <%--                    "                </tr>";--%>
            <%--                $("#medlarList").append(html);--%>
            <%--            }--%>
            <%--        }--%>

            <%--    }--%>
            <%--});--%>
            //全选
            $("#selectAll").click(function (){
                var all = $(this).prop("checked");
                var radio = $(".radio").prop("checked",all);
                if($("input:checkbox[name='a']:checked").length < 0){
                    $("#pay").attr("display","none");
                }else{
                    $("#pay").attr("display","block");
                }
                if($("input:checkbox[name='a']:checked").length >= $("input:checkbox[name='a']:checked").length){
                    radio.attr("checked","checked");
                }
            });
        });

        //单选
        // function radio(id){
        //     //存放当前选中的复选框的值
        //     var check_value;
        //     var radioArray = document.getElementsByClassName("radio");
        //     for(i in radioArray){
        //         if(radioArray[i].checked){
        //             check_value = radioArray[i].value;
        //             $.get(getPath()+"/cartServlet?opr=count&all=countOne&cartId="+id,
        //                   function (result){
        //                       $("#totalmoney").text(result);
        //                   });
        //         }
        //     }
        //     $.get(getPath()+"/cartServlet?opr=count&all=countOneNo&cartId="+id,
        //         function (){
        //             $("#totalmoney").text("");
        //         });
        // }
    </script>
</head>
<body class="second">
<div  class="brand_list container_2">
    <div ></div>
    <div class="wrapper clearfix">
        <div class="position mt_10">
            <span>您当前的位置：</span> <a href="<c:url value="/index.jsp" />"> 首页</a> » 购物车
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
                    <th><input id="selectAll" type="checkbox" />全选</th>
                    <th>图片</th>
                    <th>商品名称</th>
                    <th>单价</th>
                    <th>数量</th>
                    <th>小计</th>
                    <th class="last">操作</th>
                </tr>
                </thead>
                <tbody id="medlarList">

                    <c:choose>
                        <c:when test="${empty sessionScope.session_cart or fn:length(sessionScope.session_cart.cartItems) == 0}">
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="cartItem" items="${sessionScope.session_cart.cartItems}" >
                                <tr>
                                    <th><input name="a" class="radio" type="checkbox" /></th>
                                    <th><img src="<c:url value="/productsImage/${cartItem.productBean.main_image}"/>"></th>
                                    <th>${cartItem.productBean.subtitle}</th>
                                    <th>${cartItem.productBean.price}</th>
                                    <th>${cartItem.count}</th>
                                    <th>${cartItem.subtotal}</th>
                                    <th class="last"><a href="<c:url value="/SessionCartServlet?method=removeOne&id=${cartItem.productBean.id}"/>">删除</a></th>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
<%--                <tr>--%>
<%--                    <td>--%>
<%--                        <input type="checkbox"/>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <img  src="<c:url value="/productsImage/" />" width="66px" height="66px"  />--%>
<%--                    </td>--%>
<%--                    <td class="t_l">--%>
<%--                        <a class="blue" >subtitle</a>--%>
<%--                    </td>--%>
<%--                    <td>￥--%>
<%--                        <b>1</b>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <div class="num">--%>
<%--                            <a class="reduce" href="javascript:reduce()" >-</a>--%>
<%--                            <input class="tiny" type="text"  value="dadafa" />--%>
<%--                            <a class="add" href="javascript:add()">+</a>--%>
<%--                        </div>--%>
<%--                    </td>--%>
<%--                    <td>￥<b class="red2"--%>
<%--                    ></b></td>--%>
<%--                    <td>--%>
<%--                        <a href="javascript:del()">删除</a>--%>
<%--                    </td>--%>
<%--                </tr>--%>

                <tr class="stats">
                    <td colspan="8">金额总计（不含运费）：￥<b class="orange" id='totalmoney'>${sessionScope.session_cart.totalPrice}</b></td>
                </tr>

                </tbody>
                <tfoot>
                <tr class="stats">
                    <td colspan="8"><a href="<c:url value="/SessionCartServlet?method=clearCart" />">清空购物车</a></td>
                </tr>
                <tr>
                    <td colspan="2" class="t_l"></td>
                    <td colspan="6" class="t_r"><a class="btn_continue" href="<c:url value="/index.jsp" />">继续购物</a>
                        <a id="pay" class="btn_pay" href="<c:url value="/order?opr=addOrderData" />">去结算</a></td>
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
