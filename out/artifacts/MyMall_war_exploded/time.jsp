<%--
  Created by IntelliJ IDEA.
  User: 80664
  Date: 2020/8/7
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery.js"></script>
    <script>
        window.onload = function(){
            setInterval(function () {
                fdate();
            },1000);

            }
        }
        function fdate() {
            var nowtime = document.getElementById("time");
            var date = new Date();
            var year = date.getFullYear();
            var month = date.getMonth();
            var day = date.getDay();
            var hour = date.getHours();
            var minute = date.getMinutes();
            var second = date.getSeconds();
            var time = year+"-"+add((month+1))+"-"+add(day)+" "+add(hour)+add(minute)+add(second);
            nowtime.innerHTML = time;
        }
          function add(str) {
              var time;
              str>10?time=str:time="0"+str;
              return time;
          }
    </script>
</head>
<body>
  <div id="time"></div>
</body>
</html>
