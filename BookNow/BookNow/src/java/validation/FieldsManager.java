package validation;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;


public class FieldsManager {
    public static void forward(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String value = request.getParameter(parameterName);
            
            request.setAttribute(parameterName, value);
        }
    }
}
