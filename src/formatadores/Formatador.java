package formatadores;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author diego
 */
public class Formatador {
    
    public final static NumberFormat numeroFormatador = NumberFormat.getInstance(new Locale("pt", "BR"));
    public final static DateTimeFormatter dataFormatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
}
