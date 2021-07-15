package com.xbw.spring.boot.framework.interceptor;

import org.beetl.sql.ext.DebugInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://gitee.com/xiandafu/beetlsql/issues/IXZL4
 */
public class BeetlSqlDebugInterceptor extends DebugInterceptor {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private boolean isSimple;

    public BeetlSqlDebugInterceptor() {
        super();
    }

    public BeetlSqlDebugInterceptor(boolean isSimple) {
        this.isSimple = isSimple;
    }

    @Override
    protected void println(String str) {
        logger.debug("\r\n{}", str);
    }

    @Override
    protected void error(String str) {
        logger.error("\r\n{}", str);
    }

    @Override
    protected boolean isSimple(String sqlId) {
        logger.debug("isSimple : {}", isSimple);
        return isSimple;
    }

}