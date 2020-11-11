package com.gdut.jiyi.mapper;

import com.gdut.jiyi.model.User;
import com.gdut.jiyi.model.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExampleWithBLOBs(UserExample example);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int upDateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int upDateByExampleWithBLOBs(@Param("record") User record, @Param("example") UserExample example);

    int upDateByExample(@Param("record") User record, @Param("example") UserExample example);

    int upDateByPrimaryKeySelective(User record);

    int upDateByPrimaryKeyWithBLOBs(User record);

    int upDateByPrimaryKey(User record);
}