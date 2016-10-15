package com.yura;

public class CampaignConf implements Config{

    private String id;

    private String campaingId;
    private boolean autoOptimization;
    private int maxRedirects;
    private double precentage;

    public String getCampaingId() {
        return campaingId;
    }

    public void setCampaingId(String campaingId) {
        this.campaingId = campaingId;
    }

    public boolean isAutoOptimization() {
        return autoOptimization;
    }

    public void setAutoOptimization(boolean autoOptimization) {
        this.autoOptimization = autoOptimization;
    }

    public int getMaxRedirects() {
        return maxRedirects;
    }

    public void setMaxRedirects(int maxRedirects) {
        this.maxRedirects = maxRedirects;
    }

    public double getPrecentage() {
        return precentage;
    }

    public void setPrecentage(double precentage) {
        this.precentage = precentage;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
