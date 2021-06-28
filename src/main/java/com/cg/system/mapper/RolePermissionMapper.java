package com.cg.system.mapper;

import com.cg.entity.pojo.system.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RolePermissionMapper {

    List<RolePermission> findByRoleId(@Param("paramsMap") Map<String, Object> map);

    void updateById(RolePermission rolePermission);

    void save(RolePermission rolePermission);
}
