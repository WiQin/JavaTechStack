package com.wyw.mybatis.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtil {
    private final static Class<SqlSessionFactoryUtil> LOCK = SqlSessionFactoryUtil.class;

    private static SqlSessionFactory sqlSessionFactory;

    private SqlSessionFactoryUtil() {
    }

    public static SqlSessionFactory getFactory() {
        synchronized (LOCK) {
            if (sqlSessionFactory != null) {
                return sqlSessionFactory;
            }
            String resources = "mybatis-config.xml";
            InputStream inputStream;

            try {
                inputStream = Resources.getResourceAsStream(resources);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return sqlSessionFactory;
        }
    }

    public static SqlSession openSession() {
        if (sqlSessionFactory == null) {
            getFactory();
        }
        return sqlSessionFactory.openSession();
    }
}
