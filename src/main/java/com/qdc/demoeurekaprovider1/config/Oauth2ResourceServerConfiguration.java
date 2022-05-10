//package com.qdc.demoeurekaprovider1.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
//
//@Configuration
//public class Oauth2ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//    private static final String URL="http://localhost:8081/oauth/check_token";
//
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resource) throws Exception{
//        //设置验证Token的方法，即使用test和123456的客户端身份去URL验证token
//        RemoteTokenServices tokenService = new RemoteTokenServices();
//
//        tokenService.setCheckTokenEndpointUrl(URL);
//        tokenService.setClientId("test");
//
//        tokenService.setClientSecret("123456");
//        resource.tokenServices(tokenService);
//        //设置当前资源服务器的resource_id为userall，注意当前Client Id是否拥有对Resource Id的访问权限
//        resource.resourceId("userall").stateless(true);
//
//
//    }
//}
