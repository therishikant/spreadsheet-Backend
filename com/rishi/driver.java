package com.rishi;

import com.rishi.utils.Spreadsheet;

public class driver {
    public static void main(String[] args) {
        Spreadsheet spreadsheet = new Spreadsheet();

        spreadsheet.setCellValue("A1", 13);
        spreadsheet.setCellValue("A2", 14);

        spreadsheet.setCellValue("A3", "=A1+A2");
        System.out.println(spreadsheet.getCellValue("A3"));

        spreadsheet.setCellValue("A4", "=A1+A2+A3");
        System.out.println(spreadsheet.getCellValue("A4"));

        // Extra test cases
        try{
            spreadsheet.setCellValue("A3", "=A1+A5"); // A5 is not present
            System.out.println(spreadsheet.getCellValue("A3"));
        }catch (IllegalArgumentException e){
            System.out.println(e.getLocalizedMessage()); // CellId A5 not present int spreadsheet. Please add value or value entered is incorrect
        }

        try{
            spreadsheet.setCellValue("A3", "A1+A2"); // without '='
            System.out.println(spreadsheet.getCellValue("A3"));
        }catch (IllegalArgumentException e){
            System.out.println(e.getLocalizedMessage()); // 27
        }

        try{
            spreadsheet.setCellValue("A3", "a1+a2"); // A3 = a1 + a2
            System.out.println(spreadsheet.getCellValue("A3"));
        }catch (IllegalArgumentException e){
            System.out.println(e.getLocalizedMessage()); // Invalid Cell reference + a1
        }

    }
}
