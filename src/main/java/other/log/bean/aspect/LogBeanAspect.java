//package br.com.projeto.crud.infra.annotation.aspect;
//
//import java.lang.reflect.Method;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import br.com.projeto.crud.infra.annotation.LogBean;
//
//@Aspect
//@Component
//public class LogBeanAspect {
//
//	private static final Logger LOG = LoggerFactory.getLogger(LogBean.class.getSimpleName());
//	private static final String LOG_BEFORE_MSG = "-- CREATING BEAN >>> {{}}";
//	private static final String LOG_AFTER_MSG = "-- CREATED BEAN  >>> {{}}: {}";
//
//	@Before("@annotation(br.com.projeto.crud.infra.annotation.LogBean)")
//	public void beforeMethod(JoinPoint joinPoint) throws Exception {
//		printLog(joinPoint, LOG_BEFORE_MSG);
//	}
//
//	@After("@annotation(br.com.projeto.crud.infra.annotation.LogBean)")
//	public void afterMethod(JoinPoint joinPoint) throws Exception {
//		printLog(joinPoint, LOG_AFTER_MSG);
//	}
//
//	private static final void printLog(JoinPoint joinPoint, String logMessage) throws Exception {
//		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//		Method method = methodSignature.getMethod();
//
//		if (method.getAnnotation(Bean.class) != null) {
//			LOG.info(logMessage, methodSignature.getReturnType().getSimpleName(),
//					method.getAnnotation(LogBean.class).message());
//			return;
//		}
//	}
//
//}


