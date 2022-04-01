<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>素材详情-${material.title}</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/res/layui/css/layui.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/res/css/global.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/res/js/jquery-3.4.1.js"></script>
    <style type="text/css">
        div img{
            display: inline-block;
            height: auto;
            max-width: 100%;
        }
    </style>
    <script type="text/javascript">
        function like() {
            var arr = (window.location.href).split('/');
            var materialId = arr[5];
            console.log(materialId);
            $.get(
                "<%=request.getContextPath()%>/material/${materialId}/like.do",
                function (resp){
                    if(resp.code==0){
                        alert(resp.msg);
                    }else{
                        alert(resp.msg);
                    }
                },
                "json"
            )

        }
    </script>
    <script src="<%=request.getContextPath() %>/res/layui/layui.js"></script>
    <script>
        layui.use(['element','form'],function () {
            var element = layui.element;
            var form = layui.form;
        });
    </script>
</head>
<body>
<%--顶部导航栏--%>
<%@ include file="../../part/header.jsp" %>

<div class="layui-container">
    <hr>
    <div class="layui-row layui-col-space20" style="padding: 10px; height: 95%">
        <div class="layui-col-md1"></div>
        <div class="layui-col-md7">
            <%--面包屑导航--%>
            <fieldset class="layui-elem-field layui-field-title">
                <legend>
                    <span class="layui-breadcrumb">
                        <a href="<%=request.getContextPath()%>/index.do">首页</a>
                        <a href="<%=request.getContextPath()%>/material/index.do">素材专区</a>
                        <a><cite>素材详情</cite></a>
                    </span>
                </legend>
            </fieldset>
            <div class="layui-card" style="height: auto;">
                <blockquote class="layui-elem-quote">
                    ${material.categoryFirst} -
                        <a href="<%=request.getContextPath()%>/material/search.do?search-mode=一级学科&search-keyword=${material.categorySecond}">${material.categorySecond}</a>

                </blockquote>
                <div class="layui-card-header" style="padding: 10px 20px 10px">
                    <div style="font-size: x-large; color: black; font-weight: bold">
                        ${material.title}
                    </div>
                    <hr class="layui-border-cyan">
                </div>
                <br>
                <div class="layui-card-body" style="padding: 10px 30px 50px">
                    ${material.content}
                </div>

                <button onclick="like()" type="button" class="layui-btn layui-btn-radius layui-btn-primary" style="margin-left: 20px;margin-bottom: 10px">
                    <i class="layui-icon layui-icon-praise"> 点赞</i>
                </button>

            </div>

        </div>

        <div class="layui-col-md3">

            <fieldset class="layui-elem-field layui-field-title">
                <legend>
                    <span class="layui-breadcrumb">
                        <a><cite>素材来源</cite></a>
                    </span>
                </legend>
            </fieldset>
            <div class="layui-panel">
                <div style="padding: 20px;line-height: 2em">
                    <i class="layui-icon layui-icon-username"> 用户：${userProfile.userName}<br></i>
                    <i class="layui-icon layui-icon-time"> 发布于 <fmt:formatDate value="${material.updateTime}" type="date" pattern="yyyy-MM-dd HH:mm"/></i><br>
                </div>
            </div>

            <br>
            <fieldset class="layui-elem-field layui-field-title">
                <legend>
                    <span class="layui-breadcrumb">
                        <a><cite>案例信息</cite></a>
                    </span>
                </legend>
            </fieldset>
            <div class="layui-panel">
                <div style="padding: 20px;line-height: 2em">
                    <i class="layui-icon layui-icon-read"> 访问量 ${material.visitCount}</i><br>
                    <i class="layui-icon layui-icon-praise"> 收到了 ${material.likeCount} 个赞</i><br>
                </div>
            </div>

            <br>




            <%--<fieldset class="layui-elem-field layui-field-title">
                <legend>
                    <span class="layui-breadcrumb">
                        <a><cite>猜你喜欢</cite></a>
                    </span>
                </legend>
            </fieldset>
            <div class="layui-card">
                <div class="layui-card-header">
                    <b>
                        推荐文章1
                    </b>
                </div>
                <div class="layui-card-body">
                    文章内容1。
                </div>
            </div>
            <div class="layui-card">
                <div class="layui-card-header">
                    <b>
                        推荐文章2
                    </b>
                </div>
                <div class="layui-card-body">
                    文章内容2。
                </div>
            </div>
            <div class="layui-card">
                <div class="layui-card-header">
                    <b>
                        推荐文章3
                    </b>
                </div>
                <div class="layui-card-body">
                    文章内容3。
                </div>
            </div>--%>
        </div>
        <div class="layui-col-md1"></div>
    </div>
</div>


</body>
</html>
