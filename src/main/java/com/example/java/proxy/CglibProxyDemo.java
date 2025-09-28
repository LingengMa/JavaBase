package com.example.java.proxy;

/**
 * @author LingengMa
 * @date 2025/09/27 17:03
 * @Description:
 */

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;

// 1. 没有实现接口的目标类
class HelloService {
    public void sayHello() {
        System.out.println("Hello, world!");
    }
}

// 2. 自定义方法拦截器
class CglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("【CGLIB动态代理】方法调用前");
        Object result = proxy.invokeSuper(obj, args);  // 调用原始方法
        System.out.println("【CGLIB动态代理】方法调用后");
        return result;
    }
}

// 3. 测试类
public class CglibProxyDemo {
    public static void main(String[] args) {
        // 创建Enhancer对象，类似于JDK Proxy的Factory
        Enhancer enhancer = new Enhancer();

        // 设置父类（目标类）
        enhancer.setSuperclass(HelloService.class);

        // 设置拦截器
        enhancer.setCallback(new CglibProxy());

        // 创建代理对象
        HelloService proxy = (HelloService) enhancer.create();

        // 调用代理方法
        proxy.sayHello();
    }
}
