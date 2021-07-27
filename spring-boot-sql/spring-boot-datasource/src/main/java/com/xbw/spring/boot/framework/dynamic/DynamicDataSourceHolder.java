package com.xbw.spring.boot.framework.dynamic;

public final class DynamicDataSourceHolder {

    /**
     * 使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本,保证线程间互不干涉。
     * <p>
     * Use ThreadLocal to maintain variables. ThreadLocal provides an independent copy of the variable for each thread that uses the variable.
     * So each thread can change its own copy independently, without affecting the copy corresponding to other threads, ensuring that threads do not interfere with each other.
     */
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    private DynamicDataSourceHolder() {
    }


    public static String getDataSource() {
        return CONTEXT.get();
    }

    public static void setDataSource(String dataSource) {
        CONTEXT.set(dataSource);
    }

    public static void clearDataSource() {
        CONTEXT.remove();
    }
}
