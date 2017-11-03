package validation;

import javax.servlet.http.HttpServletRequest;


public class Validation {
    public static boolean isNotEmpty(String data) {
        return (data != null && data.trim().length() >= 1);
    }
    
    public static boolean isEmpty(HttpServletRequest request, String field) {
        String data = request.getParameter(field);
        if (data == null || data.trim().length() == 0) {
            request.setAttribute(field + "_ERROR", "this field is required");
            return true;
        } else {
            return false;
        }
    }
    
    
    public static void assignError(HttpServletRequest request, String field, String message) {
        request.setAttribute(field + "_ERROR", message);
    }
}
