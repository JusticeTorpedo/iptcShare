<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
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
    <title>讨论专区-搜索结果</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/res/layui/css/layui.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/res/css/global.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/res/css/picture.css">
    <%--固定参考地址--%>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="<%=request.getContextPath() %>/res/js/jquery-3.4.1.js"></script>
    <style type="text/css">
        div.layui-card-body{
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 4;
            overflow: hidden;
        }
    </style>
    <script src="<%=request.getContextPath() %>/res/layui/layui.js"></script>
    <script>
        layui.use(['dropdown','form'], function() {
            var dropdown = layui.dropdown;
            var form = layui.form;

            form.on('submit(search-btn)', function(){
                //检查搜索关键字
                var keyword = $('#search-keyword').val().trim();
                if('' == keyword) {
                    alert("搜索关键字不能为空！");
                    $('#search-keyword').val('')
                    return false;
                }
            });
        });
    </script>
</head>
<body>


<%--顶部导航栏--%>
<%@ include file="../../part/header.jsp"%>

<div class="layui-container">
    <hr>
    <div class="layui-row layui-col-space20" style="padding: 10px; height: 95%">
        <div class="layui-col-md1" <%--style="background-color: #009688"--%>></div>
        <div class="layui-col-md7">

            <%--面包屑导航--%>
            <fieldset class="layui-elem-field layui-field-title">
                <legend>
                    <span class="layui-breadcrumb">
                        <a href="<%=request.getContextPath()%>/index.do">首页</a>
                        <a href="<%=request.getContextPath()%>/topic/index.do">话题专区</a>
                        <a><cite>按${mode}搜索</cite></a>
                    </span>
                </legend>
            </fieldset>

            <hr>
            <form class="layui-form"  method="post" id="form-article-search" action="<%=request.getContextPath()%>/topic/search.do">
                <div class="layui-form-item">
                    <div class="layui-inline" style="width: 15%">
                        <select name="search-mode">
                            <option value="标题" selected>标题</option>
                            <option value="内容">内容</option>
                        </select>
                    </div>
                    <div class="layui-inline" style="width: 60%">
                        <input class="layui-input" name="search-keyword" id="search-keyword" autocomplete="off" placeholder="请输入关键字" required>
                    </div>
                    <div class="layui-inline" style="width: 15%">
                        <button class="layui-btn" lay-submit lay-filter="search-btn">搜索</button>
                    </div>
                </div>
            </form>
            <br>

            <fieldset class="layui-elem-field layui-field-title">
                <legend>${mode}"${keyword}"的搜索结果</legend>
            </fieldset>

            <div id="topic-list">
                <c:forEach items="${topicList}" var="a">
                    <div class="layui-card">
                        <div class="layui-card-header" style="font-size: medium">
                            <span class="layui-badge layui-bg-cyan">${a.likeCount}人赞过</span>
                            &nbsp
                            <b>
                                <a href="<%=request.getContextPath()%>/topic/${a.id}/view.do">${a.title}</a>
                            </b>
                            &nbsp
                            <span class="layui-badge">${a.answerCount}</span>
                        </div>
                        <div class="layui-card-body">
                            <a href="<%=request.getContextPath()%>/topic/${a.id}/view.do">
                                    ${a.content}
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

        </div>
        <div class="layui-col-md1" <%--style="background-color: #009688"--%>></div>
    </div>
</div>


</body>
</html>
