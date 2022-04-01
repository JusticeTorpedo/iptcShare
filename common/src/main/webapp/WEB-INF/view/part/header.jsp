<%--网站共用的顶部导航栏--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<div class="fly-header layui-bg-cyan">
    <div class="layui-container">

        <!--导航栏最左端的logo-->
        <!--<a class="fly-logo" href="/">
            <img src="" alt="layui">
        </a>-->

        <ul class="layui-nav fly-nav layui-hide-xs">
            <li class="layui-nav-item">
                <a href="<%=request.getContextPath()%>/index.do">首页</a>
            </li>
            <li class="layui-nav-item">
                <a href="<%=request.getContextPath()%>/article/index.do">案例专区</a>
            </li>
            <li class="layui-nav-item">
                <a href="<%=request.getContextPath()%>/material/index.do">素材专区</a>
            </li>
            <li class="layui-nav-item">
                <a href="<%=request.getContextPath()%>/topic/index.do">话题专区</a>
            </li>
            <li class="layui-nav-item">
                <a href="<%=request.getContextPath()%>/jump/home.do">个人中心</a>
            </li>
        </ul>

    </div>
</div>
