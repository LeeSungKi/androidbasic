package com.example.myapplication;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<NewsData> mDataset;
    private static View.OnClickListener onClickListener;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    //한줄에 들어가는 요소를 정해준다
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // 붙이 이미지의 textview 또는 정의해줘야함
        public TextView TextView_title;
        public TextView TextView_content;
        public SimpleDraweeView ImageView_title;
                        //받는부분을 view로 바꿔줌
        public  View rootView;
        public MyViewHolder(View v) {
            super(v);
            //정의한거 연결해줘
            //xml에서 어떤요소를 찾아갈땐 부모클래스에서 찾아야함 여기서는 view가부모클래스이다
            //그래서 보통액티비티에서 findViewById가아닌 v.findViewById 이렇게 해줘야함
            //왜 여기에서 따로 자식들 관리해줌???
            TextView_title =  v.findViewById(R.id.TextView_title);
            TextView_content =  v.findViewById(R.id.TextView_content);
            ImageView_title =  v.findViewById(R.id.ImageView_title);
            rootView = v;
           //레이아웃누를수있게해주기
           /* v.setClickable(true);
            v.setEnabled(true);
            v.setOnClickListener(onClickListener);*/
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    //글자들 값을 넣어준다
    public MyAdapter(List<NewsData>  myDataset , Context context, View.OnClickListener onClick) {
        mDataset = myDataset;
        onClickListener = onClick;
        Fresco.initialize(context);
    }

    // Create new views (invoked by the layout manager)
    //이부분이 한view에 이미지를 연결해줌
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        //홀더에서 부모뷰를정해줌 v로
        // 넣어줄 이미지 최상위가 LinearLayout 이므로 textview 를바꿔줘야함
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                            //list에넣어줄xml파일이름
                .inflate(R.layout.my_text_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    //바인딩 반복한거 끼워넣기
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        NewsData news = mDataset.get(position);

        holder.TextView_title.setText(news.getTitle());

        String content = news.getDescription();
        if(content != null && content.length() > 0){
            holder.TextView_content.setText(content);
        }else {
            holder.TextView_content.setText("-");
        }



        Uri uri = Uri.parse(news.getUrlToImage());

        holder.ImageView_title.setImageURI(uri);

        //tag
        holder.rootView.setTag(position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    //길이만큼반복
    @Override
    public int getItemCount() { return mDataset == null ? 0 : mDataset.size(); }

    public NewsData getNews(int position){
        return mDataset != null ? mDataset.get(position) : null;
    }

}