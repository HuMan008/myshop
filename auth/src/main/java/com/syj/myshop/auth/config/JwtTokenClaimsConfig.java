//package com.syj.myshop.auth.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.oauth2.jwt.JwtClaimsSet;
//import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
//import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
//import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
//
//import java.util.Optional;
//import java.util.Set;
//
///**
// * @author SuYajiang
// * @email suyajiang@aoscript.com
// * @create 2024-08-20 17:15
// */
//@Configuration
//@RequiredArgsConstructor
//public class JwtTokenClaimsConfig {
//    private final RedisTemplate redisTemplate;
//
//    @Bean
//    public OAuth2TokenCustomizer<JwtEncodingContext> jwtTokenCustomizer() {
//        return context -> {
//            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType()) && context.getPrincipal() instanceof UsernamePasswordAuthenticationToken) {
//                // Customize headers/claims for access_token
//                Optional.ofNullable(context.getPrincipal().getPrincipal()).ifPresent(principal -> {
//                    JwtClaimsSet.Builder claims = context.getClaims();
//                    if (principal instanceof SysUserDetails userDetails) {
//                        // 系统用户添加自定义字段
//                        Long userId = userDetails.getUserId();
//                        claims.claim("user_id", userId);  // 添加系统用户ID
//
//                        // 角色集合存JWT
//                        var authorities = AuthorityUtils.authorityListToSet(context.getPrincipal().getAuthorities())
//                                .stream()
//                                .collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
//                        claims.claim(SecurityConstants.AUTHORITIES_CLAIM_NAME_KEY, authorities);
//
//                        // 权限集合存Redis(数据多)
//                        Set<String> perms = userDetails.getPerms();
//                        redisTemplate.opsForValue().set(SecurityConstants.USER_PERMS_CACHE_PREFIX + userId, perms);
//
//                    } else if (principal instanceof MemberDetails userDetails) {
//                        // 商城会员添加自定义字段
//                        claims.claim("member_id", String.valueOf(userDetails.getId())); // 添加会员ID
//                    }
//                });
//            }
//        };
//    }
//
//
//}
