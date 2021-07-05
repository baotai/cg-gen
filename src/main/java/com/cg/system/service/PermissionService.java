package com.cg.system.service;

import com.cg.common.constants.Constants;
import com.cg.common.PagedResult;
import com.cg.common.Result;
import com.cg.entity.pojo.system.Permission;
import com.cg.enums.DeletedStatus;
import com.cg.system.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedResult findByKeyword(String keyword, Integer currentPage, Integer pageSize) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("startNum", (currentPage - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<Permission> permissionList = permissionMapper.findByKeyword(map);

        if (permissionList == null || permissionList.size() == 0) {
            return new PagedResult();
        }

        Integer records = permissionMapper.countRows(map);

        int total = PagedResult.countTotal(records, pageSize);

        return new PagedResult(currentPage, total, records, permissionList);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Permission> findByName(String name) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);

        return permissionMapper.findByName(map);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Permission> findByIdList(List<Long> idList) {
        return permissionMapper.findByIdList(idList);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result updateById(Permission permission) {

        String name = permission.getName();
        Long id = permission.getId();

        if (roleIsExists(name, id)) {
            return Result.errorMsg("name already exists！");
        }

        permission.setCgModified(new Date());
        permission.setCgModifiedBy(Constants.BY);

        permissionMapper.updateById(permission);
        return Result.ok("success！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result save(Permission permission) {

        String name = permission.getName();

        if (roleIsExists(name, null)) {
            return Result.errorMsg("name already exists！");
        }

        Date date = new Date();
        permission.setDeleted(DeletedStatus.NON_DELETED.getCode());
        permission.setCgCreated(date);
        permission.setCgModified(date);
        permission.setCgCreatedBy(Constants.BY);
        permission.setCgModifiedBy(Constants.BY);

        permissionMapper.save(permission);
        return Result.ok("success！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result deleteById(Permission permission) {

        permission.setDeleted(DeletedStatus.DELETED.getCode());
        permission.setCgModified(new Date());
        permission.setCgModifiedBy(Constants.BY);

        permissionMapper.updateById(permission);

        return Result.ok("success");
    }

    private boolean roleIsExists(String name, Long id) {

        List<Permission> permissionList = findByName(name);

//        role name is not exists
        if (permissionList == null || permissionList.size() == 0) {
            return false;
        }

        //username already exists when insert
        if (id == null || id == 0) {
            return true;
        }

        for (Permission permission : permissionList) {
            //roleName already exists on another role when update
            if (!permission.getId().equals(id)) {
                return true;
            }
        }

        return false;
    }
}
