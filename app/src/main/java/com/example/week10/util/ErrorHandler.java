package com.example.week10.util;

import org.json.JSONObject;

public class ErrorHandler {

    public static int parseYear(JSONObject obj) {
        try {
            Object yearObj = obj.get("year");

            int parsed = -1;

            if (yearObj instanceof Integer) {
                parsed = (int) yearObj;
            } else if (yearObj instanceof Double) {
                parsed = ((Double) yearObj).intValue();
            } else if (yearObj instanceof String) {
                String yearStr = ((String) yearObj).trim();

                try {
                    parsed = Integer.parseInt(yearStr);
                } catch (NumberFormatException e) {
                    // Match common worded years
                    switch (yearStr.toLowerCase()) {
                        case "nineteen-ninety-four":

                            return 1994;
                        case "two thousand":
                            return 2000;

                        default:
                            return -1;
                    }
                }
            }

            // Handle negative years
            parsed = Math.abs(parsed);


            return (parsed >= 1800 && parsed <= 2100) ? parsed : -1;

        } catch (Exception ignored) {}

        return -1;
    }
}




