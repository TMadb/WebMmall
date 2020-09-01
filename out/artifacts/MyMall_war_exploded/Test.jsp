<%@ page import="entity.Mmall_UserBean" %><%--
  Created by IntelliJ IDEA.
  User: 80664
  Date: 2020/8/10
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
</head>
<body>
  <%
      session.setAttribute("sessiontestkey","sessiontest");
      request.setAttribute("requesttestkey","requesttest");
      application.setAttribute("applicationtestkey","applicationtest");
      pageContext.setAttribute("pageContexttestkey","pageContexttest");
      Mmall_UserBean userBean = new Mmall_UserBean();
      userBean.setUsername("ljw");
      userBean.setPassword("525864");
      userBean.setPhone("13419178888");
      userBean.setEmail("806648464@qq.com");

  %>

  ${sessiontestkey}<br />
  ${pageContexttestkey}<br />
  ${applicationtestkey}<br />
  ${requesttestkey}<br />


  ${userBean.username}

  ${10 lt 8}<br />
</body>
</html>
