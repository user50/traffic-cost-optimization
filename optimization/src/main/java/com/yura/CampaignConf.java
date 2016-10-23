package com.yura;

import com.yura.repository.Config;

public class CampaignConf implements Config {

    private String id;

    private String campaignId;
    private String campaignName;
    private boolean autoOptimization;
    private int maxRedirects;
    private double percentage;
    private String testCampaignId;
    private String testCampaignName;
    private int maxTestRedirect;

    public CampaignConf() {
    }

    public CampaignConf(String campaignId, String campaignName, boolean autoOptimization, int maxRedirects,
                        double percentage, String testCampaignId, String testCampaignName, int maxTestRedirect) {
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.autoOptimization = autoOptimization;
        this.maxRedirects = maxRedirects;
        this.percentage = percentage;
        this.testCampaignId = testCampaignId;
        this.testCampaignName = testCampaignName;
        this.maxTestRedirect = maxTestRedirect;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
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

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getTestCampaignId() {
        return testCampaignId;
    }

    public void setTestCampaignId(String testCampaignId) {
        this.testCampaignId = testCampaignId;
    }

    public int getMaxTestRedirect() {
        return maxTestRedirect;
    }

    public void setMaxTestRedirect(int maxTestRedirect) {
        this.maxTestRedirect = maxTestRedirect;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getTestCampaignName() {
        return testCampaignName;
    }

    public void setTestCampaignName(String testCampaignName) {
        this.testCampaignName = testCampaignName;
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
