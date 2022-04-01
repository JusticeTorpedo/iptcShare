<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  用户后台上传文章页面
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>

<html>
<head>
    <meta charset="utf-8">
    <title>${username}的个人中心-写案例</title>
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

            form.on('switch(comment-switch)', function(data){
                layer.tips('评论区已'+ (this.checked ? '打开' : '关闭'), data.othis);
                ('swtich(comment-switch').value = this.checked ? true : false ;

            });
            form.on('select(cat1st)', function (data) {
                $.get(
                    "<%=request.getContextPath()%>/subject/cat2st/query.do",
                    {parentId:data.value},
                    function (resp){
                        if(resp.code==0){
                            var options = '';
                            $.each(resp.data, function (index, value) {
                                console.log(value);
                                $("#cat2st").append(new Option(value.name, value.id));
                            })
                            layui.form.render('select');
                        }else{
                            alert(resp.msg);
                        }
                    },
                    "json"
                )
            });

            //在构建编辑器之前，设置layedit图片上传
            layedit.set({
                uploadImage: {
                    url: '<%=request.getContextPath()%>/article/image/upload.do',
                    type: 'post'
                }
            });


            //构建一个默认的编辑器
            var index = layedit.build('article-content',{
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
                    <a href="">案例</a>
                    <a><cite>写案例</cite></a>
                </span>
            </legend>
        </fieldset>

        <div class="layui-bg-gray" style="padding: 30px;">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-panel" style="padding: 30px 30px 50px;">

                        <form class="layui-form"  method="post" id="form-article-insert" action="<%=request.getContextPath()%>/article/upload.do">

                            <div class="layui-form-item">
                                <label class="layui-form-label">学科分类 <span style="color: #FF5722; ">*</span></label>
                                <div class="layui-input-inline">
                                    <select name="subjectCat1st" lay-filter="cat1st">
                                        <option value="">请选择</option>
                                        <c:forEach items="${cat1stList}" var="cat">
                                            <option value="${cat.id}">${cat.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="layui-input-inline">
                                    <select name="subjectCat2st" id="cat2st">
                                        <option value="">请选择</option>
                                    </select>
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label">案例标题 <span style="color: #FF5722; ">*</span></label>
                                <div class="layui-input-block">
                                    <input type="text" name="articleTitle" autocomplete="off" placeholder="请输入标题" class="layui-input" required>
                                </div>
                            </div>

                            <div class="layui-form-item layui-form-text">
                                <label class="layui-form-label">案例内容 <span style="color: #FF5722; ">*</span></label>
                                <div class="layui-input-block">
                                    <textarea class="layui-textarea layui-hide" name="articleContent" id="article-content"></textarea>
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <label class="layui-form-label">开启评论</label>
                                <div class="layui-input-block">
                                    <input type="checkbox" name="allowComment" value="1" lay-skin="switch" lay-filter="comment-switch" checked>
                                </div>
                            </div>

                            <div class="layui-form-item">
                                <div class="layui-input-block">
                                    <button class="layui-btn" lay-submit="">发布案例</button>
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

