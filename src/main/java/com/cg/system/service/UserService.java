package com.cg.system.service;

import com.cg.common.Constants;
import com.cg.common.PagedResult;
import com.cg.common.Result;
import com.cg.entity.pojo.system.User;
import com.cg.enums.DeletedStatus;
import com.cg.system.mapper.UserMapper;
import com.cg.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedResult findByKeyword(String keyword, Integer currentPage, Integer pageSize) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("startNum", (currentPage - 1) * pageSize);
        map.put("pageSize", pageSize);

        List<User> userList = userMapper.findByKeyword(map);

        if (userList == null || userList.size() == 0) {
            return new PagedResult();
        }

        userList.forEach(this::addMask);

        Integer records = userMapper.countRows(map);

        int total = PagedResult.countTotal(records, pageSize);

        return new PagedResult(currentPage, total, records, userList);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public User findByUsername(String username) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("username", username);

        User user = userMapper.findOne(map);

        return addMask(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public User findByUsernameAndPassword(String username, String password) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);

        User user = userMapper.findOne(map);

        return addMask(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result updateById(User user) {

        String username = user.getUsername();
        Long id = user.getId();

        if (userIsExists(username, id)) {
            return Result.errorMsg("username already exists！");
        }

        user.setCgModified(new Date());
        user.setCgModifiedBy(Constants.BY);

        userMapper.updateById(user);
        return Result.ok("修改成功！");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result deleteById(User user) {

        user.setDeleted(DeletedStatus.DELETED.getCode());
        user.setCgModified(new Date());
        user.setCgModifiedBy(Constants.BY);

        userMapper.updateById(user);

        return Result.ok("删除成功");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Result save(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        if (userIsExists(username, null)) {
            return Result.errorMsg("username already exists！");
        }

        //生成盐,默认长度 16 位
//        String salt = BCrypt.gensalt(16);
        String salt = "root";
        String pwd = MD5Utils.encode(password, salt);

        Date date = new Date();
        user.setSalt(salt);
        user.setPassword(pwd);
        user.setDeleted(DeletedStatus.NON_DELETED.getCode());
        user.setCgCreated(date);
        user.setCgModified(date);
        user.setCgCreatedBy(Constants.BY);
        user.setCgModifiedBy(Constants.BY);

        userMapper.save(user);
        return Result.ok("添加成功！");
    }

    private boolean userIsExists(String username, Long id) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("username", username);

        List<User> userList = userMapper.findByUsername(map);

        //username is not exists
        if (userList == null || userList.size() == 0) {
            return false;
        }

        //username already exists when insert
        if (id == null || id == 0) {
            return true;
        }

        for (User user : userList) {
            //username already exists on another user when update
            if (!id.equals(user.getId()))
                return true;
        }

        return false;
    }

    private User addMask(User user) {
        if (user != null) {
            String mask = Constants.MASK;
            user.setPassword(mask).setSalt(mask);
        }
        return user;
    }
}
