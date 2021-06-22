package repository;

import repository.po.UserInfo;

import java.util.List;

public class UserRepository implements IUserRepository {

    @Override
    public UserInfo getUser(int userId) {
        try {
            //加载核心配置文件
            OrmHelper helper = new OrmHelper();
            UserInfo userInfo = helper.selectOne("userMapper.findUserById", userId);
            return userInfo;
        } catch (Exception e) {
            String message = e.getMessage();
        }
        return null;
    }

    @Override
    public int addUser(UserInfo userInfo) {
        try {
            OrmHelper helper = new OrmHelper();
            int count = helper.insert("userMapper.insertUser", userInfo,true);
            return count;
        } catch (Exception e) {
            String message = e.getMessage();
        }
        return 0;
    }

    @Override
    public int addUserMulti(List<UserInfo> userInfos) {
        try {
            OrmHelper helper = new OrmHelper();
            UserInfo userInfo = helper.selectOne("userMapper.findUserById", 122);
            userInfo = helper.selectOne("userMapper.findUserById", 122);
            int count = helper.insert("userMapper.insertUserBatch", userInfos, true);
            return count;
        } catch (Exception e) {
            String message = e.getMessage();
        }
        return 0;
    }

    @Override
    public void userLogin(String userName, String password) {

    }
}
