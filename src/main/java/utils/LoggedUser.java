package utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class LoggedUser {

    public static String getEmail (){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username= null ;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        return username;
    }
}
