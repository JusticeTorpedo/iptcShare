<%--用户个人中心的左侧垂直导航栏--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<div class="layui-side layui-bg-cyan">
    <div class="layui-side-scroll">

        <ul class="layui-nav layui-nav-tree site-demo-nav layui-bg-cyan">

            <li class="layui-nav-item">
                <a class="javascript:;" href="javascript:;">我的</a>
                <dl class="layui-nav-child">
                    <dd class="">
                        <a href="<%=request.getContextPath()%>/jump/profile.do" >个人资料</a>
                    </dd>
                    <dd>
                        <a href="" >我的点赞</a>
                    </dd>
                    <dd>
                        <a href="" >我的评论</a>
                    </dd>
                    <dd class="">
                        <a href=""  >隐私设置</a>
                    </dd>

                </dl>
            </li>

            <li class="layui-nav-item">
                <a class="javascript:;" href="javascript:;">案例</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="" >我的案例</a>
                    </dd>
                    <dd>
                        <a href="<%=request.getContextPath()%>/jump/articleWritten.do" >写案例</a>
                    </dd>
                </dl>
            </li>

            <li class="layui-nav-item">
                <a class="javascript:;" href="javascript:;">素材</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="" >我的素材</a>
                    </dd>
                    <dd>
                        <a href="<%=request.getContextPath()%>/jump/materialWritten.do" >发布素材</a>
                    </dd>
                </dl>
            </li>

            <li class="layui-nav-item">
                <a class="javascript:;" href="javascript:;">话题</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="" >我的话题</a>
                    </dd>
                    <dd>
                        <a href="" >我的回答</a>
                    </dd>
                    <dd>
                        <a href="<%=request.getContextPath()%>/jump/topicWritten.do" >发帖子</a>
                    </dd>
                </dl>
            </li>

            <%--<li class="layui-nav-item">
                <a class="javascript:;" href="javascript:;">收藏</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="" >文章</a>
                    </dd>
                    <dd>
                        <a href="" >案例</a>
                    </dd>
                    <dd>
                        <a href="" >图片素材</a>
                    </dd>
                </dl>
            </li>--%>

            <li class="layui-nav-item">
                <a class="javascript:;" href="javascript:;">消息</a>
                <dl class="layui-nav-child">
                    <dd class="">
                        <a href=""  >通知</a>
                    </dd>
                    <dd class="">
                        <a href=""  >回复</a>
                    </dd>
                    <dd>
                        <a href=""  >私信</a>
                    </dd>
                </dl>
            </li>

            <li class="layui-nav-item" style="text-align: center; padding-top: 20px">
                <a href="<%=request.getContextPath()%>/user/logout.do">
                    <button type="button" class="layui-btn layui-btn-danger layui-btn-radius" style="width: 70%">退出登录</button>
                </a>
            </li>
        </ul>

    </div>
</div>