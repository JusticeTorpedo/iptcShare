<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>案例详情-${article.articleTitle}</title>
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
            var articleId = arr[5];
            console.log(articleId);
            $.get(
                "<%=request.getContextPath()%>/article/${articleId}/like.do",
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

        function comment() {
            var arr = (window.location.href).split('/');
            var articleId = arr[5];
            var content = $("#comment").val();
            console.log(articleId+"+"+content);
            if(content == ""){
                alert("评论内容不能为空");
            }else{
                $.post(
                    "<%=request.getContextPath()%>/article/${articleId}/comment.do",
                    {content:content},
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
                        <a href="<%=request.getContextPath()%>/article/index.do">案例专区</a>
                        <a><cite>案例详情</cite></a>
                    </span>
                </legend>
            </fieldset>
            <div class="layui-card" style="height: auto;">
                <blockquote class="layui-elem-quote">
                    ${article.articleCategoryFirst} -
                        <a href="<%=request.getContextPath()%>/article/search.do?search-mode=一级学科&search-keyword=${article.articleCategorySecond}">${article.articleCategorySecond}</a>
                </blockquote>
                <div class="layui-card-header" style="padding: 10px 20px 10px">
                    <div style="font-size: x-large; color: black; font-weight: bold">
                        ${article.articleTitle}
                    </div>
                    <hr class="layui-border-cyan">
                </div>
                <br>
                <div class="layui-card-body" style="padding: 10px 30px 50px">
                    ${article.articleContent}
                </div>

                <button onclick="like()" type="button" class="layui-btn layui-btn-radius layui-btn-primary" style="margin-left: 20px;margin-bottom: 10px">
                    <i class="layui-icon layui-icon-praise"> 点赞</i>
                </button>

                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                    <legend>评论</legend>
                </fieldset>
                <div class="layui-card" style="height: auto;padding: 0px 20px 30px 20px">
                    <textarea id="comment" name="content" placeholder="来条友善的评论吧" class="layui-textarea"></textarea>
                    <button onclick="comment()" type="button" class="layui-btn layui-btn-radius layui-btn-primary" style="margin-top: 10px">
                        <i class="layui-icon layui-icon-release"> 发送</i>
                    </button>
                </div>

            </div>

        </div>

        <div class="layui-col-md3">

            <fieldset class="layui-elem-field layui-field-title">
                <legend>
                    <span class="layui-breadcrumb">
                        <a><cite>案例来源</cite></a>
                    </span>
                </legend>
            </fieldset>
            <div class="layui-panel">
                <div style="padding: 20px;line-height: 2em">
                    <i class="layui-icon layui-icon-username"> 用户：${userProfile.userName}<br></i>
                    <i class="layui-icon layui-icon-time"> 发布于 <fmt:formatDate value="${article.articleUpdateTime}" type="date" pattern="yyyy-MM-dd HH:mm"/></i><br>
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
                    <i class="layui-icon layui-icon-read"> 访问量 ${article.articleVisitCount}</i><br>
                    <i class="layui-icon layui-icon-praise"> 收到了 ${article.articleLikeCount} 个赞</i><br>
                    <i class="layui-icon layui-icon-reply-fill"> 收到了 ${article.articleCommentCount} 个评论</i><br>
                </div>
            </div>

            <br>
            <fieldset class="layui-elem-field layui-field-title">
                <legend>
                    <span class="layui-breadcrumb">
                        <a><cite>最新评论</cite></a>
                    </span>
                </legend>
            </fieldset>

            <c:forEach items="${commentList}" var="list">
                <div class="layui-card">
                    <div class="layui-card-header">
                        <b>${list.userName}</b>（<fmt:formatDate value="${list.createTime}" type="date" pattern="yyyy-MM-dd HH:mm"/>）
                    </div>
                    <div class="layui-card-body">
                        "${list.content}"
                    </div>
                </div>
            </c:forEach>




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
