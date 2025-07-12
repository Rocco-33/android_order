package com.studio.order_restaurant.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.studio.order_restaurant.R;
import com.studio.order_restaurant.adapter.ShopAdapter;
import com.studio.order_restaurant.bean.ShopBean;
import com.studio.order_restaurant.utills.Constants;
import com.studio.order_restaurant.utills.JsonParse;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rlTitleBar;
    private TextView tvTitle;
    private ImageView ivBack;
    private ListView lvshopList;
    private ShopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initView();
        initData();
    }

    private void initView(){
        rlTitleBar = findViewById(R.id.rl_title_bar);
        tvTitle = findViewById(R.id.tv_title);
        lvshopList = findViewById(R.id.lv_shop_list);
        ivBack = findViewById(R.id.iv_back);

        adapter = new ShopAdapter(this);
        lvshopList.setAdapter(adapter);
        tvTitle.setText("店铺");
        rlTitleBar.setBackgroundColor(getResources().getColor(R.color.blue_color));

        ivBack.setOnClickListener(this);
    }


    private void initData() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(Constants.WEB_SITE + Constants.REQUEST_SHOP_DATA).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                final List<ShopBean> list = JsonParse.getInstance().getShopList(json);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setData(list);
                    }
                });
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();break;
        }
    }
}
