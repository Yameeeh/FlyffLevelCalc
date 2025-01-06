package App;


public class URLGenerator {

	public static String generateURL(int minlvlMonster, int maxlvlMonster, boolean hideNoDrops, String includeFlying) {
		return "https://flyffipedia.com/monsters?min=" + minlvlMonster + "&drops=" + hideNoDrops + "&max=" + maxlvlMonster + "&sort=dmg-1&flying=" + includeFlying + "&rank=Normal%2cCaptain%2cSmall";
	}
	
}
