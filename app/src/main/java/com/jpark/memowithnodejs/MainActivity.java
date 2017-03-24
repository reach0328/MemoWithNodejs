package com.jpark.memowithnodejs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jpark.memowithnodejs.domain.Data;
import com.jpark.memowithnodejs.domain.DataStore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static  final String SITE_URL = "http://192.168.0.251:8080/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
    }

    public void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SITE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 2. 사용할 인터페이스를 설정한다
        LocalhostInterface service = retrofit.create(LocalhostInterface.class);

        // 3. 데이터를 가져온다
        Call<Data> result = service.getData();

        // 4. 데이터 요청 비동기로 처리후 CallBack 호출
        result.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                // 값이 정상적으로 리턴됬을 경우
                if(response.isSuccessful()){
                    Data data = response.body(); // 원래 반환값인 jsonString 이 Data 클래스로 변환되어 리턴된다.
                    Log.i("data", " data = " +response.body().getDatas());
                    // 데이터 저장소에 원격서버에서 가져온 데이터를 저장해둔다.
                    DataStore dataStore = DataStore.getInstatnce();
                    dataStore.setDatas(data.getDatas());
                    // 데이터를 가져온 후에 목록 Activity 를 호출한다
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    startActivity(intent);
                    // 로딩화면인 현재 Activity는 종료
                    finish();
                }else{
                    Log.e("Retrofit", response.message()); // 정상적이지 않을경우 message에 오류내용이 담겨 온다
                }
            }


            @Override
            public void onFailure(Call<Data> call, Throwable t) {
                Log.e("Retrofit", t.getMessage());
            }
        });
    }
}
