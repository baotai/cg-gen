package com.cg.system.mapper;

import com.cg.entity.pojo.system.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PermissionMapper {

    /**
     * 查询权限---分页
     */
    List<Permission> findByKeyword(@Param("paramsMap") Map<String, Object> map);

    /**
     * 根据permission name查询权限,用于新增、修改： RoleName is exists
     */
    List<Permission> findByName(@Param("paramsMap") Map<String, Object> map);

    /**
     * 查询权限,可根据name
     */
    Permission findOne(@Param("paramsMap") Map<String, Object> map);

    /**
     * 根据 id list 查询权限， 用于角色权限分配
     */
    List<Permission> findByIdList(List<Long> idList);

    /**
     * 查询权限数，用于分页
     */
    Integer countRows(@Param("paramsMap") Map<String, Object> map);

    /**
     * 根据id修改权限信息
     */
    void updateById(Permission permission);

    /**
     * 添加权限
     */
    void save(Permission permission);
}
