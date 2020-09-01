<%--
  Created by IntelliJ IDEA.
  User: 80664
  Date: 2020/8/26
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商品分类</title>
    <link rel="stylesheet" href="<c:url value="css/index.css" />" type="text/css" />
    <script src="<c:url value="js/jquery.js" />"></script>
    <script>
        $(function () {
            $.ajax({url:"showProducts",
                    type:"get",
                    data:{
                        opr:"showproductBysort",
                        condition:"红枸杞"
                    },
                    dataType:"json",
                    success:function (result) {
                        for(var i = 0;i<result.length;i++){
                            var product = result[i];
                            var html = "<li class=\"clearfix win\">";
                            html+= "<div class=\"pic\">";
                            html+="<a href=\"productDetail.jsp?id="+product.id+"\" target=\"_blank\"><img src=\"productsImage\\"+product.main_image+"\" width=\"200\" height=\"200\" /></a></div>";
                            html+="<h5 class=\"title\">";
                            html+="<a class=\"p_name\" href=\"productDetail.jsp?id="+product.id+"\" target=\"_blank\">"+product.subtitle+"</a>";
                            html+="<span style='font-size: 12px'>总销量：0(0人评论)</span>" ;
                            html+="<span class=\"grade\" lay-data=\"\"><i style=\"width: 50px\"></i></span></h5>";
                            html+="<div class=\"handle\">";
                            html+="<a title=\"\" href=\"productDetail.jsp?id="+product.id+"\" ><label class=\"btn_gray_m\"><img src=\"images/front/ucenter/shopping.gif\" width=\"15\" height=\"15\" /><input type=\"button\" value=\"加入购物车\"  /></label></a></div>";
                            html+="<div style='font-size: 14px' class=\"price\">\n" +
                                "                       ￥"+product.price+"<s>￥"+product.price+1+"</s>\n" +
                                "                   </div>\n" +
                                "               </li>";
                            $("#show").append(html);
                        }
                    }
                }
            )
        });

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
                        <li><span>1</span><a class="p_name" href="" target="_blank">百瑞源</a></li>
                        <li><span>2</span><a class="p_name" href="" target="_blank">杞里香</a></li>
                        <li><span>3</span><a class="p_name" href="" target="_blank">裕杞福</a></li>
                        <li><span>4</span><a class="p_name" href="" target="_blank">神农</a></li>
                        <li><span>5</span><a class="p_name" href="" target="_blank">同仁堂</a></li>
                    </ul>
                </div>
            </div>
            <!--销售排行-->
        </div>

        <div class="main f_r" style="margin-top: 200px">
            <ul id="show" class="display_list clearfix m_10">

            </ul>
            <!--			<div class='pages_bar'>-->
            <nav aria-label="...">
                <ul class="pagination">

                    <!--						<li class="active"><a href="#">1 <span class="sr-only">(current)</span></a></li>-->
                    <!--						<li ><a href="#">2 <span class="sr-only">(current)</span></a></li>-->
                    <!--						<li ><a href="#">3 <span class="sr-only">(current)</span></a></li>-->
                    <!--						<li ><a href="#">4 <span class="sr-only">(current)</span></a></li>-->

                </ul>
            </nav>
            <!--			</div>-->
        </div>
    </div>
    <div ></div>
    <div></div>
</div>
</body>
</html>