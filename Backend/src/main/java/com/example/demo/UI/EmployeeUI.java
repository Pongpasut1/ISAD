package com.example.demo.UI;

public class EmployeeUI {

    private String employeeID;

    public EmployeeUI(String employeeID) {
        this.employeeID = employeeID;
    }

    // แสดงการประเมินตนเอง
    public void viewSelfEvaluation() {
        // ดึง แสดง selfEvaluation
        System.out.println("แสดงการประเมินตนเองสำหรับพนักงาน ID: " + employeeID);
    }

    // แสดงเงินเดือนของตนเอง
    public void viewOwnSalary() {
        // ดึง แสดงเงินเดือนของพนักงาน
        System.out.println("แสดงเงินเดือนสำหรับพนักงาน ID: " + employeeID);
    }

    // ขอรับสลิปเงินเดือน
    public void requestSalarySlip() {
        // ขอสลิปเงินเดือน
        System.out.println("ขอสลิปเงินเดือนสำหรับพนักงาน ID: " + employeeID);
    }

    // Getter และ Setter
    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}
