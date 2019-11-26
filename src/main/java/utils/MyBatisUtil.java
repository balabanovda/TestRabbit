package utils;


import mapper.PersonMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;

public class MyBatisUtil {
    public static final String DRIVER = "org.postgresql.Driver";// скачать 9ый постгрес
    public static final String URL = "jdbc:postgresql://localhost:5433/postgres";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "spartacus10";
    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSessionFactory buildSqlSessionFactory() {
        DataSource dataSource = new PooledDataSource(DRIVER, URL, USERNAME, PASSWORD);
        Environment environment = new Environment("Development", new JdbcTransactionFactory(), dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(PersonMapper.class);
        //configuration.addMapper(AddressMapper.class);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(configuration);
        return factory;

    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public String getPersonByName(String name) {
        return new SQL() {
            {
                SELECT("*");
                FROM("person");
                WHERE("name like #{name} || '%'");
            }
        }.toString();
    }
}