package com.example.covidfight;

public class RichmondItem {
    private String zipcode;
    private String caseNumbers;
    private String testNumbers;

    public RichmondItem(String zipcode, String caseNumbers, String testNumbers) {
        this.zipcode = zipcode;
        this.caseNumbers = caseNumbers;
        this.testNumbers=testNumbers;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCaseNumbers() {
        return caseNumbers;
    }


    public void setCaseNumbers(String caseNumbers) {
        this.caseNumbers = caseNumbers;
    }

    public String getTestNumbers() {
        return testNumbers;
    }


    public void setTestNumbers(String testNumbers) {
        this.testNumbers = testNumbers;
    }

    @Override
    public String toString() {
        return "RichmondItem{" +
                "zipcode='" + zipcode + '\'' +
                ", caseNumbers=" + caseNumbers +
                ", testNumbers=" + testNumbers +
                '}';
    }
}
