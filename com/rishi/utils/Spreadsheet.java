package com.rishi.utils;

import java.util.HashMap;

public class Spreadsheet {
    private HashMap<String, Object> spreadsheet;

    public Spreadsheet(){
        spreadsheet = new HashMap<>();
    }

    public void setCellValue(String cellId, Object value){
        if(value instanceof String && ((String) value).startsWith("=")){
            int result = evaluateValue(((String) value).substring(1));
            spreadsheet.put(cellId, result);
        }else if(value instanceof String && !((String) value).startsWith("=")){
            int result = evaluateValue(((String) value));
            spreadsheet.put(cellId, result);
        }else{
            spreadsheet.put(cellId, value);
        }
    }

    private int evaluateValue(String substring) throws IllegalArgumentException{
        String[] cells = substring.split("\\+");
        int sum = 0;
        for(String token : cells){
            String cellId = token.trim();
            if(!cellId.matches("[A-Z]\\d+")){
                throw new IllegalArgumentException("Invalid Cell reference + " + cellId);
            }
            sum += getCellValue(cellId);
        }

        return sum;
    }

    public int getCellValue(String cellId) throws IllegalArgumentException{
        Object value = spreadsheet.get(cellId);
        if(value instanceof Integer){
            return (int)value;
        }else{
            throw new IllegalArgumentException("CellId " + cellId + " not present int spreadsheet. Please add value or value entered is incorrect ");
        }
    }



}
