//package com.syj.myshop.auth.web;
//
//import com.syj.myshop.msg.R;
//import jakarta.annotation.Resource;
//import jakarta.annotation.Resources;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.oauth2.core.oidc.OidcScopes;
//import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Set;
//import java.util.UUID;
//
///**
// * @author SuYajiang
// * @email suyajiang@aoscript.com
// * @create 2024-08-20 14:31
// */
//@RestController
//public class ClientController {
//
//    @Resource
//    JdbcRegisteredClientRepository jdbcRegisteredRepository;
//
//    @PostMapping("addClient")
//    public R<String> add(String clientId, String clientSecret , Set<String> resourcesIds){
//        RegisteredClient byClientId = jdbcRegisteredRepository.findByClientId(clientId);
//        if(byClientId != null){
//            return R.fail("clientId已存在");
//        }
//        RegisteredClient customClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId(clientId)
//                .clientSecret("{noop}"+clientSecret)
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
//                .redirectUri("http://127.0.0.1:8080/authorized")
//                .postLogoutRedirectUri("http://127.0.0.1:8080/logged-out")
//                .scope(OidcScopes.OPENID)
//                .scope(OidcScopes.PROFILE)
//                .scopes(resourcesIds::addAll)
//                .scope("message.read")
//                .scope("message.write")
//                .scope("user.read")
//                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                .build();
//        jdbcRegisteredRepository.save(customClient);
//        return R.success("添加成功");
//    }
//
//}
