package org.example.movieapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final AuthenticationInterceptor authenticationInterceptor;
    private final AuthorizationInterceptor  authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/api/reviews","/api/reviews/**","/api/favourites","/api/favourites/**","/thong-tin-ca-nhan","/phim-yeu-thich","lich-su-xem-phim");

        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/admin/**","/api/admin/**");
    }


}
