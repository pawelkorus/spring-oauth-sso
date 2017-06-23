package sample.springoauthsso.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Configuration
@EnableResourceServer
public class OAuthResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
        .requestMatcher(BearerTokenRequestMatcher.build())
        .authorizeRequests()
            .anyRequest().authenticated().and()
        ;
    }

    public static class BearerTokenRequestMatcher implements RequestMatcher {

        @Override
        public boolean matches(HttpServletRequest request) {
            return Optional.ofNullable(request.getHeader("Authorization"))
                .map(value -> value.startsWith("Bearer"))
                .orElse(false);
        }

        public static BearerTokenRequestMatcher build() {
            return new BearerTokenRequestMatcher();
        }
    }
}
