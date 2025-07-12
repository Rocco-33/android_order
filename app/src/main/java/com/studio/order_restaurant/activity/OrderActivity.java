package com.studio.order_restaurant.activity;

import android.app.Dialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.List;

import com.studio.order_restaurant.R;
import com.studio.order_restaurant.adapter.OrderAdapter;
import com.studio.order_restaurant.bean.FoodBean;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lvOrder;
    private OrderAdapter adapter;
    private List<FoodBean> carFoodList;
    private ImageView ivBack;
    private TextView tvTitle, tvDistributionCost,tvTotalCost,
            tvCost,tvPayment;
    private RelativeLayout rlTitleBar;
    private BigDecimal money,distributionCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //获取购物车中的数据
        carFoodList = (List<FoodBean>) getIntent().getSerializableExtra("carFoodList");
        //获取购物车中菜的总价格
        money = new BigDecimal(getIntent().getStringExtra("totalMoney"));
        //获取店铺的配送费
        distributionCost = new BigDecimal(getIntent().getStringExtra(
                "distributionCost"));
        initView();
        setData();
    }

    /**
     * 初始化界面控件
     */
    private void initView(){
        tvTitle = findViewById(R.id.tv_title);
        rlTitleBar = findViewById(R.id.rl_title_bar);
        ivBack = findViewById(R.id.iv_back);
        lvOrder= findViewById(R.id.lv_order);
        tvDistributionCost = findViewById(R.id.tv_distribution_cost);
        tvTotalCost = findViewById(R.id.tv_total_cost);
        tvCost = findViewById(R.id.tv_cost);
        tvPayment = findViewById(R.id.tv_payment);

        tvTitle.setText("订单");
        rlTitleBar.setBackgroundColor(getResources().getColor(R.color.blue_color));

        ivBack.setOnClickListener(this);
        tvPayment.setOnClickListener(this);
    }

    /**
     * 设置界面数据
     */
    private void setData() {
        adapter=new OrderAdapter(this);
        lvOrder.setAdapter(adapter);
        adapter.setData(carFoodList);
        tvCost.setText("￥"+ money);
        tvDistributionCost.setText("￥"+distributionCost);
        tvTotalCost.setText("￥"+(money.add(distributionCost)));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();break;
            case R.id.tv_payment:
                Dialog dialog = new Dialog(OrderActivity.this, R.style.Dialog_Style);
                dialog.setContentView(R.layout.qr_code);
                dialog.show();break;
        }
    }
}
