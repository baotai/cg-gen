package com.cg.filter;

import com.cg.common.constants.SecurityConstants;
import com.cg.utils.TokenUtils;
import io.jsonwebtoken.JwtException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器处理所有HTTP请求，并检查是否存在带有正确令牌的Authorization标头。例如，如果令牌未过期或签名密钥正确。
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final StringRedisTemplate redisTemplate;

    public JwtAuthorizationFilter(AuthenticationManager manager, StringRedisTemplate redisTemplate) {
        super(manager);
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String token = request.getHeader(SecurityConstants.TOKEN_HEADER);

        if (token == null || !token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            SecurityContextHolder.clearContext();
            chain.doFilter(request, response);
            return;
        }

        String tokenValue = token.replace(SecurityConstants.TOKEN_PREFIX, "");
        UsernamePasswordAuthenticationToken authentication = null;
        try {
            String previousToken = redisTemplate.opsForValue().get(TokenUtils.getId(tokenValue));
            if (!token.equals(previousToken)) {
                SecurityContextHolder.clearContext();
                chain.doFilter(request, response);
                return;
            }
            authentication = TokenUtils.getAuthentication(tokenValue);
        } catch (JwtException e) {
            logger.error("Invalid jwt : " + e.getMessage());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }
}
