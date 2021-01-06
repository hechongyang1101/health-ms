package com.he.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.he.dao.PermissionDao;
import com.he.dao.RoleDao;
import com.he.dao.UserDao;
import com.he.pojo.Permission;
import com.he.pojo.Role;
import com.he.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        if(user == null){
            return null;
        }
        Integer userId = user.getId();
        Set<Role> roles = roleDao.findByUserId(userId);
        if(roles != null && roles.size() > 0){
            for(Role role : roles){
                Integer roleId = role.getId();
                Set<Permission> permissions = permissionDao.findByRoleId(roleId);
                if(permissions != null && permissions.size() > 0){
                    role.setPermissions(permissions);
                }
            }
            user.setRoles(roles);
        }
        return user;
    }
}
