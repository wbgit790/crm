package com.bj.crm.utils;

import java.lang.reflect.InvocationHandler;

public class ServiceFactory {
	
	public static Object getService(Object service){

/*		创建目标对象
		UsbSell factory = new UsbKingFactory();
		创建InvocationHandler类对象
		InvocationHandler handler = new MyUsbHandler(factory); //MyUsbHandler继承了该接口，实现了invoke功能，实现了增强

		 UsbSell proxy = (UsbSell) Proxy.newProxyInstance(factory.getClass().getClassLoader(),
                factory.getClass().getInterfaces(),
                handler);

		*/
		return new TransactionInvocationHandler(service).getProxy(); //返回了Proxy.newProxyInstance，返回了一个代理对象
		
	}
	
}
