package App;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class MainClass {

	public static void main(String[] args) {

		int level = 150;
		int lvlMinMon = level;
		int lvlMaxMon = level + 15;

		String url = URLGenerator.generateURL(lvlMinMon, lvlMaxMon, true, "False");

		List<Monster> monsters = GatherData.parse(url, level);

		DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
		DecimalFormat efficiencyFormat = new DecimalFormat("#.##", symbols);
		DecimalFormat damageFormat = new DecimalFormat("#,###", symbols);

		int i = 1;
		for (Monster monster : monsters) {
			if (i == 1) {
				monster.setEfficiency(1.00);
			} else {
				monster.setEfficiency(monsters.get(0).getRelativeDamage() / monster.getRelativeDamage());
			}

			System.out.println("#" + String.format("%02d", i) + " " + monster.getName() + ", " + monster.getElement()
					+ ", lvl " + monster.getLvl() + ", Relative Damage per %: "
					+ damageFormat.format(monster.getRelativeDamage()) + ", Efficiency: "
					+ efficiencyFormat.format(monster.getEfficiency() * 100) + "%");
			i++;
		}

	}

}
