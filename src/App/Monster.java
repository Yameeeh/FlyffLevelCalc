package App;

public class Monster {

	private String name;
	private int lvl;
	private String element;
	private long dmgPercent;
	private double relativeDamage;
	private double efficiency;
	private int requiredKills;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public long getDmgPercent() {
		return dmgPercent;
	}

	public void setDmgPercent(long dmgPercent) {
		this.dmgPercent = dmgPercent;
	}

	public double getRelativeDamage() {
		return relativeDamage;
	}

	public void setRelativeDamage(double proportionateDamage) {
		this.relativeDamage = proportionateDamage;
	}

	public double getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}

	public int getRequiredKills() {
		return requiredKills;
	}

	public void setRequiredKills(int killsNeeded) {
		this.requiredKills = killsNeeded;
	}

}
