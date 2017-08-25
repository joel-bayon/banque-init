package fr.orsys.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

import util.JpaUtil;

@Component
public class TransactionAdvisor {
	
	public Object transactionProceeding(ProceedingJoinPoint joinPoint) throws Throwable {
		JpaUtil.getCurrentEntityManager().getTransaction().begin();
		Object result = null;
		try {
			result = joinPoint.proceed();
			JpaUtil.getCurrentEntityManager().getTransaction().commit();
		}
		catch(Throwable e) {
			JpaUtil.getCurrentEntityManager().getTransaction().rollback();
			throw e;
		}
		return result;
	}

}
