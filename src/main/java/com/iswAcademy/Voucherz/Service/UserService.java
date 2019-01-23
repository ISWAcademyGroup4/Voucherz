package com.iswAcademy.Voucherz.Service;

import com.iswAcademy.Voucherz.Dao.UserDao;
import com.iswAcademy.Voucherz.Model.User;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    public User createUser (User user);
    public boolean updateUser(Long id, User user);
    public User findUser(String Email);

}
