package com.example.covidfight;

public class DataRichmond {
  String reportDate;
  String zipCodes;
  String numberOfCases;
  String numberOfPcrTesting;

  public DataRichmond(String reportDate, String zipCodes,
                      String numberOfCases, String numberOfPcrTesting) {
    this.reportDate = reportDate;
    this.zipCodes = zipCodes;
    this.numberOfCases = numberOfCases;
    this.numberOfPcrTesting = numberOfPcrTesting;
  }

  public String getReportDate() {
    return reportDate;
  }

  public void setReportDate(String reportDate) {
    this.reportDate = reportDate;
  }

  public String getZipCodes() {
    return zipCodes;
  }

  public void setZipCodes(String zipCodes) {
    this.zipCodes = zipCodes;
  }

  public String getNumberOfCases() {
    return numberOfCases;
  }

  public void setNumberOfCases(String numberOfCases) {
    this.numberOfCases = numberOfCases;
  }

  public String getNumberOfPcrTesting() {
    return numberOfPcrTesting;
  }

  public void setNumberOfPcrTesting(String numberOfPcrTesting) {
    this.numberOfPcrTesting = numberOfPcrTesting;
  }
}
