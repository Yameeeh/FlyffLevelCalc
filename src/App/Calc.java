package App;

public class Calc {

	public static double calcRelativeDmg(int lvlAttacker, int lvlDefender, int reqDamage) {
		int delta = lvlDefender - lvlAttacker;
		double propDmg;
		if (delta > 0) {
			delta = Math.min(delta, 15);
		} else {
			delta = 0;
		}
		propDmg = reqDamage / calcFactor(delta);
		return propDmg;
	}

	private static double calcFactor(int delta) {
		double factor = Math.cos(Math.PI * delta / 32);
		return factor;
	}

}
