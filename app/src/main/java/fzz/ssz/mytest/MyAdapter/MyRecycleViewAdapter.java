package fzz.ssz.mytest.MyAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fzz.ssz.mytest.R;

/**
 * Created by 14206 on 2018/5/10.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {

    private List<String> textView_name;
    private Activity activity;
    private List<String> activity_name;
    private  Class c;



    public MyRecycleViewAdapter(List<String> textViews, Activity activity, List<String> activity_name){
        this.textView_name = textViews;
        this.activity = activity;
        this.activity_name = activity_name;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private  TextView t1;
        private View itemParentView;

        //item是子项最外层布局view
        public ViewHolder(View itemView) {
            super(itemView);
            itemParentView = itemView;
            t1 = (TextView)itemView.findViewById(R.id.item_rec_1);
        }
    }


    @Override
    public int getItemCount() {
        return textView_name.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_main_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);


        holder.itemParentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();

                try {
                    Log.i("name_position", activity_name.get(position));
                   c = Class.forName(activity_name.get(position));

                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
                Intent intent = new Intent(activity,c);
                activity.startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String str =textView_name.get(position);
        holder.t1.setText(str);
    }

}
