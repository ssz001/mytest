package fzz.ssz.mytest;

import android.util.Log;

import fzz.ssz.mytest.Base.BaseActivity;


/**
 * 这里测试了吧acticity作为dialog，刚开始没有去清单里修改style，程序和一个普通的activity一样，受影响的homeactivity的生命周期执行了onStop方法，
 * 后来去修改了清单里的style指向（style里新建了smallactivity），发现onstop方法没有执行了
 * @author 14206
 * @time 2018/5/10 17:33
 */

public class SmallDialogActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        Log.i("home_activity","onCreate_s");
        return R.layout.activity_small_dialog;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setEvent() {

    }



    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("home_activity","onRestart_s");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("home_activity","onStart_s");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("home_activity","onResume_s");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("home_activity","onPause_s");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("home_activity","onStop_s");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("home_activity","onDestroy_s");
    }
}
