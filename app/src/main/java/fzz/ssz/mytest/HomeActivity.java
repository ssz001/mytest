package fzz.ssz.mytest;



import android.annotation.SuppressLint;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import fzz.ssz.mytest.Base.BaseActivity;
import fzz.ssz.mytest.MyAdapter.MyRecycleViewAdapter;
import fzz.ssz.mytest.RecycleViewBase.RecycleViewDivider;
import fzz.ssz.mytest.Util.ShowUtil;

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
    private ImageView ivMenu1;
    private android.support.v4.widget.DrawerLayout drawer;
    private View v2;
    private android.widget.EditText etExample;
    private View v3;
    private TextView tvListen;

    @Override
    protected int getLayoutId() {
        Log.i("home_activity","onCreate");
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        appbar1 = (AppBarLayout) findViewById(R.id.appbar_1);
        toolBar = (Toolbar) findViewById(R.id.tool_bar);
//        tvMainTitle1 = (TextView) findViewById(R.id.tv_main_title_1);
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
        ivMenu1 = (ImageView) findViewById(R.id.iv_menu_1);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        v2 = (View) findViewById(R.id.v_2);
        etExample = (EditText) findViewById(R.id.et_example);
        v3 = (View) findViewById(R.id.v_3);
        tvListen = (TextView) findViewById(R.id.tv_listen);
    }


    @Override
    protected void initData() {
//        这个不能加，加了会出现MyTest，猜想是这是为了让ToolBar和ActionBar相同，而ActionBar有标题，并且在在清单中用label标签无效
//        Toolbar toolbar = (Toolbar)findViewById(R.id.tool_bar);
//        setSupportActionBar(toolbar);
        //设置菜单按钮
//        ActionBar actionBar = getSupportActionBar();
//        if(actionBar!=null){
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.mipmap.title1);
//        }
        //初始化RecycleView
        //设置Manager
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recMain1.setLayoutManager(manager);
        recMain1.addItemDecoration(new RecycleViewDivider(this,LinearLayoutManager.HORIZONTAL));//ver--hor
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
        ivMenu1.setOnClickListener(this);
        reflashMain1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reflashMain1.setRefreshing(false);//必须设置，不然刷新圆圈一直转圈圈
            }
        });

        etExample.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //xml中设置了singleline会导致监听失败,应该设置maxLine，然后这里要返回true，应该是点击了就不是输入文字动作，是点击事件
                if (actionId== EditorInfo.IME_ACTION_UNSPECIFIED||actionId==EditorInfo.IME_ACTION_NEXT){
                    if(event.getAction()==KeyEvent.ACTION_DOWN){
                        tvListen.setText(etExample.getText().toString());
                    }
                    return true;
                }
                return false;
            }
        });
    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId()){
//            case android.R.id.home:
//                drawer.openDrawer(GravityCompat.START);
//                break;
//        }
//        return true;
//    }

    /**
     * 添加recycle显示名和类名
     * @author 14206
     * @time 2018/5/10 15:11
     */
    private void addTextViewName() {
//        链式添加TextView名字
        setRecycleViewTextViewName("JsonObject").setRecycleViewTextViewName("类DialogActivity").setRecycleViewTextViewName("ViewPaper");
//        链式添加类名
        setRecycleViewName("JsonObjectActivity").setRecycleViewName("SmallDialogActivity").setRecycleViewName("ViewPaperActivity");
    }

   /**
     *这里添加activity名字后，点击通用就可以启动相应的activity
     * @author 14206
     * @time 2018/5/10 15:57
     */
    private void initInfo() {
        setClassName("SmallDialogActivity");// 1
        setClassName("JsonObjectActivity");// 2
        //指定i就可以选择启动哪一个activity
        i= i-2;//可以直接写i==？
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
//                     try {
//                          nextac = Class.forName(list_1.get(i));
//                     } catch (ClassNotFoundException e) {
//                          tvMainTitle1.setText("类名错误");
//                          e.printStackTrace();
//                     }
//                     Intent intent = new Intent(this,nextac);
//                     startActivity(intent);
                     tvListen.setText(String.format("CNY %1$s",1112222222));
                     break;
                 case R.id.iv_menu_1:
                     drawer.openDrawer(GravityCompat.START);
             }
    }


   /**
     * 添加Class类名
     * @author 14206
     * @time 2018/5/10 14:48
     */
   private HomeActivity setRecycleViewName(String str){
        list.add("fzz.ssz.mytest."+str);
        return this;
   }

   /**
    * 添加Recycle显示名
    * @author 14206
    * @time 2018/5/10 14:52
    */
   private HomeActivity setRecycleViewTextViewName(String str1){
       str.add(str1);
       return this;
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