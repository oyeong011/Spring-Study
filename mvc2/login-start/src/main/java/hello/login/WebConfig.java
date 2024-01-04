package hello.login;

import hello.login.web.argumentresolver.LoginMemberArgumentResolver;
import hello.login.web.filter.LogFilter;
import hello.login.web.interceptor.LogInterceptor;
import hello.login.web.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver()); // 로그인 멤버 리졸버 등록
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1) // 필터 순서
                .addPathPatterns("/**") // 모든 요청에 인터셉터 적용
                .excludePathPatterns("/css/**","/*.ico","/error"); // 적용 제외 경로

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2) // 필터 순서
                .addPathPatterns("/**") // 모든 요청에 인터셉터 적용
                .excludePathPatterns("/","/members/add","/login","/logout","/css/**","/*.ico","/error"); // 적용 제외 경로
    }


    @Bean
    public FilterRegistrationBean logFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter()); //로그 필터 등록
        filterRegistrationBean.setOrder(1); // 필터 순서
        filterRegistrationBean.addUrlPatterns("/*"); // 모든 요청에 필터 적용

        return filterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean loginCheckFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter()); //로그 필터 등록
        filterRegistrationBean.setOrder(2); // 필터 순서
        filterRegistrationBean.addUrlPatterns("/*"); // 모든 요청에 필터 적용

        return filterRegistrationBean;
    }
}
