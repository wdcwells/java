package com.wdc.learnning.thread;

public class ThreadLocalTest {
    private ThreadLocal<Long> id = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return super.initialValue();
        }
    };
    private ThreadLocal<String> name = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return super.initialValue();
        }
    };

    private static final ThreadLocal<ReferObject> obj = new ThreadLocal<>();

    public static void main(String[] args) throws Exception {
//        testInitialThreadLocals();
        testThreadLocalObject();
    }

    /**
     * threadlocal 将共享变量复制到本线程一份 隔离执行 变量状态的改变 仅本线程可见
     * 最常见的ThreadLocal使用场景为 用来解决 数据库连接、Session管理等
     * @throws InterruptedException
     */
    private static void testThreadLocalObject() throws InterruptedException {
        final ThreadLocalTest test = new ThreadLocalTest();
        test.set(1L, "test");
        final ReferObject obj = new ReferObject();

        obj.setName("wdc");
        test.setObj(obj);
        System.out.println(test.getObj());

        Thread thread = new Thread(() -> {
            System.out.println(obj.getName());//本线程没有set值进去，所以为null
            obj.setName("wqh");
            test.setObj(obj);
            System.out.println(test.getObj());
        });
        thread.start();
        thread.join();

        System.out.println(test.getObj());
    }

    private static class ReferObject {
        private ThreadLocal<String> name = new ThreadLocal<>();
        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        @Override
        public String toString() {
            return "ReferObject-" + super.toString() +
                    "{name='" + getName() + '\'' +
                    '}';
        }
    }

    private static void testInitialThreadLocals() throws InterruptedException {
        final ThreadLocalTest test = new ThreadLocalTest();
        System.out.println("main-set-before:" + test);
        test.set(1L, "wdc");
        System.out.println("main-set-after:" + test);

        Thread thread = new Thread(() -> {
            System.out.println("thread-set-after:" + test);
            test.set(2L, "wqh");
            System.out.println("thread-set-after:" + test);
        });
        thread.start();
        thread.join();

        System.out.println("main-set-end:" + test);
    }

    public void set(Long id, String name) {
        this.id.set(id);
        this.name.set(name);
    }

    public void setObj(ReferObject obj) {
        ThreadLocalTest.obj.set(obj);
    }

    public Long getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public ReferObject getObj() {
        return obj.get();
    }

    @Override
    public String toString() {
        return "ThreadLocalTest{" +
                "id=" + getId() +
                ", name=" + getName() +
                '}';
    }
}
