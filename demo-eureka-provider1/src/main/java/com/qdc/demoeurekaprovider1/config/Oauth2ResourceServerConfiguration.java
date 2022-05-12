//package com.qdc.demoeurekaprovider1.config;
//
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//import org.springframework.stereotype.Component;
//
//import java.net.URL;
//
//
//@Configuration
//public class Oauth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//    private static final String URL="http://localhost:8088/oauth/token";
//
//@Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception{
//
////    设置验证Token的方法 即使使用test和123456的客户端身份去URL验证token
//        RemoteTokenServices tokenService = new RemoteTokenServices();
//
//        tokenService.setCheckTokenEndpointUrl(URL);
//        tokenService.setClientId("test");
//        tokenService.setClientSecret("123456");
//        //设置认证客户端和密码
//
//        resources.tokenServices(tokenService);
//        //设置当前资源服务器的resource_id为userall
//        resources.resourceId("userall").stateless(true);
//    }
//}
