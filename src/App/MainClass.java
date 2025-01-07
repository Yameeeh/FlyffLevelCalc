package App;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class MainClass {
	
	public static void main(String[] args) {
		
		int level = 164;
		int lvlMinMon = level;
		int lvlMaxMon = level + 15;
		
		String url = URLGenerator.generateURL(lvlMinMon, lvlMaxMon, true, "False");
		
		List<Monster> monsters = GatherData.parse(url, level);
        
        int i = 1;
        
        for (Monster monster : monsters) {
        	if ( i == 1) {
        		monster.setEfficiency(1.00);
        	} else if (i > 1) {
        		monster.setEfficiency(monsters.get(0).getRelativeDamage() / monster.getRelativeDamage());
        	}
        	DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
    		DecimalFormat df = new DecimalFormat("#.##", symbols);
        	System.out.println("#" + i + " " + monster.getName() + ", " + monster.getElement() + ", lvl " + monster.getLvl() + ", Relative Damage: " + monster.getRelativeDamage() + ", Efficiency: " + df.format(monster.getEfficiency()*100) + "%");
        	i++;
		}
		
		
	}

}
