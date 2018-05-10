package fzz.ssz.mytest;



import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import fzz.ssz.mytest.Base.BaseActivity;
import fzz.ssz.mytest.MyAdapter.MyRecycleViewAdapter;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private android.support.design.widget.AppBarLayout appbar1;
    private android.support.v7.widget.Toolbar toolBar;
    private android.widget.TextView tvMainTitle1;
    private android.widget.ImageView ivMain1;
    private android.widget.Button btHome1;
    private android.widget.Button btHome2;
    private android.widget.Button btHome3;
    private android.widget.Button btHome4;
    private android.support.v4.widget.SwipeRefreshLayout reflashMain1;
    private com.zhy.autolayout.AutoRelativeLayout autolinMain1;
    private com.zhy.autolayout.AutoRelativeLayout autolinMain2;
    private android.support.v7.widget.RecyclerView recMain1;
    private android.support.design.widget.NavigationView myLeft;
    private ImageView ivMain2;
    private ImageView ivMain3;
    private ImageView ivMain4;
    private TextView tvMainTitle2;

    private List<String> str = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private MyRecycleViewAdapter myRecycleViewAdapter;
    private Class c,nextac;
    private List<String> list_1=new ArrayList<>();
    private int i;

    @Override
    protected int getLayoutId() {
        Log.i("home_activity","onCreate");
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        appbar1 = (AppBarLayout) findViewById(R.id.appbar_1);
        toolBar = (Toolbar) findViewById(R.id.tool_bar);
        tvMainTitle1 = (TextView) findViewById(R.id.tv_main_title_1);
        ivMain1 = (ImageView) findViewById(R.id.iv_main_1);
        btHome1 = (Button) findViewById(R.id.bt_home_1);
        btHome2 = (Button) findViewById(R.id.bt_home_2);
        btHome3 = (Button) findViewById(R.id.bt_home_3);
        btHome4 = (Button) findViewById(R.id.bt_home_4);
        reflashMain1 = (SwipeRefreshLayout) findViewById(R.id.reflash_main_1);
        autolinMain1 = (AutoRelativeLayout) findViewById(R.id.autolin_main_1);
        autolinMain2 = (AutoRelativeLayout) findViewById(R.id.autolin_main_2);
        recMain1 = (RecyclerView) findViewById(R.id.rec_main_1);
        myLeft = (NavigationView) findViewById(R.id.my_left);
        ivMain2 = (ImageView) findViewById(R.id.iv_main_2);
        ivMain3 = (ImageView) findViewById(R.id.iv_main_3);
        ivMain4 = (ImageView) findViewById(R.id.iv_main_4);
        tvMainTitle2 = (TextView) findViewById(R.id.tv_main_title_2);
    }

    @Override
    protected void initData() {
        //初始化RecycleView
        //设置Manager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recMain1.setLayoutManager(manager);
        //初始化数据
        addTextViewName();
        //初始化适配器
        myRecycleViewAdapter = new MyRecycleViewAdapter(str,this,list);
        recMain1.setAdapter(myRecycleViewAdapter);
         initInfo();
    }


    @Override
    protected void setEvent() {
        btHome1.setOnClickListener(this);
        btHome4.setOnClickListener(this);
        reflashMain1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reflashMain1.setRefreshing(false);//必须设置，不然刷新圆圈一直转圈圈
            }
        });
    }

    /**
     * 添加recycle显示名和类名
     * @author 14206
     * @time 2018/5/10 15:11
     */
    private void addTextViewName() {
//        链式添加TextView名字
        setRecycleViewTextViewName(str,"JsonObject");
//        链式添加类名
        setRecycleViewName(list,"JsonObjectActivity");
    }

/**
 * 这里添加activity名字后，点击通用就可以启动相应的activity
 * @author 14206
 * @time 2018/5/10 15:57
 */
    private void initInfo() {
        setClassName("SmallDialogActivity");// 1
        setClassName("JsonObjectActivity");// 2

        //指定i就可以选择启动哪一个activity
        i= i-2;
    }

    /**
     * @author 14206
     * @time 2018/5/10 15:16
     * @Return 装有类名的集合
     */
    private void setClassName(String se){
        for(int i=0;i<list_1.size();i++){
            if (list_1.get(i).equals(se)){
                return ;
            }
        }
        se ="fzz.ssz.mytest."+se;
        list_1.add(se);
        i=list_1.size();
    }

    @Override
    public void onClick(View v) {
             switch (v.getId()){
                 case R.id.bt_home_1: //View
//                     ClassTest();//测试MainActivity.class是否为反射
                 break;
                 case R.id.bt_home_4: //通用
                     try {
                          nextac = Class.forName(list_1.get(i));
                     } catch (ClassNotFoundException e) {
                          tvMainTitle1.setText("类名错误");
                          e.printStackTrace();
                     }
                     Intent intent = new Intent(this,nextac);
                     startActivity(intent);
                     break;
             }
    }


/**
 * 添加Class类名
 * @author 14206
 * @time 2018/5/10 14:48
 */
   private List<String> setRecycleViewName(List<String> list,String str){
       str = "fzz.ssz.mytest."+str;
        list.add(str);
        return list;
   }

   /**
    * 添加Recycle显示名
    * @author 14206
    * @time 2018/5/10 14:52
    */
   private List<String> setRecycleViewTextViewName(List<String> list,String str){
       list.add(str);
       return list;
   }


   /**
    * 过期测试
    * Class.fromName（）必须写完整的相对路径名
    * @author 14206
    * @time 2018/5/10 14:56
    */
      private void ClassTest(){
//          Intent intent = new Intent(this,SmallDialogActivity.class);
          try {
              c  =   Class.forName("fzz.ssz.mytest.SmallDialogActivity");
          }catch (ClassNotFoundException E){
              E.printStackTrace();
          }
          Intent intent = new Intent(this,c);
          startActivity(intent);
      }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("home_activity","onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("home_activity","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("home_activity","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("home_activity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("home_activity","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("home_activity","onDestroy");
    }
}