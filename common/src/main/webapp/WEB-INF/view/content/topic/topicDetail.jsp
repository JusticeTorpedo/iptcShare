<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>话题-${topic.title}</title>
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
            var topicId = arr[5];
            console.log(topicId);
            $.get(
                "<%=request.getContextPath()%>/topic/${topicId}/like.do",
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

        function answer() {
            var content = $("#answer-content").text();
            alert(content);
            /*var arr = (window.location.href).split('/');
            var topicId = arr[5];
            var content = $("#answer").val();
            console.log(topicId+"+"+content);
            if(content == ""){
                alert("回复内容不能为空");
            }else{
                $.post(
                    "<%=request.getContextPath()%>/topic/${topicId}/answer.do",
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
            }*/
        }
    </script>
    <script src="<%=request.getContextPath() %>/res/layui/layui.js"></script>
    <script>
        layui.use(['element','form'],function () {
            var element = layui.element;
            var form = layui.form;
            var layedit = layui.layedit;

            //在构建编辑器之前，设置layedit图片上传
            layedit.set({
                uploadImage: {
                    url: '<%=request.getContextPath()%>/topic/answer/image/upload.do',
                    type: 'post'
                }
            });

            //构建一个默认的编辑器
            var index = layedit.build('answer-content',{
                height:400
            });

            $('#answer-btn').on('click', function () {
                var content = layedit.getContent(index);
                if ('' == content){
                    alert("内容不能为空！");
                }else {
                    var arr = (window.location.href).split('/');
                    var topicId = arr[5];
                    $.post(
                        "<%=request.getContextPath()%>/topic/${topicId}/answer.do",
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
            })
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
                        <a href="<%=request.getContextPath()%>/topic/index.do">讨论专区</a>
                        <a><cite>话题详情</cite></a>
                    </span>
                </legend>
            </fieldset>
            <div class="layui-card" style="height: auto;">
                <div class="layui-card-header" style="padding: 10px 20px 10px">
                    <div style="font-size: x-large; color: black; font-weight: bold">
                        ${topic.title}
                    </div>
                    <hr class="layui-border-cyan">
                </div>
                <br>
                <div class="layui-card-body" style="padding: 10px 30px 50px">
                    ${topic.content}
                </div>

                <button onclick="like()" type="button" class="layui-btn layui-btn-radius layui-btn-primary" style="margin-left: 20px;margin-bottom: 20px">
                    <i class="layui-icon layui-icon-praise"> 点赞话题</i>
                </button>

                <div class="layui-collapse">
                    <div class="layui-colla-item">
                        <h2 class="layui-colla-title">在这里分享您的想法</h2>
                        <div class="layui-colla-content">
                            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                                <legend>写回复</legend>
                            </fieldset>
                            <div class="layui-card" style="height: auto">
                                <%--<textarea id="answer" name="content" placeholder="在这里您可以畅所欲言" class="layui-textarea"></textarea>--%>
                                <textarea class="layui-textarea layui-hide" name="answerContent" id="answer-content"></textarea>
                                <button id="answer-btn" type="button" class="layui-btn layui-btn-radius layui-btn-primary" style="margin-top: 10px">
                                    <i class="layui-icon layui-icon-release"> 发送</i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend>最新回复 ${topic.answerCount}</legend>
            </fieldset>

            <c:forEach items="${answerList}" var="a">
                <div class="layui-card">
                    <div class="layui-card-header" style="font-size: medium">
                        <span class="layui-badge layui-bg-cyan">${a.likeCount}人赞过</span>
                        &nbsp
                        <b>
                            <a href="<%=request.getContextPath()%>/topic/${topic.id}/${a.id}/view.do">${a.userName}</a>
                        </b>
                        <span style="float: right;font-size: small">
                            发布于<fmt:formatDate value="${a.createTime}" type="date" pattern="yyyy-MM-dd"/>
                        </span>
                    </div>
                    <div class="layui-card-body">
                        <a href="<%=request.getContextPath()%>/topic/${topic.id}/${a.id}/view.do">
                                ${a.content}
                        </a>
                    </div>
                </div>
            </c:forEach>

        </div>

        <div class="layui-col-md3">

            <fieldset class="layui-elem-field layui-field-title">
                <legend>
                    <span class="layui-breadcrumb">
                        <a><cite>话题来源</cite></a>
                    </span>
                </legend>
            </fieldset>
            <div class="layui-panel">
                <div style="padding: 20px;line-height: 2em">
                    <i class="layui-icon layui-icon-username"> 用户：${userProfile.userName}<br></i>
                    <i class="layui-icon layui-icon-time"> 发布于 <fmt:formatDate value="${topic.createTime}" type="date" pattern="yyyy-MM-dd HH:mm"/></i><br>
                </div>
            </div>

            <br>
            <fieldset class="layui-elem-field layui-field-title">
                <legend>
                    <span class="layui-breadcrumb">
                        <a><cite>话题信息</cite></a>
                    </span>
                </legend>
            </fieldset>
            <div class="layui-panel">
                <div style="padding: 20px;line-height: 2em">
                    <i class="layui-icon layui-icon-read"> 访问量 ${topic.visitCount}</i><br>
                    <i class="layui-icon layui-icon-praise"> 收到了 ${topic.likeCount} 个赞</i><br>
                    <i class="layui-icon layui-icon-reply-fill"> 收到了 ${topic.answerCount} 个回复</i><br>
                </div>
            </div>
        </div>
        <div class="layui-col-md1"></div>
    </div>
</div>


</body>
</html>
