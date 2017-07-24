package com.halove.latte.ui.recycler;

import java.util.List;

/**
 * Created by xieshangwu on 2017/7/24
 */

public class Bean {

    private int code;
    private String message;
    private int total;
    private int page_size;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private int goodsId;
        private int spanSize;
        private String text;
        private String imageUrl;
        private List<String> banners;

        public int getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(int goodsId) {
            this.goodsId = goodsId;
        }

        public int getSpanSize() {
            return spanSize;
        }

        public void setSpanSize(int spanSize) {
            this.spanSize = spanSize;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public List<String> getBanners() {
            return banners;
        }

        public void setBanners(List<String> banners) {
            this.banners = banners;
        }
    }
}
