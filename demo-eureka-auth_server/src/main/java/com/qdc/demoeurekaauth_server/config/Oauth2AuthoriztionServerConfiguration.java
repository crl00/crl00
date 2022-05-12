package com.qdc.demoeurekaauth_server.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configuration.ClientDetailsServiceConfiguration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


@Component
@Configuration
public class Oauth2AuthoriztionServerConfiguration extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;
    

    @Autowired
    private DruidDataSource druidDataSource;  //自动注入的数据源


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception{

        System.out.println("aaaa");
        security.checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
        clients.withClientDetails(new JdbcClientDetailsService(druidDataSource));
        System.out.println("bbbb");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
        System.out.println("cccc");
        endpoints.userDetailsService(userDetailsService);

        TokenStore tokenStore=new JdbcTokenStore(druidDataSource);

        DefaultTokenServices tokenServices=new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(new JdbcClientDetailsService(druidDataSource));
        tokenServices.setAccessTokenValiditySeconds(38000);

        endpoints.tokenServices(tokenServices);

        //数据库管理授权码
        endpoints.authorizationCodeServices(new JdbcAuthorizationCodeServices(druidDataSource));

        //数据库管离授权信息
        ApprovalStore approvalStore=new JdbcApprovalStore(druidDataSource);

        endpoints.approvalStore(approvalStore);



    }
    
    






}
