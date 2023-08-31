package com.hometest.OAuth2JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration 
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter{
	
	   private String clientId = "pixeltrice";
	   private String clientSecret = "pixeltrice-secret-key";
	   private String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwQbFGhdS24tvV\n" +
			   "8SH1VNDeihIwqZb1eWV606s6dfq7E5X8/Jyi4yLzBp9TuqGUE47LIeKET40ozuaV\n" +
			   "KIMvbUZ9aOcxXJU1iShEW2n6O9DCXkcjTqp19jZ0rw6dEOS6Xzf7z7OYhBX7JOec\n" +
			   "8VxwVN2j8g0V/8IBcpWxfT1DEKGoQzJWIUAYxz2Y8T0BjJaBtgzbzsGcTD4xKYHR\n" +
			   "mYbQJMMMf+rXf1WuW5YlfgzQ0jJ2+hsK6tGcQpppC6lKein2Nc8q6bkJqtxDxzBL\n" +
			   "7H2S1PGxdoEGE6u1sxNLz94Kc5MI3U9PTjmCqJcpNK5+eUmv/pIG4r0zUyVDXuYj\n" +
			   "wVq4p6aBAgMBAAECggEACFzdkeiIqrLB1nlN8Kt5C44GheuN/5CmeXjwj2IIpvar\n" +
			   "b6j2r53SSzCdv6XDw9hXtpihfECgBHcoppwY+VeYeBvRmFx5seQTloa11QsggRBj\n" +
			   "FLBHUzh+5fNmYJpsF9GrwNVegpaFyng6Sw6CocvNBlnWOBx98n6izpyQGw3/dZHC\n" +
			   "ed6ZEnWLV0+auLKo/cN/e3RtF0XN2wG1jbKTPE73D+FwCgXs+Y7900fMZqU7wNsJ\n" +
			   "xSLpXYS4m9ekpITW5MzBpPonGsLaNemgY402h7vl/Me2G+ZfLH8NlnLhgUkTb2+E\n" +
			   "hMltbBK/k/gPem3tXuJOz3UVgwmqKKjHEdRA+W1IzwKBgQDUlDxQGSST66vUY11E\n" +
			   "8dosZ3HMUkqyI/Vz95q20bUh1syXzxF+8QVrusBvXrf1flVN0UauOJndNmgjg3SA\n" +
			   "len59pFSwi9dF2bc3mI7iFsyxhj1C7rrYhRnDfmApty57Y3YHm4TZEbUIReu3AY0\n" +
			   "B2QITvVP+HLarAi+F1sBAZOkowKBgQDUQifgEDcI8VHrfr7CmuRLkU8fjF8PwFgw\n" +
			   "+OYpH7iqZu7QLNy8Oy2g5/46U7ZXTxsOVR+2hAxYi9IfJE02T5I6aG9Hs4q61u7P\n" +
			   "uqHWm1aSxtYgM3FnDWqNU71CKnJbwsUoYKvvfmdjjdgin/zLlCFgNAFFNVh5fdx0\n" +
			   "z5Ldd3/WiwKBgBOB3t8Cz6Z7U/NTgvWWtAyrGXhEfPJH9nhD2oC4UcTfLzsnF1Xs\n" +
			   "zDCMnKgbqwnuI+nWMIRAUW5JVdkccQ5zs1rq038irrH+OcQ+7AjGhIfuQdAK2YZM\n" +
			   "I88pKyIxLcQqFoAkmrlQ1GcW+lNjgXY4z2crx7sk83U5oDvBbOp0uJW9AoGAZSIo\n" +
			   "UbAoKNCikaxQQ/T9NaxEoOmHGcAxxROyxoFgwYBN5U6a1ez0bqhbD3t3c94zK/8P\n" +
			   "Hy2HnGDyevgW+fur1ryP2QAI58ElBEEOf2yAMWsf9zPLoqhXzoLJuE71Mhd5TSXS\n" +
			   "hkgaBus8wjKshmtMA8SDnnAueaSod9e3SGJ5vMUCgYEAnPD5X0h7osvFmf57+aZu\n" +
			   "RhFlG3yy442pl1kxF3kxUKB73fBRSw/IpFu4VIZ82LIGch5+m99DLSTexNmAItuS\n" +
			   "hCGBDAbf/7s94iNLtd0YNtaY5zNnpHVBXS1VE9OxdUyLcCWc03boz9/rvJ8F76Zr\n" +
			   "ty8GGu/XnEs6fM7OykBgnmU=";
	   private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
			   "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsEGxRoXUtuLb1fEh9VTQ\n" +
			   "3ooSMKmW9XlletOrOnX6uxOV/PycouMi8wafU7qhlBOOyyHihE+NKM7mlSiDL21G\n" +
			   "fWjnMVyVNYkoRFtp+jvQwl5HI06qdfY2dK8OnRDkul83+8+zmIQV+yTnnPFccFTd\n" +
			   "o/INFf/CAXKVsX09QxChqEMyViFAGMc9mPE9AYyWgbYM287BnEw+MSmB0ZmG0CTD\n" +
			   "DH/q139VrluWJX4M0NIydvobCurRnEKaaQupSnop9jXPKum5CarcQ8cwS+x9ktTx\n" +
			   "sXaBBhOrtbMTS8/eCnOTCN1PT045gqiXKTSufnlJr/6SBuK9M1MlQ17mI8FauKem\n" +
			   "gQIDAQAB\n" +
			   "-----END PUBLIC KEY-----";
	   
	   @Autowired
	   @Qualifier("authenticationManagerBean")
	   private AuthenticationManager authenticationManager;
	   
	   @Autowired
	   PasswordEncoder passwordEncoder;
	   
	   @Bean
	   public JwtAccessTokenConverter tokenEnhancer() {
	      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	      converter.setSigningKey(privateKey);
	      converter.setVerifierKey(privateKey);
	      return converter;
	   }
	   
	   @Bean
	   public JwtTokenStore tokenStore() {
	      return new JwtTokenStore(tokenEnhancer());
	   }
	   
	   @Override
	   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
	      .accessTokenConverter(tokenEnhancer());
	   }
	   @Override
	   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	      security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	   }
	   @Override
	   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	      clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
	         .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
	         .refreshTokenValiditySeconds(20000);

	   }

}
