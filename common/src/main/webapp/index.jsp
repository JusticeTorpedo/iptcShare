<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    //等价于http://localhost:8080/common/
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":"
            + request.getServerPort()
            + request.getContextPath() + "/";
%>
<html>
<head>
    <meta charset="utf-8">
    <title>课程思政资源平台</title>
    <link rel="stylesheet" href="res/layui/css/layui.css">
    <link rel="stylesheet" href="res/css/global.css">
    <link rel="stylesheet" href="res/css/picture.css">
    <%--固定参考地址--%>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="res/js/jquery-3.4.1.js"></script>
    <style type="text/css">
        div.layui-card-body{
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 4;
            overflow: hidden;
        }
    </style>
    <script src="res/layui/layui.js"></script>
</head>
<body>


<%--顶部导航栏--%>
<%@ include file="WEB-INF/view/part/header.jsp"%>

<%--轮播图--%>
<%@ include file="WEB-INF/view/part/indexBanner.jsp"%>

<div class="layui-container">
    <div class="layui-row layui-col-space20" style="padding: 10px; height: 95%">
        <div class="layui-col-md1" <%--style="background-color: #009688"--%>></div>
        <div class="layui-col-md7">
            <fieldset class="layui-elem-field layui-field-title">
                <legend>最近热门</legend>
            </fieldset>
            <div id="article-list">
                <c:forEach items="${articleList}" var="a">
                    <div class="layui-card">
                        <div class="layui-card-header" style="font-size: medium">
                            <span class="layui-badge layui-bg-cyan">${a.articleLikeCount}人赞过</span>
                            &nbsp;
                            <b>
                                <a href="<%=request.getContextPath()%>/article/${a.articleId}/view.do">${a.articleTitle}</a>
                            </b>
                            <button type="button" class="layui-btn layui-btn-primary layui-border-green layui-btn-sm" style="float: right">
                                <a href="<%=request.getContextPath()%>/article/search.do?search-mode=一级学科&search-keyword=${a.articleCategorySecond}">${a.articleCategorySecond}</a>
                            </button>
                        </div>
                        <div class="layui-card-body">
                            <a href="<%=request.getContextPath()%>/article/${a.articleId}/view.do">
                                    ${a.articleContent}
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <hr>

        </div>

        <div class="layui-col-md3">
            <fieldset class="layui-elem-field layui-field-title">
                <legend>公告</legend>
            </fieldset>
            <div class="layui-card">
                <div class="layui-card-header">
                    <b>
                        公告1
                    </b>
                </div>
                <div class="layui-card-body">
                    公告内容1。
                </div>
            </div>
            <div class="layui-card">
                <div class="layui-card-header">
                    <b>
                        公告2
                    </b>
                </div>
                <div class="layui-card-body">
                    公告内容2。
                </div>
            </div>
            <hr>

            <%--<div style="font-size: large; color: #777777; font-style: italic;">
                探索更多
            </div>
            <hr>
            <button type="button" class="layui-btn layui-btn-fluid" style="height: 70px; font-size: large">
                <i class="layui-icon layui-icon-find-fill"></i>
                点击开启您的探索之旅
            </button>--%>


            <%--<div style="font-size: large; color: #777777; font-style: italic;">
                猜你喜欢
                <button type="button" class="layui-btn layui-btn-radius layui-btn-sm" style="float:right; font-size: small">
                    <i class="layui-icon layui-icon-refresh-1"></i>
                    换一批
                </button>
            </div>
            <hr>
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
        <div class="layui-col-md1" <%--style="background-color: #009688"--%>></div>
    </div>
</div>


</body>
</html>
