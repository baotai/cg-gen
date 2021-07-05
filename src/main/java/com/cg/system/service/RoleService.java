package com.cg.system.service;

import com.cg.common.constants.Constants;
import com.cg.common.PagedResult;
import com.cg.common.Result;
import com.cg.entity.pojo.system.Role;
import com.cg.enums.DeletedStatus;
import com.cg.system.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedResult findByKeyword(String keyword, Integer currentPage, Integer pageSize) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("startNum", (currentPage - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<Role> roleList = roleMapper.findByKeyword(map);

        if (roleList == null || roleList.size() == 0) {
            return new PagedResult();
        }

        Integer records = roleMapper.countRows(map);

        int total = PagedResult.countTotal(records, pageSize);

        return new PagedResult(currentPage, total, records, roleList);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Role> findByRoleName(String roleName) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("roleName", roleName);

        return roleMapper.findByRoleName(map);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Role> findByIdList(List<Long> idList) {
        return roleMapper.findByIdList(idList);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result updateById(Role role) {

        String roleName = role.getRoleName();
        Long id = role.getId();

        if (roleIsExists(roleName, id)) {
            return Result.errorMsg("roleName already exists！");
        }

        role.setCgModified(new Date());
        role.setCgModifiedBy(Constants.BY);

        roleMapper.updateById(role);
        return Result.ok("修改成功！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result save(Role role) {

        String roleName = role.getRoleName();

        if (roleIsExists(roleName, null)) {
            return Result.errorMsg("roleName already exists！");
        }

        Date date = new Date();
        role.setDeleted(DeletedStatus.NON_DELETED.getCode());
        role.setCgCreated(date);
        role.setCgModified(date);
        role.setCgCreatedBy(Constants.BY);
        role.setCgModifiedBy(Constants.BY);

        roleMapper.save(role);
        return Result.ok("添加成功！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result deleteById(Role role) {

        role.setDeleted(DeletedStatus.DELETED.getCode());
        role.setCgModified(new Date());
        role.setCgModifiedBy(Constants.BY);

        roleMapper.updateById(role);

        return Result.ok("删除成功");
    }

    private boolean roleIsExists(String roleName, Long id) {

        List<Role> roleList = findByRoleName(roleName);

//        role name is not exists
        if (roleList == null || roleList.size() == 0) {
            return false;
        }

        //username already exists when insert
        if (id == null || id == 0) {
            return true;
        }

        for (Role role : roleList) {
            //roleName already exists on another role when update
            if (!role.getId().equals(id)) {
                return true;
            }
        }

        return false;
    }
}
