package ei.algobaroapi.global.exception.common;

import static ei.algobaroapi.global.exception.common.GlobalErrorCode.ACCESS_DENIED;
import static ei.algobaroapi.global.exception.common.GlobalErrorCode.INTERNAL_SERVER_ERROR;
import static ei.algobaroapi.global.exception.common.GlobalErrorCode.INVALID_INPUT_VALUE;

import ei.algobaroapi.global.exception.GlobalEntityException;
import ei.algobaroapi.global.exception.S3Exception;
import ei.algobaroapi.global.response.message.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> catchAccessDeniedException(AccessDeniedException e) {
        log.warn(e.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.of(
                        ACCESS_DENIED.getErrorCode(),
                        ACCESS_DENIED.getErrorMessage())
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> catchMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                        INVALID_INPUT_VALUE.getErrorCode(),
                        INVALID_INPUT_VALUE.getErrorMessage())
                );
    }

    @ExceptionHandler(GlobalEntityException.class)
    public ResponseEntity<ErrorResponse> catchGlobalCustomException(GlobalEntityException e) {
        log.error(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                        e.getErrorCode(),
                        e.getErrorMessage())
                );
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ErrorResponse> catchMemberPasswordException(RuntimeException e) {
//        log.error(e.getMessage());
//        e.printStackTrace();
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(ErrorResponse.of(
//                        INTERNAL_SERVER_ERROR.getErrorCode(),
//                        INTERNAL_SERVER_ERROR.getErrorMessage())
//                );
//    }

    @ExceptionHandler(S3Exception.class)
    public ResponseEntity<ErrorResponse> catchS3Exception(S3Exception e) {
        log.error(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(
                        e.getErrorCode(),
                        e.getErrorMessage())
                );
    }
}

/*

2024-03-15T05:43:16.205302836Z 2024-03-15T05:43:16.204Z ERROR 1 --- [nio-8080-exec-4] e.a.g.e.common.GlobalExceptionHandler    : null
2024-03-15T05:43:16.205801727Z ei.algobaroapi.domain.auth.exception.AuthPasswordException
2024-03-15T05:43:16.206269448Z 	at ei.algobaroapi.domain.auth.exception.AuthPasswordException.of(AuthPasswordException.java:18)
2024-03-15T05:43:16.206593075Z 	at ei.algobaroapi.domain.auth.service.AuthService.signIn(AuthService.java:44)
2024-03-15T05:43:16.206910214Z 	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
2024-03-15T05:43:16.207203723Z 	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
2024-03-15T05:43:16.207721665Z 	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
2024-03-15T05:43:16.208043919Z 	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
2024-03-15T05:43:16.208363787Z 	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:351)
2024-03-15T05:43:16.208663986Z 	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:196)
2024-03-15T05:43:16.208969868Z 	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
2024-03-15T05:43:16.209269516Z 	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:765)
2024-03-15T05:43:16.209551410Z 	at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:123)
2024-03-15T05:43:16.209841344Z 	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:385)
2024-03-15T05:43:16.210126558Z 	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119)
2024-03-15T05:43:16.210463263Z 	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
2024-03-15T05:43:16.210756489Z 	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:765)
2024-03-15T05:43:16.211042300Z 	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:717)
2024-03-15T05:43:16.211368299Z 	at ei.algobaroapi.domain.auth.service.AuthService$$SpringCGLIB$$0.signIn(<generated>)
2024-03-15T05:43:16.211666496Z 	at ei.algobaroapi.domain.auth.controller.AuthController.signIn(AuthController.java:41)
2024-03-15T05:43:16.211958396Z 	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
2024-03-15T05:43:16.212367285Z 	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
2024-03-15T05:43:16.212647227Z 	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
2024-03-15T05:43:16.212906061Z 	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
2024-03-15T05:43:16.213169431Z 	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:351)
2024-03-15T05:43:16.213427593Z 	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:713)
2024-03-15T05:43:16.213808001Z 	at ei.algobaroapi.domain.auth.controller.AuthController$$SpringCGLIB$$0.signIn(<generated>)
2024-03-15T05:43:16.214104901Z 	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
2024-03-15T05:43:16.214496389Z 	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
2024-03-15T05:43:16.214788345Z 	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
2024-03-15T05:43:16.215103182Z 	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
2024-03-15T05:43:16.215390499Z 	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:261)
2024-03-15T05:43:16.215681574Z 	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:189)
2024-03-15T05:43:16.215982072Z 	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:118)
2024-03-15T05:43:16.216279735Z 	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:917)
2024-03-15T05:43:16.216569212Z 	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:829)
2024-03-15T05:43:16.216855890Z 	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
2024-03-15T05:43:16.217331186Z 	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1089)
2024-03-15T05:43:16.217343037Z 	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:979)
2024-03-15T05:43:16.217347864Z 	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014)
2024-03-15T05:43:16.217352577Z 	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:914)
2024-03-15T05:43:16.217357008Z 	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
2024-03-15T05:43:16.217361497Z 	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885)
2024-03-15T05:43:16.218348577Z 	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
2024-03-15T05:43:16.218642384Z 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:205)
2024-03-15T05:43:16.218927971Z 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)
2024-03-15T05:43:16.221270968Z 	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)
2024-03-15T05:43:16.221285260Z 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)
2024-03-15T05:43:16.221290759Z 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)
2024-03-15T05:43:16.221295451Z 	at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:108)
2024-03-15T05:43:16.221300014Z 	at org.springframework.security.web.FilterChainProxy.lambda$doFilterInternal$3(FilterChainProxy.java:231)
2024-03-15T05:43:16.221304564Z 	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:365)
2024-03-15T05:43:16.221309299Z 	at org.springframework.security.web.access.intercept.AuthorizationFilter.doFilter(AuthorizationFilter.java:100)
2024-03-15T05:43:16.221313806Z 	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374)
2024-03-15T05:43:16.221318259Z 	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:126)
2024-03-15T05:43:16.221322720Z 	at org.springframework.security.web.access.ExceptionTranslationFilter.doFilter(ExceptionTranslationFilter.java:120)
2024-03-15T05:43:16.221327319Z 	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374)
2024-03-15T05:43:16.221332030Z 	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:131)
2024-03-15T05:43:16.221336656Z 	at org.springframework.security.web.session.SessionManagementFilter.doFilter(SessionManagementFilter.java:85)
2024-03-15T05:43:16.221341174Z 	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374)
2024-03-15T05:43:16.221371782Z 	at org.springframework.security.web.authentication.AnonymousAuthenticationFilter.doFilter(AnonymousAuthenticationFilter.java:100)
2024-03-15T05:43:16.221385659Z 	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374)
2024-03-15T05:43:16.221390550Z 	at org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter.doFilter(SecurityContextHolderAwareRequestFilter.java:179)
2024-03-15T05:43:16.221395052Z 	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374)
2024-03-15T05:43:16.221400293Z 	at org.springframework.security.web.savedrequest.RequestCacheAwareFilter.doFilter(RequestCacheAwareFilter.java:63)
2024-03-15T05:43:16.221404800Z 	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374)
2024-03-15T05:43:16.221409393Z 	at ei.algobaroapi.global.jwt.JwtAuthenticationFilter.doFilter(JwtAuthenticationFilter.java:32)
2024-03-15T05:43:16.221413766Z 	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374)
2024-03-15T05:43:16.221418176Z 	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:107)
2024-03-15T05:43:16.221422529Z 	at org.springframework.security.web.authentication.logout.LogoutFilter.doFilter(LogoutFilter.java:93)
2024-03-15T05:43:16.221426949Z 	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374)
2024-03-15T05:43:16.221431306Z 	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91)
2024-03-15T05:43:16.221435621Z 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
2024-03-15T05:43:16.225549726Z 	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374)
2024-03-15T05:43:16.225932886Z 	at org.springframework.security.web.header.HeaderWriterFilter.doHeadersAfter(HeaderWriterFilter.java:90)
2024-03-15T05:43:16.227666920Z 	at org.springframework.security.web.header.HeaderWriterFilter.doFilterInternal(HeaderWriterFilter.java:75)
2024-03-15T05:43:16.228069938Z 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
2024-03-15T05:43:16.228391101Z 	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374)
2024-03-15T05:43:16.228700054Z 	at org.springframework.security.web.context.SecurityContextHolderFilter.doFilter(SecurityContextHolderFilter.java:82)
2024-03-15T05:43:16.228995269Z 	at org.springframework.security.web.context.SecurityContextHolderFilter.doFilter(SecurityContextHolderFilter.java:69)
2024-03-15T05:43:16.229294286Z 	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374)
2024-03-15T05:43:16.229576280Z 	at org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter.doFilterInternal(WebAsyncManagerIntegrationFilter.java:62)
2024-03-15T05:43:16.229875875Z 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
2024-03-15T05:43:16.230189988Z 	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374)
2024-03-15T05:43:16.230495873Z 	at org.springframework.security.web.session.DisableEncodeUrlFilter.doFilterInternal(DisableEncodeUrlFilter.java:42)
2024-03-15T05:43:16.230772014Z 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
2024-03-15T05:43:16.231047243Z 	at org.springframework.security.web.FilterChainProxy$VirtualFilterChain.doFilter(FilterChainProxy.java:374)
2024-03-15T05:43:16.231323392Z 	at org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:233)
2024-03-15T05:43:16.231601005Z 	at org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:191)
2024-03-15T05:43:16.231881452Z 	at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:113)
2024-03-15T05:43:16.232154363Z 	at org.springframework.web.servlet.handler.HandlerMappingIntrospector.lambda$createCacheFilter$3(HandlerMappingIntrospector.java:195)
2024-03-15T05:43:16.232437329Z 	at org.springframework.web.filter.CompositeFilter$VirtualFilterChain.doFilter(CompositeFilter.java:113)
2024-03-15T05:43:16.232718486Z 	at org.springframework.web.filter.CompositeFilter.doFilter(CompositeFilter.java:74)
2024-03-15T05:43:16.233015234Z 	at org.springframework.security.config.annotation.web.configuration.WebMvcSecurityConfiguration$CompositeFilterChainProxy.doFilter(WebMvcSecurityConfiguration.java:225)
2024-03-15T05:43:16.233303899Z 	at org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:352)
2024-03-15T05:43:16.233577492Z 	at org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:268)
2024-03-15T05:43:16.233862324Z 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)
2024-03-15T05:43:16.234146387Z 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)
2024-03-15T05:43:16.235537465Z 	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)
2024-03-15T05:43:16.236218099Z 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
2024-03-15T05:43:16.237158801Z 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)
2024-03-15T05:43:16.237517979Z 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)
2024-03-15T05:43:16.237857480Z 	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)
2024-03-15T05:43:16.241694785Z 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
2024-03-15T05:43:16.242025867Z 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)
2024-03-15T05:43:16.242341950Z 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)
2024-03-15T05:43:16.242684711Z 	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)
2024-03-15T05:43:16.242982130Z 	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
2024-03-15T05:43:16.243267143Z 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)
2024-03-15T05:43:16.243561532Z 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)
2024-03-15T05:43:16.243853032Z 	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
2024-03-15T05:43:16.244160228Z 	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
2024-03-15T05:43:16.244450615Z 	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:482)
2024-03-15T05:43:16.244730387Z 	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:115)
2024-03-15T05:43:16.245027339Z 	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
2024-03-15T05:43:16.245313372Z 	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
2024-03-15T05:43:16.245705543Z 	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:340)
2024-03-15T05:43:16.246008376Z 	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:391)
2024-03-15T05:43:16.247815806Z 	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
2024-03-15T05:43:16.248144086Z 	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:896)
2024-03-15T05:43:16.248455106Z 	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1744)
2024-03-15T05:43:16.248756265Z 	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)
2024-03-15T05:43:16.249053111Z 	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
2024-03-15T05:43:16.249348134Z 	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
2024-03-15T05:43:16.249678303Z 	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
2024-03-15T05:43:16.249965759Z 	at java.base/java.lang.Thread.run(Thread.java:833)

 */
