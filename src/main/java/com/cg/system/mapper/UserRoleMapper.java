package com.cg.system.mapper;

import com.cg.entity.pojo.system.UserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRoleMapper {

    List<UserRole> findByUserId(@Param("paramsMap") Map<String, Object> map);

    UserRole findByUserIdAndRoleId(@Param("paramsMap") Map<String, Object> map);

    void updateById(UserRole userRole);

    void save(UserRole userRole);
}
