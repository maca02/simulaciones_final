
package objects;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.log;
import static java.lang.Math.sqrt;
import java.util.Random;


public class Distribuciones {
    
    private static int var = 0;
    
    public static double calcular_uniforme(double a, double b, double rnd) {
        return a + (rnd* (b-a));
    }       
    
    
    public static double calcular_exponencial(double media, double rnd) {
        return (media*-1) * Math.log(1-rnd);
    }
        
   
}
