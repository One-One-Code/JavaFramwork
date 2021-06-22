package repository;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import repository.po.UserInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class OrmHelper {
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;

    public OrmHelper() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("config.xml");

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
    }

    private void openSession(boolean autoCommit) {
        sqlSession = sqlSessionFactory.openSession(autoCommit);
    }

    private void openSession() {
        openSession(false);
    }

    private void closeSession() {
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    public <T> T selectOne(String mapperId, Object obj) {
        openSession();
        T data = sqlSession.selectOne(mapperId, obj);
        closeSession();
        return data;
    }

    public <T> List<T> selectAll(String mapperId, Object obj, boolean close) {
        openSession();
        List<T> data = sqlSession.selectList(mapperId, obj);
        if (close) {
            closeSession();
        }

        return data;
    }

    public <T> int insert(String mapperId, T obj, boolean commit) {
        openSession();
        int data = sqlSession.insert(mapperId, obj);
        if (commit) {
            sessionCommit();
            closeSession();
        }
        return data;
    }

    public void sessionCommit() {
        if (sqlSession != null) {
            sqlSession.commit();
        }
    }
}
