package sample;

import java.lang.String;

public class Mark {
    private double weightFactor;
    private double grade;
    private String testName;


    public Mark(String testName, double grade,double weightFactor){
        this.weightFactor = weightFactor;
        this.grade = grade;
        this.testName = testName;
    }

    //Default constructor
    public Mark(){
        weightFactor = 0.0;
        grade = 0.0;
        testName = "";
    }

    //Setter and Getter methods
    public double getWeightFactor() {
        return weightFactor;
    }

    public void setWeightFactor(double weightFactor) {
        this.weightFactor = weightFactor;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
}
