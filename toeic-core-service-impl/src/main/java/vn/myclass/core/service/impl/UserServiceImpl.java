package vn.myclass.core.service.impl;

import vn.myclass.core.dao.UserDao;
import vn.myclass.core.daoimpl.UserDaoImpl;
import vn.myclass.core.dto.UserDTO;
import vn.myclass.core.persistence.etity.UserEntity;
import vn.myclass.core.service.UserService;
import vn.myclass.core.web.utils.UserBeanUtil;

public class UserServiceImpl implements UserService {
    public UserDTO isUserExist(UserDTO dto) {
        UserDao userDao=new UserDaoImpl();
        UserEntity userEntity= userDao.findUserByUsernameAnhPassword(dto.getName(),dto.getPassword());


        return UserBeanUtil.entityToDTO(userEntity);
    }

    public UserDTO findRoleByUser(UserDTO dto) {
        UserDao userDao=new UserDaoImpl();
        UserEntity userEntity= userDao.findUserByUsernameAnhPassword(dto.getName(),dto.getPassword());

        return UserBeanUtil.entityToDTO(userEntity);
    }
}
