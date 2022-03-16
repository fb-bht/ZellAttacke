package de.bht.cellattack.helper;

/**
 * Validator Class
 */
public class Validator {
    
    /** 
     * @param email
     * @return boolean
     */
    public static boolean isValidEmail(String email) {
      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      return email.matches(regex);
   }
    
}
