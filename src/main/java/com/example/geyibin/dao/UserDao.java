package com.example.geyibin.dao;

import com.example.geyibin.mapper.UserMapper;
import com.example.geyibin.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    public Integer register(User user){
        return userMapper.insert(user);
    }

    public User loginIn(String userName,String password){
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("userName",userName)
                .andEqualTo("password",password);
        return userMapper.selectOneByExample(example);
    }

    public Integer updateUser(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public User findUserById(Long userId){
        return userMapper.selectByPrimaryKey(userId);
    }

    public List<User> findAllUser(){
        return userMapper.selectAll();
    }

    public Integer countAllUser(){
        Example example = new Example(User.class);
        return userMapper.selectCountByExample(example);
    }
}
