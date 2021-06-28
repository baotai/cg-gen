package com.cg.system.mapper;

import com.cg.entity.pojo.system.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoleMapper {

    /**
     * 查询角色---分页
     */
    List<Role> findByKeyword(@Param("paramsMap") Map<String, Object> map);

    /**
     * 根据role name查询角色,用于新增、修改： RoleName is exists
     */
    List<Role> findByRoleName(@Param("paramsMap") Map<String, Object> map);

    /**
     * 查询角色,可根据role name、id
     */
    Role findOne(@Param("paramsMap") Map<String, Object> map);

    /**
     * 根据 id list 查询角色， 用于用户角色分配
     */
    List<Role> findByIdList(List<Long> idList);

    /**
     * 查询角色数，用于分页
     */
    Integer countRows(@Param("paramsMap") Map<String, Object> map);

    /**
     * 根据id修改角色信息
     */
    void updateById(Role user);

    /**
     * 添加角色
     */
    void save(Role user);
}
