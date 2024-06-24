package org.example.movieapp.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.movieapp.entity.User;
import org.example.movieapp.model.enums.UserType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
 @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{
     User user = (User) request.getSession().getAttribute("currentUser");
     if (user == null){
         response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //status= 401
         return false;
     }
     if (!user.getType().equals(UserType.ADMIN)){
         response.setStatus(HttpServletResponse.SC_FORBIDDEN);//403
         return false;
     }
     return true;
 }
}
