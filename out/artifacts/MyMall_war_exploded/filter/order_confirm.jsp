<%@ page import="entity.Mmall_UserBean" %>
<%@ page import="org.apache.commons.dbutils.QueryRunner" %>
<%@ page import="com.chinasofti.jdbc.TxQueryRunner" %>
<%@ page import="dao.BaseDao" %>
<%@ page import="entity.Mmall_orderBean" %><%--
  Created by IntelliJ IDEA.
  User: 80664
  Date: 2020/9/2
  Time: 0:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>提交订单-小牛枸杞店</title>
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
        $(function (){
            // $.ajax({
            //     url:getPath()+"/order",
            //     data:{
            //         opr:"showAllOrder"
            //     },
            //     type:"get",
            //     dataType:"json",
            //     success:function (result){
            //         if(result.stutasCode == 200){
            //             console.log("cg");
            //         }
            //     }
            // });
        });
    </script>
</head>

<body class="second">
<div class="brand_list container_2">
    <div></div>
    <div class="wrapper clearfix">
        <div class="position mt_10">
            <span>您当前的位置：</span> <a href="<c:url value="/index.jsp" />"> 首页</a> » 成功提交订单
        </div>
        <div class="myshopping m_10">
            <ul class="order_step">
                <li><span class="first">1、查看购物车</span></li>
                <li class="current_prev"><span>2、填写核对订单信息</span></li>
                <li class="last_current"><span>3、成功提交订单</span></li>
            </ul>
        </div>

        <div class="cart_box m_10">
            <div class="title">成功提交订单</div>
            <div class="cont">
                <p class="order_stats">
                    <img width="48px" height="51px" alt="" src="images/front/right.gif"><strong
                        class="f14">订单已提交</strong>
                </p>

                <div class="stats_box">
                    <h3>订单信息</h3>
                    <table width="100%" class="form_table t_l orange">
                        <col width="75px" />
                        <col />

                        <tbody>
                        <tr>
                            <th>订单编号：</th>

                            <td class="f18 bold red2" >${sessionScope.order.id}</td>
                        </tr>
                        <tr>
                            <th>订单金额：</th>
                            <td class="f18 bold red2">￥<b>${sessionScope.order.totalprice}</b></td>
                        </tr>
                        <tr>
                            <th>支付方式：</th>
                            <td class="f18 bold red2">支付宝</td>
                        </tr>
                        </tbody>
                    </table>

                    <form action='<c:url value="/filter/index.jsp" />' method='post' target='_blank'>
                        <input type="hidden" name="opr">
                        <input type="hidden" name="id">
                        <input type="hidden" name="orderNo">
                        <input class="submit_pay" type="submit" value="立即支付" />
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div></div>
</div>
</body>
</html>
