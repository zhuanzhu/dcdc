package com.egeo.components.product.facade;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.user.client.UserClient;
import com.egeo.components.user.client.UserExtendClient;
import com.egeo.components.user.dto.UserDTO;
import com.egeo.components.user.dto.UserExtendDTO;

/**
 * Created by 0.0 on 2018/9/10.
 */
@Component
public class UserExtendFacade {

    @Autowired
    private UserExtendClient userExtendReadService;
    @Autowired
    private UserClient userReadService;


    public List<Long> findUserList(String name, String mail, String mobile, Integer sex, Date birthday, List<Long> companyId, Long platformId) {
        return com.egeo.utils.StringUtils.stringsToLongs(userExtendReadService.findUserList(name,mail,mobile,sex,birthday,com.egeo.utils.StringUtils.longsToStrings(companyId),platformId));

    }

    public UserDTO findUserById(Long userId) {
        return userReadService.findUserByID(userId);
    }

    public UserExtendDTO findUserExtendById(Long userId) {
        return userExtendReadService.findById(userId);
    }



    public UserDTO findUserBymail(String mail) {
        return userReadService.findByMail(mail);
    }
}
