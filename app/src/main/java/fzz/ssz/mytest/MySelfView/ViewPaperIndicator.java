package fzz.ssz.mytest.MySelfView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.List;

import fzz.ssz.mytest.R;

/**
 *
 * Created by 14206 on 2018/5/14.
 */

public class ViewPaperIndicator extends LinearLayout{

    private ViewPager mviewPager;
    List<String> tab_text_list;
    private int tabNum;
    private static final int COUNT_DEFAULT_TAB = 4;
    private int tab_nomal_color;
    private int tab_choose_color;
    private Paint myPaint;
    private String HEIGHT_LIGHT_TEXT="#28bb82";

    private int bottom_line_width;
    private int bottom_line_height;
    private Path mPath;
    private float offset;

    public ViewPaperIndicator(Context context) {
     this(context,null);
    }

    public ViewPaperIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPaperIndicator);

//        设置的数量，默认的数量
         tabNum = a.getInt(R.styleable.ViewPaperIndicator_vpi_item_count,COUNT_DEFAULT_TAB);
//         默认颜色
         tab_nomal_color = a.getColor(R.styleable.ViewPaperIndicator_never_choose_color, Color.GRAY);
//         设置的颜色
         tab_choose_color = a.getColor(R.styleable.ViewPaperIndicator_choose_color,Color.BLUE);

         if(tabNum<0){
             tabNum=COUNT_DEFAULT_TAB;
         }
          a.recycle();
//        初始化画笔
          initPaint();
    }

    private void initPaint() {
        myPaint = new Paint();
        myPaint.setAntiAlias(true);
        myPaint.setColor(Color.parseColor(HEIGHT_LIGHT_TEXT));
        myPaint.setStyle(Paint.Style.FILL);
        myPaint.setPathEffect(new CornerPathEffect(3));
        myPaint.setStrokeWidth(3);
    }


//    先于dispatchDraw
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.i("viewPaperIndicator_value", String.valueOf(w));//1080   xml中match_parent
        Log.i("viewPaperIndicator_value", String.valueOf(h));//100    xml中100px
        Log.i("viewPaperIndicator_value", String.valueOf(oldw));//0
        Log.i("viewPaperIndicator_value", String.valueOf(oldh));//0
        Log.i("viewPaperIndicator","onSizeChanged");

        bottom_line_width = w/4;      //   1080/4
        bottom_line_height = h/10;    //   100/4
        initLine();
    }
/**
 * 初始化线条
 * @author 14206
 * @time 2018/5/14 18:20
 */
    private void initLine() {
      mPath = new Path();
      mPath.moveTo(0,0);
      mPath.lineTo(0,-bottom_line_height);
      mPath.lineTo(bottom_line_width,-bottom_line_height);
      mPath.lineTo(bottom_line_width,0);
      mPath.close();
//      int
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {

        Log.i("viewPaperIndicator","dispatchDraw");
        canvas.save();
        //这两个参数
//        canvas.translate(mInitTranslationX + mTranslationX, getHeight() + 1);
        Log.i("getHeight_viewpaper", String.valueOf(getHeight()));
        canvas.translate(offset,getHeight());
        canvas.drawPath(mPath, myPaint);
        canvas.restore();

        super.dispatchDraw(canvas);
        Log.i("getWidth", String.valueOf(getWidth()));  //0 ?
    }

    private void set(int position,float offset1){
        offset = getWidth()/2*(position + offset1);
        Log.i("getWidth", String.valueOf(getWidth()));
        Log.i("nuu", String.valueOf(offset));

        int  tabWidth = getWidth()/tabNum;
        if(offset1>0&&position>=(tabNum-2)&&getChildCount()>tabNum){
            this.scrollTo(position-(tabNum-2)*tabWidth+(int)(tabWidth*offset1),0);
        }

//     下面这句话不写的话上面的scrollTo（）方法不启用，  废除之前的view，重新启用onDraw（）
        /**
         * Invalidate the whole view. If the view is visible,
         * {@link #onDraw(android.graphics.Canvas)} will be called at some point in
         * the future.
         * <p>
         * This must be called from a UI thread. To call from a non-UI thread, call
         * {@link #postInvalidate()}.
         */
        invalidate();
    }


    public void setViewPaper(ViewPager viewPaper){

        this.mviewPager = viewPaper;

           viewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                   set(position,positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



}
