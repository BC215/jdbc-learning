package kr.co.iei.common;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {
    public static SqlSession getSqlSession() {
        SqlSession session = null;
        String resource = "mybatis-config.xml";

        try {
            InputStream is = Resources.getResourceAsStream(resource);
            // mybatis-config.xml 파일을 InputStream으로 읽어옴(설정값을 읽어오기위함

            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            //SQlSessionFactoryBuilder를 만들수 있는 팩토리 빌더
            SqlSessionFactory factory = builder.build(is);
            //설정파일을 바탕으로 접속객체를 생성하는 factory 생성
            session = factory.openSession(false); // 자동 커밋 false
            //실제 접속객체인 SqlSession 생성(매개변수 false는 트랜젝션관리를 직접하겠다는 의미)
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
    }
}