package com.halove.latte.ui.recycler;

import java.util.ArrayList;

/**
 * Created by xieshangwu on 2017/7/24
 */

public abstract class DataConverter {

    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();

    private String mJsonData;

    public abstract ArrayList<MultipleItemEntity> convert();

    public DataConverter setJsonData(String json) {

        this.mJsonData = json;
        return this;
    }

    protected String getJsonData() {
        if(mJsonData == null || mJsonData.isEmpty()) {
            throw new NullPointerException("data is null");
        }
        return mJsonData;
    }
}
