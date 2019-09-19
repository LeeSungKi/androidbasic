package com.example.ex03_listview;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private ArrayList<ItemVo> list;
    private MainActivity mainActivity;


    MyAdapter(ArrayList<ItemVo> list, MainActivity mainActivity){ // 필수사항아님!
        this.list = list;
        this.mainActivity = mainActivity;
    }


    @Override
    public int getCount() { // 보여줄 아이템의 전체 개수
        return list.size();
    }

    @Override
    public Object getItem(int i) { //  i번째 아이템을 return
        return list.get(i);
    }

    @Override
    public long getItemId(int i) { // i번째 아이템의 id 를 return
        return i;
    }

    @Override // i번째 View를 return
    public View getView(int i, View view, ViewGroup viewGroup) {
        // view 매개변수 : 화면에 보여져야하는 View 객체
        //  ( Adapter 는 스크롤 할 때 마다 새롭게 View를 생성하지 않는다.
        //    대신, 화면에 보이지 않는 View를 대기시킨다.
        //    이때 getView()가 호출되면 대기중이었던 View가 인자로 들어온다.)


       /* Log.d("My",
                view == null ?
                        i + "번째 뷰 최초 생성" : "i:" + i + ",view.getId():"+view.getId());

        View v = View.inflate(mainActivity, R.layout.item, null);
                // inflate : xml에 지정되지 않았던 뷰를 동적으로 추가
        TextView titleView = v.findViewById(R.id.item_title);
        TextView contentView = v.findViewById(R.id.item_content);

        titleView.setText(list.get(i).getTitle());
        contentView.setText(list.get(i).getContent());

        return v;*/

        /*Log.d("My",
                view == null ?
                        i + "번째 뷰 최초 생성" : "i:" + i + ",view.getId():"+view.getId());
        if(view == null) {
            view = View.inflate(mainActivity, R.layout.item, null);
            // inflate : xml에 지정되지 않았던 뷰를 동적으로 추가
        }
        TextView titleView = view.findViewById(R.id.item_title);
        TextView contentView = view.findViewById(R.id.item_content);

        titleView.setText(list.get(i).getTitle());
        contentView.setText(list.get(i).getContent());


        return view;*/

        /*

            < View Holder Pattern >
            adapter 는 해당 ListView의 아이템이 스크롤에 의해 재등장 해야 하는 경우
            그때마다 매번 getView()를 실행
            무조건 inflate을 시켜버리면 함수가 호출될 때 마다 View 생성됨 ==> 메모리 효율 저하
            ==> ViewHolder Pattern 을 사용
         */
        MyHolder holder;
        if (view == null){
        holder = new MyHolder();

        view = View.inflate(mainActivity,R.layout.item,null);
        //==>외부의 xml을 참고하여 View 새로 생성 (inflate)
        //==>View.inflate( View를 붙일 액티비티, 생성할 레이아웃 xml, 부모 View )
        holder.title = view.findViewById(R.id.test01_title);//방금 생성한 item View의 title 뷰 객체를 얻어옴
        holder.content = view.findViewById(R.id.test01_content);//방금 생성한 item View의 content 뷰 객체를 얻어옴
            holder.imageView.setImageResource(list.get(i).getImage());
            view.setTag(holder);
    }
        else {
        holder = (MyHolder)view.getTag();
    }
        holder.title.setText(list.get(i).getTitle());
        holder.content.setText(list.get(i).getContent());
        holder.imageView.setImageResource(list.get(i).getImage());

        return view;
}
class MyHolder{
    TextView title;
    TextView content;
    ImageView imageView;
}
}













