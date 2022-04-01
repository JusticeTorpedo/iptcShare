<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>${answer.userName}的回复</title>
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
            $.get(
                "<%=request.getContextPath()%>/topic/${topic.id}/${answer.id}/like.do",
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
            var content = $("#comment").val();
            if(content == ""){
                alert("评论内容不能为空");
            }else{
                $.post(
                    "<%=request.getContextPath()%>/topic/${answer.topicId}/${answer.id}/comment.do",
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
        function commentLike(commentId) {
            $.get(
                "<%=request.getContextPath()%>/topic/${topic.id}/${answer.id}/"+commentId+"/like.do",
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
                        <a href="<%=request.getContextPath()%>/topic/index.do">话题专区</a>
                        <a href="<%=request.getContextPath()%>/topic/${answer.topicId}/view.do">${topic.title}</a>
                        <a><cite>${answer.userName}的回复</cite></a>
                    </span>
                </legend>
            </fieldset>
            <div class="layui-card" style="height: auto;">
                <blockquote class="layui-elem-quote">
                    话题 - <a href="<%=request.getContextPath()%>/topic/${answer.topicId}/view.do">${topic.title}</a>
                </blockquote>
                <div class="layui-card-header" style="padding: 10px 20px 10px">
                    <div style="font-size: x-large; color: black; font-weight: bold">
                        ${answer.userName}
                    </div>
                    <hr class="layui-border-cyan">
                </div>
                <br>
                <div class="layui-card-body" style="padding: 10px 30px 50px">
                    ${answer.content}
                </div>

                <button onclick="like()" type="button" class="layui-btn layui-btn-radius layui-btn-primary" style="margin-left: 20px;margin-bottom: 10px">
                    <i class="layui-icon layui-icon-praise"> 点赞</i>
                </button>

                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                    <legend>写评论</legend>
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

            <div class="layui-panel">
                <div style="padding: 20px;line-height: 2em">
                    <i class="layui-icon layui-icon-username"> 用户：${userProfile.userName}<br></i>
                    <i class="layui-icon layui-icon-time"> 发布于 <fmt:formatDate value="${answer.createTime}" type="date" pattern="yyyy-MM-dd HH:mm"/></i><br>
                </div>
            </div>

            <br>

            <div class="layui-panel">
                <div style="padding: 20px;line-height: 2em">
                    <i class="layui-icon layui-icon-praise"> 收到了 ${answer.likeCount} 个赞</i><br>
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
                        "${list.content}"<br>
                        <button onclick="commentLike(${list.id})" type="button" class="layui-btn layui-btn-radius layui-btn-sm" style="margin-top: 10px">
                            <i class="layui-icon layui-icon-praise"> ${list.likeCount}</i>
                        </button>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="layui-col-md1"></div>
    </div>
</div>

</body>
</html>
