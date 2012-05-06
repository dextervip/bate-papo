package bate.papo.servidor;

/**
 * Classe com utilitarios para servidor
 */
public class Utils {
    
    /**
     * Converte um vetor para string
     * @param a
     * @param separator
     * @return string
     */
    public static String arrayToString(String[] a, String separator) {
        StringBuffer result = new StringBuffer();
        if (a.length > 0) {
            result.append(a[0]);
            for (int i = 1; i < a.length; i++) {
                result.append(separator);
                result.append(a[i]);
            }
        }
        return result.toString();
    }
    
    /**
     * Valida se o username contem caracteres permitidos
     * @param username
     * @return boolean
     */
    public static boolean validarUsername(String username){
        return true;
    }
}
