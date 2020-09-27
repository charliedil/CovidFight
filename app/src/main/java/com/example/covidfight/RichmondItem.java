package com.example.covidfight;

public class RichmondItem {
    private String zipcode;
    private int caseNumbers;

    public RichmondItem(String zipcode, int caseNumbers) {
        this.zipcode = zipcode;
        this.caseNumbers = caseNumbers;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getCaseNumbers() {
        return caseNumbers;
    }
    public String getStringCaseNumbers() {
        return Integer.toString(caseNumbers);
    }

    public void setCaseNumbers(int caseNumbers) {
        this.caseNumbers = caseNumbers;
    }

    @Override
    public String toString() {
        return "RichmondItem{" +
                "zipcode='" + zipcode + '\'' +
                ", caseNumbers=" + caseNumbers +
                '}';
    }
}
