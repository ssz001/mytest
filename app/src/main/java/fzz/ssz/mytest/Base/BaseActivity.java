package fzz.ssz.mytest.Base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toolbar;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
        setEvent();
    }
    protected abstract int getLayoutId();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void setEvent();


}

