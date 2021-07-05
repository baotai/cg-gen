package com.cg.system.service;

import com.cg.common.constants.Constants;
import com.cg.common.Result;
import com.cg.entity.bo.system.UserRoleBO;
import com.cg.entity.pojo.system.Role;
import com.cg.entity.pojo.system.UserRole;
import com.cg.entity.vo.system.UserRoleVO;
import com.cg.enums.DeletedStatus;
import com.cg.system.mapper.UserRoleMapper;
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
public class UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleService roleService;

    @Transactional(propagation = Propagation.SUPPORTS)
    public Result getByUserId(Long userId) {

        List<UserRole> userRoles = findByUserId(userId);

        if (userRoles == null || userRoles.size() == 0) {
            return Result.errorMsg("该用户未绑定角色");
        }

        List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());

        List<Role> roleList = roleService.findByIdList(roleIds);

        return Result.ok(new UserRoleVO(userId, roleList));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<UserRole> findByUserId(Long userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        return userRoleMapper.findByUserId(map);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void update(UserRoleBO userRoleBO) {

        Long userId = userRoleBO.getUserId();
        List<Long> roleIds = userRoleBO.getRoleIds();

        List<UserRole> userRoles = findByUserId(userId);

        //删除绑定
        userRoles
                .stream()
                .filter(e -> !roleIds.contains(e.getRoleId()))
                .forEach(e -> updateById(e.getId(), userId, e.getRoleId(), DeletedStatus.DELETED.getCode()));

        Map<Long, UserRole> userRoleMap = userRoles
                .stream()
                .collect(Collectors.toMap(UserRole::getRoleId, e -> e, (k1, k2) -> k2));

        roleIds.stream().filter(roleId -> roleId != null && roleId != 0).forEach(roleId -> {

            UserRole userRole = userRoleMap.get(roleId);
            //已绑定角色
            if (userRole != null) {
                //已删除状态---->改为未删除状态
                if (DeletedStatus.DELETED.getCode().equals(userRole.getDeleted())) {
                    updateById(userRole.getId(), userId, roleId, DeletedStatus.NON_DELETED.getCode());
                }
            } else {
                save(userId, roleId);
            }
        });
    }

    private void updateById(Long id, Long userId, Long roleId, Byte deleted) {

        UserRole userRole = new UserRole(userId, roleId);
        userRole.setId(id);
        userRole.setDeleted(deleted);
        userRole.setCgModified(new Date());
        userRole.setCgModifiedBy(Constants.BY);

        userRoleMapper.updateById(userRole);
    }

    private void save(Long userId, Long roleId) {

        UserRole userRole = new UserRole(userId, roleId);

        Date date = new Date();
        userRole.setDeleted(DeletedStatus.NON_DELETED.getCode());
        userRole.setCgCreated(date);
        userRole.setCgModified(date);
        userRole.setCgCreatedBy(Constants.BY);
        userRole.setCgModifiedBy(Constants.BY);

        userRoleMapper.save(userRole);
    }
}
