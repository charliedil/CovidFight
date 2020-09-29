package com.example.covidfight;

public class DataRichmond {
    String report_date;
    String zip_code;
    String number_of_cases;
    String number_of_pcr_testing;

    public DataRichmond(String report_date, String zip_code, String number_of_cases, String number_of_pcr_testing) {
        this.report_date = report_date;
        this.zip_code = zip_code;
        this.number_of_cases = number_of_cases;
        this.number_of_pcr_testing = number_of_pcr_testing;
    }

    public String getReport_date() {
        return report_date;
    }

    public void setReport_date(String report_date) {
        this.report_date = report_date;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getNumber_of_cases() {
        return number_of_cases;
    }

    public void setNumber_of_cases(String number_of_cases) {
        this.number_of_cases = number_of_cases;
    }

    public String getNumber_of_pcr_testing() {
        return number_of_pcr_testing;
    }

    public void setNumber_of_pcr_testing(String number_of_pcr_testing) {
        this.number_of_pcr_testing = number_of_pcr_testing;
    }
}
