package fzz.ssz.mytest.Base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Toolbar;

public abstract class BaseActivity extends AppCompatActivity {
    protected int ScreenHeight;
    protected int ScreenWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        getScreenMetrics();
        initView();
        initData();
        setEvent();
    }
    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void setEvent();

    public void getScreenMetrics(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
         ScreenHeight = dm.heightPixels;
         ScreenWidth = dm.widthPixels;
    }

}

