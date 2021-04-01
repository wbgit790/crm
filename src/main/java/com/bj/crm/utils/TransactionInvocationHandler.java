package com.bj.crm.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.ibatis.session.SqlSession;

public class TransactionInvocationHandler implements InvocationHandler{
	
	private Object target;
	
	public TransactionInvocationHandler(Object target){
		
		this.target = target;
		
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//业务处理动态代理，多了一个session？
		SqlSession session = null;
		
		Object obj = null;

		try{
			session = SqlSessionUtil.getSqlSession();
			// 等同于  float price = factory.sell(amout); 厂家的价格。通过Method可以执行某个目标类的法
			obj = method.invoke(target, args);
			
			session.commit();
		}catch(Exception e){
			session.rollback();
			e.printStackTrace();
			
			//处理的是什么异常，继续往上抛什么异常
			throw e.getCause();
		}finally{
			SqlSessionUtil.myClose(session);
		}
		
		return obj;
	}
	
	public Object getProxy(){

/*		Proxy:核心的对象，创建代理对象。之前创建对象都是new类的构造方法，现在用proxy代替new的使用
		方法：静态方法   newProxyInstance()
		作用：创建代理对象，等同于 TaoBao taobao = new TaoBao*/
		
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);
		
	}
	
}











































