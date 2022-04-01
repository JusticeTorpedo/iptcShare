package cn.edu.ncut.interceptor;

import cn.edu.ncut.util.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();
        String token="";
        if(cookies != null){
            for(Cookie c: cookies){
                if("token".equals(c.getName())){
                    token = c.getValue();
                    break;
                }
            }
        }

        System.out.println("Token："+token);

        //检查Token是否为空
        if(!"".equals(token)){

            //Token非空，验证其合法性
            if(JWTUtils.verifyToken(token) == true){
                System.out.println("token合法");
                return true;
            }else{
                System.out.println("token非法！");
            }
        }else{
            System.out.println("token为空！");
        }

        //若执行到此处，则表示token为空或token非法，需要跳转到登录页面
        response.sendRedirect(request.getContextPath()+"/jump/login.do");
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
