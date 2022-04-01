
<%--
    首页轮播图
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    layui.use('carousel', function(){
        var carousel = layui.carousel;
        //建造轮播实例
        carousel.render({
            elem: '#banner1'
            ,width: '100%' //设置容器宽度
            ,arrow: 'hover' //始终显示箭头
            ,anim: 'default' //切换动画方式
            ,autoplay: true
            ,indicator: 'none'
        });
    });
</script>
<div class="layui-carousel" id="banner1">
    <div carousel-item>
        <c:forEach items="${bannerImages}" var="i">
            <img src="${i.imagePath}">
        </c:forEach>
    </div>
</div>
