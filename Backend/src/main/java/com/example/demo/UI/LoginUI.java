package com.example.demo.UI;

public class LoginUI {

    // แสดงสถานะการเข้าสู่ระบบ
    public void displayLoginStatus(boolean status) {
        if (status) {
            System.out.println("เข้าสู่ระบบสำเร็จ!");
        } else {
            System.out.println("เข้าสู่ระบบล้มเหลว กรุณาลองใหม่อีกครั้ง");
        }
    }

    // แสดงข้อความเมื่อผู้ใช้ออกจากระบบ
    public void showLogoutMessage() {
        System.out.println("คุณได้ออกจากระบบเรียบร้อยแล้ว");
    }

    // เริ่มกระบวนการเข้าสู่ระบบ
    public void initiateLogin() {
        // โค้ดสำหรับเริ่มต้นกระบวนการเข้าสู่ระบบ
        System.out.println("โปรดป้อนชื่อผู้ใช้และรหัสผ่านของคุณ");
        // คุณสามารถเพิ่มโค้ดสำหรับรับข้อมูลผู้ใช้ที่นี่
    }

    // เริ่มกระบวนการออกจากระบบ
    public void initiateLogout() {
        // โค้ดสำหรับเริ่มต้นกระบวนการออกจากระบบ
        System.out.println("กำลังออกจากระบบ...");
        showLogoutMessage();
    }

}

