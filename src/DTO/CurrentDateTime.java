package DTO;

import java.time.LocalDateTime;

public class CurrentDateTime {
    LocalDateTime currentDateTime;

    public CurrentDateTime() {
        this.currentDateTime = LocalDateTime.now();
    }

    public int getYear() {
        return currentDateTime.getYear();
    }

    public String getYearAndDate(){
        return String.valueOf(currentDateTime.getDayOfMonth()+"-"+currentDateTime.getMonthValue()+"-"+this.getYear());
    }

    public LocalDateTime getCurrentDateTime() {
        return currentDateTime;
    }

    public String getTime() {
        // Get the hour, minute, and second components
        int hour = currentDateTime.getHour();
        int minute = currentDateTime.getMinute();
        int second = currentDateTime.getSecond();

        // Format the time components
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    public String getFormatDateTime(){
        return this.getYearAndDate()+" "+this.getTime();
    }

    public String getdate(){
        return String.valueOf(currentDateTime.toLocalDate());
    }
    public static void main(String[] args) {
        CurrentDateTime dt = new CurrentDateTime();
        System.out.println("Current date and time: " + dt.getdate());
    }
}
