# Spring OAuth2 SSO

## Resource Server

Sample resource server listens on port `8700` and defines public accessible resources: `/home`, `/samplePage` 
and protected resource `/user/photo`.

**prefer-info-token** is used to choose between token-info-uri and user-info-uri 
in case both are defined

## Authorization Server

OAuth 2 specification states that authorization endpoint is used to interact with the resource owner and obtain an 
authorization grant. Before this grant is issued resource owner has to be identified, but OAuth specification does not 
define any specific authentication method. For the purpose of this demo HTTP Basic Auth is used for securing 
authorization endpoint. In this demo, which uses Spring, authorization endpoint is secured using
Spring Web Security and defines in memory user details service with two users
and enables HTTP Basic Auth (`WebSecurityConfig`).

## SSO and Authorization Code Grant

In this demo Single Sign On works on top of OAuth 2 Authorization Code Grant workflow. When user directs browser to 
the protected resource on resource server he will be redirected to the authorization endpoint. After authenticating 
user may authorize access to protected resources. After another round of requests access code is obtained and stored 
in the session on the resource server. After this, whenever user directs browser to the protected resource, authentication 
object is loaded from the session and response with protected resource returned.

In case user wants to access his protected resources on second resource server then again he is redirected to the
authorization server. But this time he is already authenticated and only needs to authorize access for protected 
resources.

Configuration for such flow is rather basic and well documented.

## Password Grant

Password grant type should be used in case of trusted clients like native apps. In this case client collects
user credentials and exchange them for access token.

To support both authrorization code grant and password grant at the same time is more complicated. This demo project was created to 
present sample configuration for such scenario.

On the authentication server if you want to use the same authentication manager for both types of grant you need to
expose AuthenticationManager bean (see [WebSecurityConfig::authenticationManagerBean](https://github.com/pawelkorus/spring-oauth-sso/blob/master/authorization_server/src/main/java/sample/springoauthsso/config/WebSecurityConfig.java)) and the use it when configurin OAuth (see `configure` method that takes `AuthorizationServerEndpointsConfigurer` [AuthorizationServerConfig](https://github.com/pawelkorus/spring-oauth-sso/blob/master/authorization_server/src/main/java/sample/springoauthsso/config/AuthorizatonServerConfig.java)

On the resource server it was required to create custom request matcher that detects if incoming request contains Authorization Http header with Bearer token (see [OAuthResourceServerConfig](https://github.com/pawelkorus/spring-oauth-sso/blob/master/resource_server/src/main/java/sample/springoauthsso/config/OAuthResourceServerConfig.java)) 

## Troubleshooting

Possible CSRF detected - state parameter was present but no state could be found: https://github.com/spring-projects/spring-security-oauth/issues/322
