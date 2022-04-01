<%--
  用户个人中心首页
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>

<html>
<head>

    <title>${username}的个人中心</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/res/layui/css/layui.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/res/css/global.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/res/js/jquery-3.4.1.js"></script>

</head>
<body>

<script src="<%=request.getContextPath() %>/res/layui/layui.js"></script>
<script>
    layui.use(['element','form','layedit'],function () {
        var element = layui.element;
        var form = layui.form;
        var layedit = layui.layedit
            ,$ = layui.jquery;
    });
</script>

<div class="layui-layout layui-layout-admin">

    <!--顶部导航栏-->
    <%@ include file="../part/header.jsp"%>

    <%--左侧常驻垂直导航--%>
    <%@ include file="../part/userHomeSideBar.jsp"%>

    <%--右侧内容区域--%>
    <div class="layui-body">

        <%--面包屑导航--%>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>
                <span class="layui-breadcrumb">
                    <a><cite>欢迎您，${username}!</cite></a>
                </span>
            </legend>
        </fieldset>

        <div class="layui-bg-gray" style="padding: 30px;">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-panel" style="padding: 30px 30px 50px;">

                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

</body>
</html>

