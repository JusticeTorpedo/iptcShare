<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <title>课程思政资源平台.jsp</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="keywords" content="课程思政">
    <meta name="description" content="致力于打造一个课程思政资源共建共享的平台">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/res/layui/css/layui.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/res/css/global.css">
    <script type="text/javascript" src="<%=request.getContextPath() %>/res/js/jquery-3.4.1.js"></script>

</head>
<body>
<script src="<%=request.getContextPath() %>/res/layui/layui.js"></script>
<script>
    layui.use(['element','form'],function () {
        var element = layui.element;
        var form = layui.form;
    })
</script>

<!--顶部导航栏-->
<%@ include file="../part/header.jsp"%>



<div class="layui-container" style="margin-top: 50px">
    <div class="layui-row layui-col-space10">
        <div class="layui-col-md3"></div>
        <div class="layui-col-md6">
            <div class="layui-panel">
                <div class="layui-tab layui-tab-brief" style="margin: 15px">
                    <ul class="layui-tab-title">
                        <li class="layui-this">登录</li>
                        <li>注册</li>
                    </ul>
                    <div class="layui-tab-content">

                        <%--登录--%>
                        <div class="layui-tab-item layui-show">
                            <form class="layui-form" id="login-form" action="<%=request.getContextPath()%>/user/login.do" method="post" style="margin-top: 15px">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">用户名</label>
                                    <div class="layui-input-inline">
                                        <input type="text" name="username" required lay-verify="required" placeholder="请输入用户名或邮箱" autocomplete="on" class="layui-input">
                                    </div>
                                    <%--<div class="layui-form-mid layui-word-aux">辅助文字</div>--%>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">密码框</label>
                                    <div class="layui-input-inline">
                                        <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                                    </div>
                                    <a class="layui-form-mid layui-word-aux" href="javascript:;">忘记密码？</a>
                                </div>

                                <div class="layui-form-item">
                                    <label class="layui-form-label"></label>
                                    <div class="layui-input-inline" style="height: 35px">
                                        <input type="checkbox" name="keepLogin" value="1" title="7天免登录" lay-skin="primary">
                                    </div>
                                </div>

                                <div class="layui-form-item">
                                    <div class="layui-input-block" style="margin-top: 20px">
                                        <button id="login-btn" type="submit" class="layui-btn">登录</button>
                                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <%--注册--%>
                        <div class="layui-tab-item">
                            <form class="layui-form" id="register-form" action="<%=request.getContextPath()%>/user/register.do" method="post" style="margin-top: 15px">
                                <div class="layui-form-item">
                                    <label class="layui-form-label">用户名</label>
                                    <div class="layui-input-inline">
                                        <input type="text" name="username" required lay-verify="required" placeholder="请输入用户名" autocomplete="on" class="layui-input">
                                    </div>
                                    <%--<div class="layui-form-mid layui-word-aux">辅助文字</div>--%>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">邮箱</label>
                                    <div class="layui-input-inline">
                                        <input type="text" name="email" required lay-verify="required" placeholder="请输入邮箱" autocomplete="on" class="layui-input">
                                    </div>
                                    <%--<div class="layui-form-mid layui-word-aux">辅助文字</div>--%>
                                </div>
                                <div class="layui-form-item">
                                    <label class="layui-form-label">密码框</label>
                                    <div class="layui-input-inline">
                                        <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                                    </div>
                                    <%--<div class="layui-form-mid layui-word-aux">辅助文字</div>--%>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-input-block" style="margin-top: 30px">
                                        <button type="submit" class="layui-btn">注册</button>
                                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div class="layui-col-md3"></div>
    </div>
</div>

</body>
</html>
