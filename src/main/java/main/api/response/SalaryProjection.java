package main.api.response;

public interface SalaryProjection {

    String getName();
    long getAvg();
    void setName(String name);
    void setAvg(long avg);
}
