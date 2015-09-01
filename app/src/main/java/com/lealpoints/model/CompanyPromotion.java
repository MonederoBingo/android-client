package com.lealpoints.model;

public class CompanyPromotion {
    private final long promotionConfigurationId;
    private final long companyId;
    private final String description;
    private final double requiredPoints;

    public CompanyPromotion(long promotionConfigurationId, long companyId, String description, double requiredPoints) {
        this.promotionConfigurationId = promotionConfigurationId;
        this.companyId = companyId;
        this.description = description;
        this.requiredPoints = requiredPoints;
    }

    public long getPromotionConfigurationId() {
        return promotionConfigurationId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public String getDescription() {
        return description;
    }

    public double getRequiredPoints() {
        return requiredPoints;
    }
}
