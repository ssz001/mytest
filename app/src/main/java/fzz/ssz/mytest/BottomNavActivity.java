package fzz.ssz.mytest;



import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;

import fzz.ssz.mytest.Base.BaseActivity;
import fzz.ssz.mytest.fragment.Fragment1;
import fzz.ssz.mytest.fragment.Fragment2;
import fzz.ssz.mytest.fragment.Fragment3;
import fzz.ssz.mytest.fragment.Fragment4;

public class BottomNavActivity extends BaseActivity {


    private BottomNavigationBar myHome;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private ArrayList<Drawable> icon = new ArrayList<>();

    public BottomNavActivity() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.content_bottom_nav;
    }

    @Override
    protected void initView() {
         initNavData();
          myHome = (BottomNavigationBar) findViewById(R.id.my_home);
//          myHome.addItem(new BottomNavigationItem());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setEvent() {

    }

    private void initNavData() {
    }

}
