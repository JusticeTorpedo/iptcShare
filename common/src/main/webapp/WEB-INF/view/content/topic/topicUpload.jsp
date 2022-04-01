<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  用户后台发布话题页面
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <meta charset="utf-8">
    <title>${username}的个人中心-发布话题</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/res/layui/css/layui.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/res/css/global.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/res/js/jquery-3.4.1.js"></script>
    <script src="<%=request.getContextPath() %>/res/layui/layui.js"></script>
    <script>
        layui.use(['element','form','layedit'],function () {
            var element = layui.element;
            var form = layui.form;
            var layedit = layui.layedit
                ,$ = layui.jquery;

            //在构建编辑器之前，设置layedit图片上传
            layedit.set({
                uploadImage: {
                    url: '<%=request.getContextPath()%>/topic/image/upload.do',
                    type: 'post'
                }
            });

            //构建一个默认的编辑器
            var index = layedit.build('topic-content',{
                height:400
            });
        });
    </script>

</head>
<body>
<div class="layui-layout layui-layout-admin">

    <!--顶部导航栏-->
    <%@ include file="../../part/header.jsp"%>

    <%--左侧常驻垂直导航--%>
    <%@ include file="../../part/userHomeSideBar.jsp"%>

    <%--右侧内容区域--%>
    <div class="layui-body">

        <%--面包屑导航--%>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>
                <span class="layui-breadcrumb">
                    <a href="<%=request.getContextPath()%>/jump/home.do">个人中心</a>
                    <a href="">话题</a>
                    <a><cite>发布话题</cite></a>
                </span>
            </legend>
        </fieldset>

        <div class="layui-bg-gray" style="padding: 30px;">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-panel" style="padding: 30px 30px 50px;">

                        <form class="layui-form"  method="post" id="form-article-insert" action="<%=request.getContextPath()%>/topic/upload.do">

                            <div class="layui-form-item">
                                <label class="layui-form-label">话题标题 <span style="color: #FF5722; ">*</span></label>
                                <div class="layui-input-block">
                                    <input type="text" name="topicTitle" autocomplete="off" placeholder="请输入标题" class="layui-input" required>
                                </div>
                            </div>

                            <div class="layui-form-item layui-form-text">
                                <label class="layui-form-label">话题内容 <span style="color: #FF5722; ">*</span></label>
                                <div class="layui-input-block">
                                    <textarea class="layui-textarea layui-hide" name="topicContent" id="topic-content"></textarea>
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <button class="layui-btn" lay-submit="">发布话题</button>
                                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                </div>
                            </div>

                        </form>

                    </div>
                </div>
            </div>
        </div>

        <div style="padding: 15px;">编辑器外部区域</div>

    </div>

</div>
</body>
</html>

