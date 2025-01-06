package App;

import java.util.List;

public class MainClass {
	
	public static void main(String[] args) {
		
		int level = 150;
		int lvlMinMon = level;
		int lvlMaxMon = level + 15;
		
		String url = URLGenerator.generateURL(lvlMinMon, lvlMaxMon, true, "False");
		
		List<Monster> monsters = GatherData.parse(url, level);
        
        int i = 1;
        
        for (Monster monster : monsters) {
        	System.out.println("#" + i + " " + monster.getName() + ", " + monster.getElement() + ", lvl " + monster.getLvl() + ", Relative Damage: " + monster.getRelativeDamage());
        	i++;
		}
		
		
	}

}
