package fr.orsys.aop;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component("loggerInterceptor")
@Aspect
public class LoggerInterceptor {
	@Before("execution(* fr.orsys.service.*.*(..)) || execution(* fr.orsys.dao.*.*(..))") 
	public void logBefore(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		String name = joinPoint.getSignature().getName();
		StringBuffer sb = new StringBuffer(name 
							+ " appelé avec en paramètres : ");
		sb.append(Arrays.toString(args));
		Logger logger = Logger.getLogger(joinPoint.getTarget().getClass());
		logger.info(sb);
	}
}


