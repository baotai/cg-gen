package com.cg.system.mapper;

import com.cg.entity.pojo.system.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {

    /**查询用户---分页*/
    List<User> findByKeyword(@Param("paramsMap") Map<String, Object> map);

    /**根据username查询用户,用于新增、更新： username is exists*/
    List<User> findByUsername(@Param("paramsMap") Map<String, Object> map);

    /**查询用户,可根据username、password、id*/
    User findOne(@Param("paramsMap") Map<String, Object> map);

    /**查询用户数，用于分页*/
    Integer countRows(@Param("paramsMap") Map<String, Object> map);

    /**根据id修改用户信息*/
    void updateById(User user);

    /**添加用户*/
    void save(User user);
}
