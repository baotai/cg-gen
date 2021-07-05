package com.cg.system.service;

import com.cg.common.constants.Constants;
import com.cg.common.Result;
import com.cg.entity.bo.system.RolePermissionBO;
import com.cg.entity.pojo.system.Permission;
import com.cg.entity.pojo.system.RolePermission;
import com.cg.entity.vo.system.RolePermissionVO;
import com.cg.enums.DeletedStatus;
import com.cg.system.mapper.RolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private PermissionService permissionService;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Result getByRoleId(Long roleId) {

        List<RolePermission> rolePermissions = findByRoleId(roleId);

        if (rolePermissions == null || rolePermissions.size() == 0) {
            return Result.errorMsg("该角色未绑定权限");
        }

        List<Long> permissionIdList = rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());

        List<Permission> permissionList = permissionService.findByIdList(permissionIdList);

        return Result.ok(new RolePermissionVO(roleId, permissionList));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<RolePermission> findByRoleId(Long roleId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);

        return rolePermissionMapper.findByRoleId(map);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(RolePermissionBO rolePermissionBO) {

        Long roleId = rolePermissionBO.getRoleId();
        List<Long> permissionIds = rolePermissionBO.getPermissionIds();

        List<RolePermission> rolePermissions = findByRoleId(roleId);

        //删除绑定
        rolePermissions
                .stream()
                .filter(e -> !permissionIds.contains(e.getPermissionId()))
                .forEach(e -> updateById(e.getId(), roleId, e.getPermissionId(), DeletedStatus.DELETED.getCode()));

        Map<Long, RolePermission> rolePermissionMap = rolePermissions
                .stream()
                .collect(Collectors.toMap(RolePermission::getPermissionId, e -> e, (k1, k2) -> k2));

        permissionIds.stream().filter(permissionId -> permissionId != null && permissionId != 0).forEach(permissionId -> {

            RolePermission rolePermission = rolePermissionMap.get(permissionId);
            //已绑定权限
            if (rolePermission != null) {
                //已删除状态---->改为未删除状态
                if (DeletedStatus.DELETED.getCode().equals(rolePermission.getDeleted())) {
                    updateById(rolePermission.getId(), roleId, permissionId, DeletedStatus.NON_DELETED.getCode());
                }
            } else {
                save(roleId, permissionId);
            }
        });
    }

    private void updateById(Long id, Long roleId, Long permissionId, Byte deleted) {

        RolePermission rolePermission = new RolePermission(roleId, permissionId);
        rolePermission.setId(id);
        rolePermission.setDeleted(deleted);
        rolePermission.setCgModified(new Date());
        rolePermission.setCgModifiedBy(Constants.BY);

        rolePermissionMapper.updateById(rolePermission);
    }

    private void save(Long roleId, Long permissionId) {

        RolePermission rolePermission = new RolePermission(roleId, permissionId);

        Date date = new Date();
        rolePermission.setDeleted(DeletedStatus.NON_DELETED.getCode());
        rolePermission.setCgCreated(date);
        rolePermission.setCgModified(date);
        rolePermission.setCgCreatedBy(Constants.BY);
        rolePermission.setCgModifiedBy(Constants.BY);

        rolePermissionMapper.save(rolePermission);
    }
}
