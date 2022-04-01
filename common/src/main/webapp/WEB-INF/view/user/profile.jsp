<%--
    用户的个人资料编辑页面
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <title>${username}的个人中心</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/res/layui/css/layui.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/res/css/global.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/res/js/jquery-3.4.1.js"></script>

</head>
<body>
<script src="<%=request.getContextPath() %>/res/layui/layui.js"></script>
<script>
    layui.use(['element','form','upload'],function () {
        //var $ = layui.jquery;
        var element = layui.element;
        var form = layui.form;
        var upload = layui.upload;

        //用户头像上传
        var uploadInst = upload.render({
            elem: '#avatar-upload'
            ,url: '<%=request.getContextPath()%>/user/avatar/upload.do' //改成自己的上传接口即可。
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#avatar-image').attr('src', result); //图片链接（base64）
                });

                element.progress('avatar-progress', '0%'); //进度条复位
                layer.msg('上传中', {icon: 16, time: 0});
            }
            ,done: function(res){
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                //上传成功的一些操作
                alert(res.data.get('src'));
                document.getElementById('avatar-image').value = res.data.get('src');
                $('#avatar-text').html(''); //置空上传失败的状态
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var avatarText = $('#avatar-text');
                avatarText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs avatar-reload">重试</a>');
                avatarText.find('.avatar-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
            //进度条
            ,progress: function(n, elem, e){
                element.progress('avatar-progress', n + '%'); //可配合 layui 进度条元素使用
                if(n == 100){
                    layer.msg('上传完毕', {icon: 1});
                }
            }
        });

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
                    <a href="<%=request.getContextPath()%>/jump/home.do">个人中心</a>
                    <a><cite>${profile.userName}的个人资料</cite></a>
                </span>
            </legend>
        </fieldset>

        <%--编辑资料区--%>
        <div class="layui-bg-gray" style="padding: 30px;">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-panel" style="padding: 30px 30px 50px;">

                        <form class="layui-form" method="post" action="<%=request.getContextPath()%>/user/profile/upload.do">

                            <%--<div class="layui-form-item">
                                <label class="layui-form-label">头像</label>
                                <div class="layui-input-block">
                                    <div class="layui-upload">
                                        <button type="button" class="layui-btn" id="avatar-upload">上传图片</button>
                                        <div class="layui-upload-list">
                                            <img class="layui-upload-img" name="avatar-path" id="avatar-image">
                                            <p id="avatar-text"></p>
                                        </div>
                                        <div style="width: 95px;">
                                            <div class="layui-progress layui-progress-big" lay-showpercent="yes" lay-filter="avatar-progress">
                                                <div class="layui-progress-bar" lay-percent=""></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>--%>

                            <%--<div class="layui-form-item">
                                <label class="layui-form-label">用户名</label>
                                <div class="layui-input-block">
                                    <input type="text" name="username" placeholder="${profile.userName}" autocomplete="off" class="layui-input">
                                </div>
                            </div>--%>

                            <div class="layui-form-item">
                                <label class="layui-form-label">真实姓名<span style="color: #FF5722; ">*</span></label>
                                <div class="layui-input-block">
                                    <input type="text" name="realName" required  lay-verify="required" placeholder="${profile.userRealName}" autocomplete="off" class="layui-input">
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label">性别</label>
                                <div class="layui-input-block">
                                    <c:choose>
                                        <c:when test="${profile.userGender=='男'}">
                                            <input type="radio" name="gender" value="男" title="男" checked>
                                            <input type="radio" name="gender" value="女" title="女" >
                                        </c:when>
                                        <c:otherwise>
                                            <input type="radio" name="gender" value="男" title="男" >
                                            <input type="radio" name="gender" value="女" title="女" checked>
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label">学校<span style="color: #FF5722; ">*</span></label>
                                <div class="layui-input-block">
                                    <input type="text" name="university" required  lay-verify="required" placeholder="${profile.userUniversity}" autocomplete="off" class="layui-input">
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label">职称</label>
                                <div class="layui-input-block">
                                    <c:choose>
                                        <c:when test="${profile.userTitle=='助教'}">
                                            <select name="title">
                                                <option value="助教" selected>助教</option>
                                                <option value="讲师">讲师</option>
                                                <option value="副教授">副教授</option>
                                                <option value="教授">教授</option>
                                            </select>
                                        </c:when>
                                        <c:when test="${profile.userTitle=='讲师'}">
                                            <select name="title">
                                                <option value="助教">助教</option>
                                                <option value="讲师" selected>讲师</option>
                                                <option value="副教授">副教授</option>
                                                <option value="教授">教授</option>
                                            </select>
                                        </c:when>
                                        <c:when test="${profile.userTitle=='副教授'}">
                                            <select name="title">
                                                <option value="助教">助教</option>
                                                <option value="讲师">讲师</option>
                                                <option value="副教授" selected>副教授</option>
                                                <option value="教授">教授</option>
                                            </select>
                                        </c:when>
                                        <c:when test="${profile.userTitle=='教授'}">
                                            <select name="title">
                                                <option value="助教">助教</option>
                                                <option value="讲师">讲师</option>
                                                <option value="副教授">副教授</option>
                                                <option value="教授" selected>教授</option>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <select name="title">
                                                <option value=""></option>
                                                <option value="助教">助教</option>
                                                <option value="讲师">讲师</option>
                                                <option value="副教授">副教授</option>
                                                <option value="教授">教授</option>
                                            </select>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="layui-form-item layui-form-text">
                                <label class="layui-form-label">个人简介</label>
                                <div class="layui-input-block">
                                    <textarea name="signature" placeholder="${profile.userSignature}" class="layui-textarea"></textarea>
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <button class="layui-btn" lay-submit>更新</button>
                                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                </div>
                            </div>

                        </form>

                    </div>
                </div>
            </div>
        </div>

        <div style="padding: 15px;">外部区域</div>

    </div>
</div>
</body>
</html>


