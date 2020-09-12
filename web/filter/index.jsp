<%@ page import="entity.Mmall_UserBean" %>
<%@ page import="dao.BaseDao" %>
<%@ page import="entity.Mmall_orderBean" %>
<%@ page import="org.apache.commons.dbutils.handlers.BeanHandler" %>
<%@ page import="service.OrderDaoService" %>
<%@ page import="org.apache.commons.dbutils.QueryRunner" %>
<%@ page import="com.chinasofti.jdbc.TxQueryRunner" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<base href="${base}/" />
	<title>个人中心_${site}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/index.css" />" />
	<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
	<script type="text/javascript">
		//用于用户中心左边菜单栏的选择项的样式
		function setSelectedClass(url){
			$('div.cont ul.list li a[href~="'+url+'"]').parent().addClass("current");
		}

	</script>
	<%
		BaseDao<Mmall_UserBean> dao = new BaseDao();
		QueryRunner qr = new TxQueryRunner();
		String userName =(String) request.getSession().getAttribute("userName");
		Mmall_UserBean user = dao.selectOne("select * from mmall_user where username=?",Mmall_UserBean.class,userName);
		Mmall_orderBean orderBean = qr.query("select * from mmall_order where user_id = ?",new BeanHandler<>(Mmall_orderBean.class),user.getId());
	%>
</head>
<body class="index">
<div class="ucenter container">
	<jsp:include page="../header.jsp" />
	<jsp:include page="../navbar.jsp" />

	<div class="position">
		您当前的位置： <a href="">首页</a> » <a href="">我的账户</a>
	</div>
	<div class="wrapper clearfix">

		<div class="main f_r">

			<div class="userinfo_bar">
				<b class="f14">您好，${sessionScope.userName} 欢迎回来!</b>
			</div>
			<div class="box clearfix">
				<h3>用户信息</h3>
				<dl class="userinfo_box">
					<dt>
						<a class="ico"><img style="overflow: hidden" id="user_ico_img" src="<c:url value="/productsImage/tiger4.jpg" />" width="100"
											height="100" alt="" /></a>
						<a class="blue" href="#">修改头像</a>
					</dt>
					<dd>
						<table width="100%" cellpadding="0" cellspacing="0">
							<col width="350px" />
							<tr>

								<a class="blue" href="<c:url value="/ucenter/order_list.jsp" />">进入订单列表</a></td>
							</tr>
						</table>

<%--						<div class="stat">--%>
<%--								<span>待评价商品：<label>(<b class="red2">1</b>)--%>
<%--								</label></span> <span>待付款订单：<label>(<b class="red2">0</b>)--%>
<%--								</label></span> <span>待确认收货：<label>(<b class="red2"><a href="">0</a></b>)</label></span>--%>
<%--						</div>--%>
					</dd>
				</dl>
			</div>
			<h3 class="bg">我的订单</h3>
			<div class="box m_10">
				<table class="list_table" width="100%" cellpadding="0"
					   cellspacing="0">
					<col width="140px" />

					<c:forEach var="order" items="${sessionScope.orders}">
						<tr>
							<th>${order.id}</th>
							<th>${order.send_time}</th>
							<th>${order.user.username}</th>
							<th>支付宝</th>
							<th >${order.totalprice}</th>
							<th>未支付</th>
						</tr>
					<c:forEach var="orderItem" items="${order.orderItemBeanList}">
						<tr>
							<td><a href="usercenter/order_view.jsp">${orderItem.order_no}</a></td>
							<td><img width="40" height="40" style="overflow: hidden" src="<c:url value="/productsImage/${orderItem.main_image}" />"></td>
							<td>${orderItem.address}</td>
							<td>支付宝</td>
							<td >${orderItem.totalprice}</td>
							<td>未支付</td>
						</tr>
					</c:forEach>
					</c:forEach>
					<tfoot>
					<tr>
						<td colspan="6" class="t_r"><a class="blue" href="<c:url value="/ucenter/order_list.jsp" />">更多订单&gt;&gt;</a></td>
					</tr>
					</tfoot>
				</table>
			</div>

		</div>
	</div>
	<jsp:include page="../help.jsp" />
	<jsp:include page="../footer.jsp" />
</div>
</body>
</html>
