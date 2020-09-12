<%--
  Created by IntelliJ IDEA.
  User: 80664
  Date: 2020/9/2
  Time: 0:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8" />
    <title>核对订单信息</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css" />" />
    <script src="<c:url value="/js/jquery.js" />"></script>
    <script>
    //获取项目的根路径
    function getPath(){
    var path = window.location.pathname;
    var index = path.indexOf("/",1);
    var rootPath = path.substring(0,index);
    return rootPath;
    }
    //商品信息
    <%--$(function (){--%>
    <%--$.ajax({--%>
    <%--url:getPath()+"/order",--%>
    <%--data:{--%>
    <%--opr:"showOneOrder"--%>
    <%--},--%>
    <%--type:"get",--%>
    <%--dataType:"json",--%>
    <%--  success:function (result) {--%>
    <%--      if (result.stutasCode == 200) {--%>
    <%--          console.log("cg");--%>
    <%--          for (var i in result.data) {--%>
    <%--              var key = result.data;--%>
    <%--              // var main_image = key.orderItemBeanList[i].product.main_image;--%>
    <%--              var subtitle = key.subtitle;--%>
    <%--              var price = key.id;--%>
    <%--              var totalprice = key.totalprice;--%>
    <%--              console.log(1);--%>
    <%--              var html = "<tr>\n" +--%>
    <%--                  "                                <td>\n" +--%>
    <%--                  &lt;%&ndash;"                                    <img src='<c:url value="/productsImage/" />" + main_image + "' width=\"66px\" height=\"66px\"/>\n" +&ndash;%&gt;--%>
    <%--                  "                                </td>\n" +--%>
    <%--                  "                                <td class=\"t_l\">\n" +--%>
    <%--                  "                                    <a  class=\"blue\" target=\"_blank\">" + subtitle + "</a>\n" +--%>
    <%--                  "                                </td>\n" +--%>
    <%--                  "                                <td>￥<b>" + price + "</b>\n" +--%>
    <%--                  "                                </td>\n" +--%>
    <%--                  "                                <td>1</td>\n" +--%>
    <%--                  "                                <td>￥" + totalprice + "<b class=\"red2\"></b></td>\n" +--%>
    <%--                  "                            </tr>";--%>
    <%--              $("#content").append(html);--%>
    <%--          }--%>
    <%--      }--%>
    <%--  }--%>
    <%--    });--%>
<%--     }--%>
<%--   });--%>

    // $.ajax(
    //     {
    //        url: "order",
    //        data: {
    //            opr:"sum",
    //        },
    //         dataType: "json",
    //         success:function (result){
    //            $("#sum").text(result);
    //            $("#final_sum").text(result);
    //         }
    //     });
    // });


    </script>
</head>
<body class="second">
<div class="brand_list container_2">
    <div></div>
    <div class="wrapper clearfix">
        <div class="position mt_10">
            <span>您当前的位置：</span> <a href="<c:url value="/index.jsp" />"> 首页</a> » 填写核对订单信息
        </div>
        <div class="myshopping m_10">
            <ul class="order_step">
                <li class="current_prev"><span class="first"><a
                        href='javascript:history.go(-1);'>1、查看购物车</a></span></li>
                <li class="current"><span>2、填写核对订单信息</span></li>
                <li class="last"><span>3、成功提交订单</span></li>
            </ul>
        </div>

        <form method='post' name='order_form'>
            <input type="hidden" name="opr" value="add" />
            <div class="cart_box m_10">
                <div class="title">填写核对订单信息</div>
                <div class="cont">

                    <!--地址管理 开始-->
                    <div class="prompt_4 m_10" id='address_often'>
                        <strong>常用收货地址</strong>
                        <ul class="addr_list">
                            <c:forEach items="${address}" var="address">
                                <li><label><input class="radio" name="order.address.id"
                                                  type="radio" value="${address.id}"
                                                  <c:if test="${address.isdefault eq '1'}">checked</c:if> />${address.accept}&nbsp;&nbsp;&nbsp;&nbsp;${address.province}
                                        ${address.city} ${address.area} ${address.address} </label></li>
                            </c:forEach>
                            <li><label>
                                <input class="radio" name="one"  type="radio" value="四川省泸州市合江县先滩镇" />四川省泸州市合江县先滩镇
                                </label>
                            </li>
                            <li><label>
                                <input class="radio" name="one"  type="radio" value="四川省成都市华阳街道" />四川省成都市华阳街道
                            </label>
                            </li>

                        </ul>
                    </div>
                </div>
                    <!--地址管理 结束-->
                    <!--支付方式 开始-->
                    <div class="wrap_box" id='paymentBox'>
                        <h3>
                            <span class="orange">支付方式</span>
                        </h3>

                        <table width="100%" class="border_table" id='payment_form'>
                            <col width="200px" />
                            <col />
                            <tr>
                                <th><label><input class="radio" name="paytype"
                                                  alt="0" title="支付宝" type="radio" value="1" />支付宝</label></th>
                                <td>支付手续费：没有手续费</td>
                            </tr>
                            <tr>
                                <th><label><input class="radio" name="paytype"
                                                  alt="0" title="货到付款" type="radio" value="2" />货到付款</label></th>
                                <td>支付手续费：￥</td>
                            </tr>
                        </table>
                    </div>
                    <!--支付方式 结束-->
                    <!--购买清单 开始-->
                    <div class="wrap_box">
                        <h3>
                            <span class="orange">购买的商品</span>
                        </h3>

                        <table width="100%" class="cart_table t_c">
                            <col width="115px" />
                            <col />
                            <col width="80px" />
                            <col width="80px" />
                            <col width="80px" />

                            <thead>
                            <tr>
                                <th>图片</th>
                                <th>商品名称</th>
                                <th>单价</th>
                                <th>数量</th>
                                <th class="last">小计</th>
                            </tr>
                            </thead>

                            <tbody id="content">
                                <c:choose>
                                    <c:when test="${empty sessionScope.session_cart or fn:length(sessionScope.session_cart.cartItems) == 0}">
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="cartItem" items="${sessionScope.session_cart.cartItems}" >
                                            <tr>
                                                <th><img src="<c:url value="/productsImage/${cartItem.productBean.main_image}"/>"></th>
                                                <th>${cartItem.productBean.subtitle}</th>
                                                <th>${cartItem.productBean.price}</th>
                                                <th>${cartItem.count}</th>
                                                <th>${cartItem.subtotal}</th>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
<%--                                <tr>--%>
<%--                                <tr>--%>
<%--                                    <th>${order.id}</th>--%>
<%--                                    <th>提交时间:${order.send_time}</th>--%>
<%--                                    <th>下单用户:${order.user.username}</th>--%>
<%--                                    <th>支付类型:支付宝</th>--%>
<%--                                    <th>订单总额:${order.totalprice}</th>--%>
<%--                                    <th>未支付</th>--%>
<%--                                </tr>--%>
<%--                                    <td>--%>
<%--                                        <img src="<c:url value="/productsImage/" />"<%%> width="66px" height="66px"/>--%>
<%--                                    </td>--%>
<%--                                    <td class="t_l">--%>
<%--                                        <a class="blue" target="_blank">${orderItem.subtitle}</a>--%>
<%--                                    </td>--%>
<%--                                    <td>￥<b>${orderItem.price}</b>--%>
<%--                                    </td>--%>
<%--                                    <td></td>--%>
<%--                                    <td>￥<b class="red2"></b>${orderItem.totalprice}</td>--%>
<%--                                </tr>--%>
                            <!-- 商品展示 结束-->
                            </tbody>
                        </table>
                    </div>
                    <!--购买清单 结束-->
                </div>
            </div>

            <!--金额结算-->
            <div class="cart_box" id='amountBox'>
                <div class="cont_2">
<%--                    <hr class="dashed" />--%>
                    <strong>结算信息</strong>
                    <div class="pink_box gray m_10">
                        <table width="100%" class="form_table t_l">
                            <col width="220px" />
                            <col />
                            <col width="250px" />
                            <tr>
                                <td class="t_r">
                                    <b class="price f14">
                                        应付总额：<span class="red2">￥${sessionScope.totalprice}<b id='final_sum'>

                                    </b>
                                    </span>元
                                </b></td>
                            </tr>
                        </table>
                    </div>
                    <p class="m_10 t_r">
                        <a href="<c:url value="/order?opr=showAllOrder" />"><input type="button" class="submit_order" /></a>
                    </p>
                </div>
            </div>
        </form>

    </div>
    <div></div>
</div>
</body>
</html>

