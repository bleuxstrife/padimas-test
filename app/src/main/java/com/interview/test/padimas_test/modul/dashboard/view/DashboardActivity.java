package com.interview.test.padimas_test.modul.dashboard.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.interview.test.padimas_test.R;
import com.interview.test.padimas_test.modul.dashboard.adapter.CarListAdapter;
import com.interview.test.padimas_test.modul.dashboard.controller.HomeController;
import com.interview.test.padimas_test.modul.dashboard.model.CarListModel;
import com.interview.test.padimas_test.modul.login.view.LoginActivity;
import com.interview.test.padimas_test.utils.BaseObject;
import com.interview.test.padimas_test.utils.Constant;
import com.interview.test.padimas_test.utils.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

public class DashboardActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.title_toolbar)
    TextView titleToolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.noDataTV)
    TextView noDataTV;
    @BindView(R.id.error_view)
    LinearLayout errorView;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.nameTV)
    TextView nameTV;


    HomeController homeController;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        titleToolbar.setText(getString(R.string.app_name));
        swipeRefresh.setOnRefreshListener(this);
        homeController = new HomeController(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        String fullName = getString(R.string.welcome)+", "+homeController.getProfile().getFirstName();
        nameTV.setText(fullName);
        onRefresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                new MaterialDialog.Builder(this)
                        .title(R.string.logout)
                        .content(getString(R.string.logout_content))
                        .positiveColor(ContextCompat.getColor(this, android.R.color.black))
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                homeController.logout();
                                Intent I = new Intent(getContext(), LoginActivity.class);
                                I.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(I);
                            }
                        })
                        .positiveText(getString(R.string.ok))
                        .negativeColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
                        .negativeText(getString(R.string.cancel))
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        }).show();

                break;
            default:
                return false;
        }
        return super.onOptionsItemSelected(item);

    }


    @Subscribe
    public void onErrorEvent(Throwable t) {
        swipeRefresh.setRefreshing(false);
        empty();
        showToast(t.getMessage());
        Log.e("onErrorEvent: ", t.getMessage());
    }

    @Subscribe
    public void onEvent(BaseObject object) {
        swipeRefresh.setRefreshing(false);
        if (object.getCode() == Constant.GET_SPECIAL_CAR) {
            List<CarListModel> modelList = (List<CarListModel>) object.getData();
            if (modelList.size() > 0) {
                available();
                CarListAdapter carListAdapter = new CarListAdapter(this, modelList);
                recyclerView.setAdapter(carListAdapter);
            } else {
                empty();
            }
        }
    }

    private void available() {
        recyclerView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    private void empty() {
        recyclerView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {
        swipeRefresh.setRefreshing(true);
        homeController.getCar();
    }
}
