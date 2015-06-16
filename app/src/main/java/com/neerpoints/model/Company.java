package com.neerpoints.model;

public class Company {
    private final int companyId;
    private final String name;
    private final String urlImageLogo;
    private final double points;

    public Company(int clientId, String name, String urlImageLogo, double points) {
        this.companyId = clientId;
        this.name = name;
        this.urlImageLogo = urlImageLogo;
        this.points = points;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getName() {
        return name;
    }

    public String getUrlImageLogo() {
        return urlImageLogo;
    }

    public double getPoints() {
        return points;
    }
}

