package com.studio.order_restaurant.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.studio.order_restaurant.R;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    private ImageView ivBack;
    private RelativeLayout rlTitleBar;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ininView();
    }

    private void ininView() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        rlTitleBar = findViewById(R.id.rl_title_bar);
        tvTitle = findViewById(R.id.tv_title);
        ivBack = findViewById(R.id.iv_back);

        tvTitle.setText("登入");
        rlTitleBar.setBackgroundColor(getResources().getColor(R.color.blue_color));
        ivBack.setVisibility(View.INVISIBLE);
    }

    public void click(View view) {
        String name = username.getText().toString();
        String pwd = password.getText().toString();
        switch (view.getId()) {
            case R.id.login:
                //登录成功，跳转Main界面
                if(name.isEmpty()){
                    username.setError("用户名不能为空！");
                }
                else if(pwd.isEmpty()){
                    password.setError("密码不能为空！");
                }else{
                    SharedPreferences sp = getSharedPreferences("userInfo",MODE_PRIVATE);
                    String n = sp.getString("name","");
                    String p = sp.getString("pwd","");
                    if(!name.equals(n)){
                        username.setError("用户名错误！");
                    }else if(!pwd.equals(p)){
                        password.setError("密码错误！");
                    }else if(name.equals(n) && pwd.equals(p)){
                        Intent intent = new Intent(this,ShopActivity.class);
                        startActivity(intent);
                    }
                }
                break;

            case R.id.cancel:
                //取消，退出APP
                finish();
                break;
        }
    }

    public void tv_register(View view){
        Intent intent1 = new Intent(this,RegisterActivity.class);
        startActivityForResult(intent1,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            if (requestCode == 1){
                if(resultCode == 1){
                    String name = data.getStringExtra("username");
                    String pwd = data.getStringExtra("password");
                    username.setText(name);
                    password.setText(pwd);
                }
            }
        }
    }

}
