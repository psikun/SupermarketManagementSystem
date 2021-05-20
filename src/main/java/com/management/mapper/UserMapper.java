package com.management.mapper;

import com.management.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author SiKun
 * @date 2021/05/20/ 13:07
 */
public interface UserMapper {
    /**
     * 查询所有的用户
     * @return 返回用户列表
     */
    List<User> findAllUsers();

    /**
     * 登录接口
     * @param username 用户名
     * @param password 密码
     * @return 返回登录对象
     */
    User login(@Param("username") String username, @Param("password") String password);

    /**
     * 用户注册接口
     * @param username 用户名
     * @param password 密码
     * @return 是否成功
     */
    int register(@Param("username") String username, @Param("password") String password);

    /**
     * 通过Id查询用户
     * @param id id号码
     * @return 查询到的用户
     */
    User findUserById(int id);

    /**
     * 通过用户名查询用户
     * @param username 用户名
     * @return 查询到的用户
     */
    User findUserByName(String username);

    /**
     * 修改用户权限
     * @param id 用户id
     * @return 是否成功
     */
    int chmod(int id);

    /**
     * 删除用户
     * @param id 用户Id
     * @return 是否成功
     */
    int deleteUser(int id);

}
