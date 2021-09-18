package hulk.store.kardex.service;

import java.text.DecimalFormat;

public class FormatterService {
    public static String doubleToString(Double num, int decimals){
    	DecimalFormat format = new DecimalFormat();
    	format.setMaximumFractionDigits(decimals);
    	format.setMinimumFractionDigits(decimals);
    	format.setMinimumIntegerDigits(1);    	
    	return format.format(num);
    }

}
