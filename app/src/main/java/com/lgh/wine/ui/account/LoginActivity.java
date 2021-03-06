package com.lgh.wine.ui.account;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lgh.wine.MainActivity;
import com.lgh.wine.contract.AccountContract;
import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.Account;
import com.lgh.wine.beans.LoginInput;
import com.lgh.wine.model.AccountModel;
import com.lgh.wine.presenter.AccountPresenter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.ClearEditText;
import com.lgh.wine.utils.MD5Helper;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnClick;
import vn.luongvo.widget.iosswitchview.SwitchView;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements AccountContract.View {
    @BindView(R.id.et_phone)
    ClearEditText et_phone;
    @BindView(R.id.et_password)
    ClearEditText et_password;
    @BindView(R.id.switch_view)
    SwitchView switch_view;
    @BindView(R.id.btn_login)
    Button btn_login;

    private boolean isPhoneNull = true;
    private boolean isPwdNull = true;

    private AccountPresenter presenter;
    private String pwd;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    protected void initUI() {
        Account account = AccountUtil.getAccount();
        if (account != null) {
            et_phone.setText(account.getUserPhone());
            et_password.setText(account.getUserPassword());
            setBtnEnable(true);
        }
        switch_view.setOnCheckedChangeListener(new SwitchView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchView switchView, boolean b) {
                et_password.setTransformationMethod(b ? HideReturnsTransformationMethod.getInstance() :
                        PasswordTransformationMethod.getInstance()
                );
            }
        });

        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editable.toString().trim();
                if (TextUtils.isEmpty(s)) {
                    isPhoneNull = true;
                    setBtnEnable(false);
                } else {
                    isPhoneNull = false;
                    if (!isPwdNull)
                        setBtnEnable(true);
                }
            }
        });
        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editable.toString().trim();
                if (TextUtils.isEmpty(s)) {
                    isPwdNull = true;
                    setBtnEnable(false);
                } else {
                    isPwdNull = false;
                    if (!isPhoneNull)
                        setBtnEnable(true);
                }
            }
        });
    }

    private void setBtnEnable(boolean b) {
        btn_login.setEnabled(b);
        btn_login.setBackgroundColor(b ? getResources().getColor(R.color.color_red) :
                getResources().getColor(R.color.color_grey_e5e5e5));
    }

    @Override
    protected void initData() {
        presenter = new AccountPresenter(this, AccountModel.newInstance());
        addPresenter(presenter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tvTitle = toolbar.findViewById(R.id.toolbar_title);
        TextView tvOther = toolbar.findViewById(R.id.toolbar_other);
        tvOther.setTextColor(getResources().getColor(android.R.color.black));

        tvOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, RegisterActivity.class));
            }
        });
        tvOther.setText("注册");
        tvTitle.setText("账号登录");
    }

    @OnClick({R.id.btn_login, R.id.tv_quick_login})
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_quick_login:
                startActivity(new Intent(mContext, LoginBySmsCodeActivity.class));
                break;
        }
    }

    private void login() {
        String phone = et_phone.getText().toString();
        pwd = et_password.getText().toString();

        presenter.login(phone, MD5Helper.getMd5Value(pwd), 1);
    }

    @Override
    public void showLoginInfo(Account data) {
        if (data != null) {
            showError("登录成功");

            if (AccountUtil.hasAccount()) {
                Account.deleteAll(Account.class);
            }
            data.setUserPassword(pwd);
            data.save();
            startActivity(new Intent(mContext, MainActivity.class));
            finish();
        }
    }

    @Override
    public void showSmsCode(String code) {

    }

    @Override
    public void verifyCodeSuccess() {

    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void dealAddFeedbackResult() {

    }

    @Override
    public void dealUpdateUserResult() {

    }

}
