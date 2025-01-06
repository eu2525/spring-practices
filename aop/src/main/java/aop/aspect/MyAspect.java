package aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

	@Before("execution(public aop.domain.Product aop.service.ProductService.find(String) throws RuntimeException)")
	public void adviceBefore() {
		System.out.println("-- Before Advice --");
	}
	
	@After("execution(public aop.domain.Product aop.service.ProductService.find(String))")
	public void adviceAfter() {
		System.out.println("-- After Advice --");
	}
	
	@AfterReturning("execution(* aop.service.ProductService.find(..))")
	public void adviceReturning() {
		System.out.println("-- AfterReturning Advice --");
	}
	
	@AfterThrowing(value="execution(* *..*.ProductService.find(..))", throwing="ex")
	public void adviceAfterThrowing(Throwable ex) {
		System.out.println("-- AfterThrowing[" + ex.getMessage() + "] Advice --");
	}
	
	@Around(value="execution(* *..*.*.*(..))")
	public Object adviceAround(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("-- Around Advice : Before --");
		
		Object result = pjp.proceed();
		
		System.out.println("-- Around Advice : After --");
		
		return result;
	}
	
}
