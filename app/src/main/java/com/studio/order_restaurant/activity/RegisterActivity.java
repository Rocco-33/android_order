package com.studio.order_restaurant.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.studio.order_restaurant.R;

public class RegisterActivity extends AppCompatActivity {

    EditText username,password,password2;
    private ImageView ivBack;
    private RelativeLayout rlTitleBar;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);
        rlTitleBar = findViewById(R.id.rl_title_bar);
        tvTitle = findViewById(R.id.tv_title);
        ivBack = findViewById(R.id.iv_back);

        tvTitle.setText("注册");
        rlTitleBar.setBackgroundColor(getResources().getColor(R.color.blue_color));
        ivBack.setVisibility(View.INVISIBLE);
    }

    public void btn_click(View view){
        String name = username.getText().toString();
        String pwd = password.getText().toString();
        String pwd2 = password2.getText().toString();
        switch (view.getId()){
            case R.id.register:
                if(TextUtils.isEmpty(name)){
                    username.setError("用户名不能为空");
                }else if(TextUtils.isEmpty(pwd)){
                    password.setError("密码不能为空");
                }else if(TextUtils.isEmpty(pwd2)){
                    password2.setError("密码不能为空");
                }else if(!pwd2.equals(pwd)){
                    password2.setError("两次密码不一致");
                    password2.setText("");
                }else{
                    SharedPreferences sp = getSharedPreferences("userInfo",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("name",name);
                    editor.putString("pwd",pwd);
                    editor.commit();
                }

            case R.id.cancel:
                finish();
                break;
        }
    }
}
