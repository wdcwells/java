package com.wdc.learnning.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理是由java内部的反射机制来实现的，cglib动态代理底层则是借助asm来实现的。
 * 总的来说，反射机制在生成类的过程中比较高效，而asm在生成类之后的相关执行过程中比较高效
 * （可以通过将asm生成的类进行缓存，这样解决asm生成类过程低效问题）。
 * 还有一点必须注意：jdk动态代理的应用前提，必须是目标类基于统一的接口。
 * 如果没有上述前提，jdk动态代理不能应用。由此可以看出，jdk动态代理有一定的局限性，
 * cglib这种第三方类库实现的动态代理应用更加广泛，且在效率上更有优势。
 */
public class JdkProxy {

    public static void main(String[] args) {
        int a = 1 | 16;
        System.out.println(a);
        UserService userService = new UserServiceImpl();
        InvocationHandler invocationHandler = new MyInvocationHandler(userService);
        ClassLoader classLoader = userService.getClass().getClassLoader();
        UserService userServiceProxy = (UserService) Proxy.newProxyInstance(classLoader,
                userService.getClass().getInterfaces(), invocationHandler);
        System.out.println(userServiceProxy.getName(1));
        System.out.println(userServiceProxy.getAge(1));
    }

    private static class MyInvocationHandler implements InvocationHandler {
        private Object target;

        MyInvocationHandler() {
            super();
        }

        MyInvocationHandler(Object target) {
            super();
            this.target = target;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] args) throws Throwable {
            if ("getName".equals(method.getName())) {
                System.out.println("++++++before " + method.getName() + "++++++");
                Object result = method.invoke(target, args);
                System.out.println("++++++after " + method.getName() + "++++++");
                return result;
            } else {
                Object result = method.invoke(target, args);
                return result;
            }

        }
    }

    public interface UserService {
        String getName(int id);

        Integer getAge(int id);
    }

    private static class UserServiceImpl implements UserService {
        @Override
        public String getName(int id) {
            System.out.println("------getName------");
            return "Tom";
        }

        @Override
        public Integer getAge(int id) {
            System.out.println("------getAge------");
            return 10;
        }
    }
}
