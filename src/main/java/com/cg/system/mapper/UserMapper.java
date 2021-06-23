package com.cg.system.mapper;

import com.cg.entity.pojo.system.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {

    List<User> findByKeyword(@Param("paramsMap") Map<String, Object> map);

    User findByUsername(@Param("paramsMap") Map<String, Object> map);

    User findByUsernameAndPassword(@Param("paramsMap") Map<String, Object> map);

    Integer countRows(@Param("paramsMap") Map<String, Object> map);

    void updateByUsername(User user);

    void save(User user);
}
