package fzz.ssz.mytest;




import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fzz.ssz.mytest.Base.BaseActivity;
import fzz.ssz.mytest.MySelfView.ViewPaperIndicator;
import fzz.ssz.mytest.fragment.FragmentViewpaperIndicatitor;


public class ViewPaperActivity extends BaseActivity {

    private FragmentPagerAdapter adapter;
    private List<Fragment> myfragment = new ArrayList<>();
    private FragmentViewpaperIndicatitor fragment1,fragment2,fragment3,fragment4;
    private fzz.ssz.mytest.MySelfView.ViewPaperIndicator viewpaperIndi;
    private ViewPager viewpaper1;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_paper;
    }

    @Override
    protected void initView() {
        viewpaperIndi = (ViewPaperIndicator) findViewById(R.id.viewpaper_indi);
        viewpaper1 = (ViewPager) findViewById(R.id.viewpaper_1);
    }

    @Override
    protected void initData() {
        //这里新建了同一个Fragment的多个对象
        fragment1 = new FragmentViewpaperIndicatitor();
        fragment2 = new FragmentViewpaperIndicatitor();
        fragment3 = new FragmentViewpaperIndicatitor();
        fragment4 = new FragmentViewpaperIndicatitor();
        myfragment.add(fragment1);
        myfragment.add(fragment2);
        myfragment.add(fragment3);
        myfragment.add(fragment4);
        viewpaperIndi.setViewPaper(viewpaper1);


        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return myfragment.get(position);
            }

            @Override
            public int getCount() {
                return myfragment.size();
            }
        };
      viewpaper1.setAdapter(adapter);
    }

    @Override
    protected void setEvent() {
    //    我在这里设置了同一个对象和setViewPaper（）函数里相同的监听事件，然后函数里的不起作用，猜想是优先考虑本类的

//      viewpaper1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//          @Override
//          public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//              //参数说明：position：第几个fragment
//              //偏移量   从0 到 1 ；
//              //偏移量   以屏幕像素宽度为单位，这里max： 1080
//              Log.i("viewpaperactivity","PageScrolled "+  String.valueOf(position));
//              Log.i("viewpaperactivity", "PageScrolled "+  String.valueOf(positionOffset));
//              Log.i("viewpaperactivity","PageScrolled "+  String.valueOf(positionOffsetPixels));
//              Log.i("viewpaperactivity","---------------------------------------------");
//          }
//
//          @Override
//          public void onPageSelected(int position) {
////              中间的时候会变成下一个framgment的编号
//             Log.i("viewpaperactivity", "onPageSelected "+  String.valueOf(position));
//          }
//
//          @Override
//          public void onPageScrollStateChanged(int state) {
//              // 这里是fragment的状态 1 --> 0   //中间的时候会变成2
//               Log.i("viewpaperactivity","onPageScrollStateChanged "+  state);
//          }
//      });
    }



}
