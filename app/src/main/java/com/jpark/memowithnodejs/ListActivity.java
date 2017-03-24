package com.jpark.memowithnodejs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jpark.memowithnodejs.domain.DataStore;
import com.jpark.memowithnodejs.domain.QnA;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    CustomAdapter adapter;
    List<QnA> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, WriteActivity.class);
                startActivity(intent);
            }
        });

        setList();
    }

    private void setList() {
        listView = (ListView) findViewById(R.id.list);
        DataStore datastore = DataStore.getInstatnce();
        datas = datastore.getDatas();
        adapter = new CustomAdapter(this,datas);
        listView.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    class CustomAdapter extends BaseAdapter{
        List<QnA> datas;
        LayoutInflater inflater;
        Context context;
        public CustomAdapter(Context context, List<QnA> datas) {
            this.context = context;
            this.datas = datas;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = inflater.inflate(R.layout.item,null);
            }
            QnA qna = datas.get(position);
            TextView textTitle = (TextView) convertView.findViewById(R.id.textView);
            TextView textName = (TextView) convertView.findViewById(R.id.textView2);

            textTitle.setText(qna.getTitle());
            textName.setText(qna.getName());

            return convertView;
        }
    }
}
