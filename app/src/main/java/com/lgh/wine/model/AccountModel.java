package com.lgh.wine.model;

import com.lgh.wine.MyCallBack;
import com.lgh.wine.api.ApiFactory;
import com.lgh.wine.base.BaseModel;
import com.lgh.wine.beans.Account;
import com.lgh.wine.beans.LoginInput;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niujingtong on 2018/7/13.
 * 模块：
 */
public class AccountModel extends BaseModel {

    private static AccountModel model;

    public static AccountModel newInstance() {

        if (model == null) {
            model = new AccountModel();
        }
        return model;
    }

    /**
     * 登录
     *
     * @param callBack
     */
    public void login(String phone, String pwd, int type, MyCallBack callBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("phone", phone);
        params.put("sms_password", pwd);
        params.put("type", type);
        ApiFactory.getService().login(params).enqueue(callBack);
    }

    /**
     * 获取验证码
     *
     * @param phone
     * @param type
     * @param callBack
     */
    public void getSmsCode(String phone, int type, MyCallBack callBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("phone", phone);
        params.put("type", type);
        ApiFactory.getService().getSmsCode(params).enqueue(callBack);
    }

    /**
     * 验证手机号
     *
     * @param phone
     * @param sms_code
     * @param callBack
     */
    public void verifyCode(String phone, String sms_code, MyCallBack callBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("phone", phone);
        params.put("sms_code", sms_code);
        ApiFactory.getService().verifyCode(params).enqueue(callBack);
    }

    /**
     * 设置密码
     *
     * @param password
     * @param callBack
     */
    public void register(String phone, String password, MyCallBack callBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("phone", phone);
        params.put("password", password);
        ApiFactory.getService().register(params).enqueue(callBack);
    }

    /**
     * 反馈
     *
     * @param callBack
     */
    public void addFeedback(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().register(params).enqueue(callBack);
    }

    /**
     * 修改个人信息
     * @param params
     * @param callBack
     */
    public void updateUser(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().updateUser(params).enqueue(callBack);
    }
}
