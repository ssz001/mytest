package fzz.ssz.mytest;


import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import fzz.ssz.mytest.Base.BaseActivity;
import fzz.ssz.mytest.Bean.TextBean;

public class JsonObjectActivity extends BaseActivity {

    private TextView tvJson1;
    private String str = "{\"data\":200,\"company\":{\"id\":100,\"name\":\"alibaba\",\"img\":\"gggh\",\"addr\":{\"is\":\"3434\",\"is2\":\"4545\",\"is3\":{\"it\":\"for\"}}}}";
    private String str2 = "{\"data\":200,\"company\":[\"id\":\"100\",\"name\":\"alibaba\",\"img\":\"gggh\",\"addr\":[\"is\":\"3434\",\"is2\":\"4545\",\"is3\":{\"it\":\"for\"}]]}";
    private String str3 = "{\"data\":200,\"company\":[{\"id\":\"we1\"}{\"id2\":\"we2\"}{\"id3\":\"we3\"}]";
    private String str4 = "{\"data\":200,\"company\":[{\"id\":\"we1\",\"id2\":\"we2\",\"id3\":\"we3\"}]}";  //格式：[{},{},{}]
    private String str5 = "{\"data\":200,\"company\":[{\"id\":\"s1\"},{\"id2\":\"s2\"},{\"id3\":\"s3\"},{\"id4\":\"字符测试\"}]}";//注意里面是键值对形式
    private String str6 = "{\"data\":200,\"company\":[{\"id\":\"s1\"},{\"id2\":\"s2\"},{\"id3\":\"s3\"},{\"id4\":\"s4\"}]}";//注意里面是键值对形式
    private String str7 = "{\"data\":[{\"id\":1,\"name\":\"张三\"},{\"id\":2,\"name\":\"李四\"},{\"id\":3,\"name\":\"王五\"},{\"id\":4,\"name\":\"赵六\"}]}";
    private TextView tvJson2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jsonobject;
    }

    @Override
    protected void setEvent() {

    }

    @Override
    protected void initData() {
        JSONObject json = null;
        try {
            json = new JSONObject(str7);
        } catch (JSONException e) {
            e.printStackTrace();
            tvJson2.setText("解析错误");
        }
        try {
//            错误
//            String strtest = json.getJSONObject("is3").getString("it");

//            正确
//            String strtest = json.getJSONObject("company").getJSONObject("addr").getJSONObject("is3").getString("it");
//            tvJson1.setText(strtest);

//              String strtest = json.getJSONArray("company").getString(3);
//              tvJson1.setText(strtest);
//               String str = json.getString("data");
//               json.getJSONObject("data").getString("data");  //返回的是一个JSONObject对象。
//               json.getString("data");//返回的是一个String对象

//              if(json.getJSONObject("company").has("name")){tvJson1.setText("true");} //true

//          数组的情况
            String strr  =json.getString("data");
            Type listType = new TypeToken<ArrayList<TextBean>>(){}.getType();//对象。getType

            ArrayList<TextBean> textBeans = new Gson().fromJson(strr,listType);

            int a = 1;
            tvJson1.setText(textBeans.get(a).getId());
            tvJson2.setText(textBeans.get(a).getName());

            //这种方法不行，会出错，猜想是键值对的原因，如果是是数组。用Type。。。
//               String set = json.getString("data");
//               TextBean textBean =new Gson().fromJson(set,TextBean.class);
//               tvJson1.setText(textBean.getList().get(1).getName());
        } catch (JSONException e) {
            e.printStackTrace();
            tvJson1.setText("获取错误");
        }
    }

    @Override
    protected void initView() {
        tvJson1 = (TextView) findViewById(R.id.tv_json_1);
        tvJson2 = (TextView) findViewById(R.id.tv_json2);
    }
}
