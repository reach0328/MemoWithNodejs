package com.jpark.memowithnodejs.domain;

import java.util.List;

/**
 * Created by JH on 2017-03-24.
 */

public class DataStore {
    private static DataStore instatnce = null;
    private DataStore() {

    }

    public static DataStore getInstatnce() {
        if(instatnce == null){
            instatnce = new DataStore();
        }
        return instatnce;
    }
    private List<QnA> datas;

    public List<QnA> getDatas() {
        return datas;
    }

    public void setDatas(List<QnA> datas) {
        this.datas = datas;
    }
}
