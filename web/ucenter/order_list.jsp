<%--
  Created by IntelliJ IDEA.
  User: 80664
  Date: 2020/9/4
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    // $(function () {
    //     goPage(1);
    //     function goPage(p){
    //         $.getJSON("order?opr=list&p="+p, function (result) {
    //             r=result;
    //             if (result.code == 200) {
    //                 //for (let order of result.data.data) {
    //                 /*let s = `<tr>
    //                 <td><a href="#" onclick="view()" class="orange" >`+ order.no + `</a></td>
    //                 <td>`+ order.orderTime + `</td>
    //                 <td>`+ order.accept + `</td>
    //                 <td>`+ order.payType + `</td>
    //                 <td>￥`+ order.totalMoney + `</td>
    //                 <td>`+ order.status + `</td>
    //             <tr>`;*/
    //                 let s=template('orderTpl',{data:result.data.data});
    //                 $("#orderData").empty();
    //                 $("#orderData").append(s);
    //
    //                 let a=[];
    //                 for(let i=1;i<=result.data.totalPage;i++){
    //                     a.push(i);
    //                 }
    //
    //                 let page=template("pagesTpl",{
    //                         totalPages:a,
    //                         currPage:result.data.currPage
    //                     }
    //                 );
    //                 $("#pages_bar").empty();
    //                 $("#pages_bar").html(page);
    //
    //                 $(".other_page").click(function(){
    //                     goPage($(this).text());
    //                 });
    //
    //                 $("#firstPage").click(function(){
    //                     goPage(1);
    //                 });
    //                 $("#lastPage").click(function(){
    //                     goPage(result.data.totalPage);
    //                 });
    //                 //}
    //             }
    //         })
    //     }
    //
    //
    // })

    // function view(id){
    //     orderId=id;
    //     $("#main").load("order_view.html");
    // }
</script>

<script id="orderTpl" type="text/html">
    {{each data as order}}
    <tr>
        <td><a href="#" onclick="view({{order.id}})" class="orange" >{{order.no}}</a></td>
        <td>{{order.orderTime}}</td>
        <td>{{order.accept}}</td>
        <td>{{order.payType==1?"支付宝":"货到付款"}}</td>
        <td>￥{{order.totalMoney}}</td>
        <td>{{order.status}}</td>
    <tr>
        {{/each}}
</script>
<script id="pagesTpl" type="text/html">
    <a href='javascript:void(0)' id="firstPage">首页</a>
    {{each totalPages as p}}
    <a  href='javascript:void(0)' {{if p==currPage}} class="current_page" {{/if}}{{if p!=currPage}} class="other_page" {{/if}}>{{p}}</a>
    {{/each}}
    <a href='javascript:void(0)' id="lastPage">尾页</a>
    <span>当前第{{currPage}}页/共{{totalPages.length}}页</span>
</script>
<div class="uc_title m_10">
    <label class="current"><span>我的订单</span></label>
</div>
<div class="box m_10">
    <table class="list_table" width="100%" cellpadding="0" cellspacing="0">
        <col width="250px" />
        <col width="140px" />
        <thead>
        <tr>
            <th>订单编号</th>
            <th>下单日期</th>
            <th>收货人</th>
            <th>支付方式</th>
            <th>总金额</th>
            <th>订单状态</th>
        </tr>
        </thead>
        <tbody id="orderData">
        <c:forEach var="order" items="${sessionScope.orders}">
            <tr>
                <th>${order.id}</th>
                <th>${order.send_time}</th>
                <th>${order.user.username}</th>
                <th>支付宝</th>
                <th >${order.totalprice}</th>
                <th>未支付</th>
            </tr>
<%--            <c:forEach var="orderItem" items="${order.orderItemBeanList}">--%>
<%--                <tr>--%>
<%--                    <td><a href="usercenter/order_view.jsp">${orderItem.order_no}</a></td>--%>
<%--                    <td><img width="40" height="40" style="overflow: hidden" src="<c:url value="/productsImage/${orderItem.main_image}" />"></td>--%>
<%--                    <td>${orderItem.address}</td>--%>
<%--                    <td>支付宝</td>--%>
<%--                    <td >${orderItem.totalprice}</td>--%>
<%--                    <td>未支付</td>--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="6" class="t_l">
                <div class='pages_bar' id='pages_bar'>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
</div>
