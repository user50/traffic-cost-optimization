package com.yura.zeropark.model;

public class TargetStats {

    private double spent;
    private double payout;
    private int redirects;
    private double conversions;

    public double getSpent() {
        return spent;
    }

    public void setSpent(double spent) {
        this.spent = spent;
    }

    public double getPayout() {
        return payout;
    }

    public void setPayout(double payout) {
        this.payout = payout;
    }

    public int getRedirects() {
        return redirects;
    }

    public void setRedirects(int redirects) {
        this.redirects = redirects;
    }

    public double getConversions() {
        return conversions;
    }

    public void setConversions(int conversions) {
        this.conversions = conversions;
    }
}
