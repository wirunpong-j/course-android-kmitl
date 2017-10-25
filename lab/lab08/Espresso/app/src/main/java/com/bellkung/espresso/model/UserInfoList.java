package com.bellkung.espresso.model;

import java.util.List;

/**
 * Created by BellKunG on 22/10/2017 AD.
 */

public class UserInfoList {
    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    private List<UserInfo> userInfoList;
}
