package repository;

import repository.po.UserInfo;

import java.io.IOException;
import java.util.List;

public interface IUserRepository {
    UserInfo getUser(int userId);
    int addUser(UserInfo userInfo);
    int addUserMulti(List<UserInfo> userInfos);
    void userLogin(String userName,String password);
}
