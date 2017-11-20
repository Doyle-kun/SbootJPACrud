package com.sboot.crud.model;

import java.util.Map;

public class Report {

    private Double minSalary;

    private String lowestPaidEmployeeName;

    private String lowestPaidEmployeePosition;

    private Double maxSalary;

    private String highestPaidEmployeeName;

    private String highestPaidEmployeePosition;

    private Double avgSalary;

    private Double avgSalaryPerYear;

    private Double totalIncomePerYear;

    private Map<String, Double> individualSalaryMap;

    private Map<String, Double> positionSalarySumMap;

    public Double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Double minSalary) {
        this.minSalary = minSalary;
    }

    public String getLowestPaidEmployeeName() {
        return lowestPaidEmployeeName;
    }

    public void setLowestPaidEmployeeName(String lowestPaidEmployeeName) {
        this.lowestPaidEmployeeName = lowestPaidEmployeeName;
    }

    public String getLowestPaidEmployeePosition() {
        return lowestPaidEmployeePosition;
    }

    public void setLowestPaidEmployeePosition(String lowestPaidEmployeePosition) {
        this.lowestPaidEmployeePosition = lowestPaidEmployeePosition;
    }

    public Double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getHighestPaidEmployeeName() {
        return highestPaidEmployeeName;
    }

    public void setHighestPaidEmployeeName(String highestPaidEmployeeName) {
        this.highestPaidEmployeeName = highestPaidEmployeeName;
    }

    public String getHighestPaidEmployeePosition() {
        return highestPaidEmployeePosition;
    }

    public void setHighestPaidEmployeePosition(String highestPaidEmployeePosition) {
        this.highestPaidEmployeePosition = highestPaidEmployeePosition;
    }

    public Double getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(Double avgSalary) {
        this.avgSalary = avgSalary;
    }

    public Double getAvgSalaryPerYear() {
        return avgSalaryPerYear;
    }

    public void setAvgSalaryPerYear(Double avgSalaryPerYear) {
        this.avgSalaryPerYear = avgSalaryPerYear;
    }

    public Double getTotalIncomePerYear() {
        return totalIncomePerYear;
    }

    public void setTotalIncomePerYear(Double totalIncomePerYear) {
        this.totalIncomePerYear = totalIncomePerYear;
    }

    public Map<String, Double> getIndividualSalaryMap() {
        return individualSalaryMap;
    }

    public void setIndividualSalaryMap(Map<String, Double> individualSalaryMap) {
        this.individualSalaryMap = individualSalaryMap;
    }

    public Map<String, Double> getPositionSalarySumMap() {
        return positionSalarySumMap;
    }

    public void setPositionSalarySumMap(Map<String, Double> positionSalarySumMap) {
        this.positionSalarySumMap = positionSalarySumMap;
    }

}
