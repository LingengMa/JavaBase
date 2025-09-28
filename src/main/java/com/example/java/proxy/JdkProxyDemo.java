package com.example.java.proxy;

/**
 * @author LingengMa
 * @date 2025/09/27 17:00
 * @Description:
 */


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 1. 定义接口
interface Hello {
    void sayHello();
}

// 2. 实现接口的类
class HelloImpl implements Hello {
    @Override
    public void sayHello() {
        System.out.println("Hello, world!");
    }
}

// 3. 实现 InvocationHandler 接口
class MyInvocationHandler implements InvocationHandler {
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("在方法调用前执行");
        Object result = method.invoke(target, args);
        System.out.println("在方法调用后执行");
        return result;
    }
}

// 4. 测试类
public class JdkProxyDemo {
    public static void main(String[] args) {
        // 创建被代理的对象
        Hello hello = new HelloImpl();

        // 创建 InvocationHandler 对象
        InvocationHandler handler = new MyInvocationHandler(hello);

        // 获取类加载器
        ClassLoader classLoader = hello.getClass().getClassLoader();

        // 创建代理对象
        Hello proxyHello = (Hello) Proxy.newProxyInstance(
                classLoader, new Class[]{Hello.class}, handler
        );

        // 通过代理对象调用方法
        proxyHello.sayHello();
    }
}
