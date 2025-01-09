package App;

import java.util.List;
import java.util.StringJoiner;

public class URLGenerator {

	public static String generateURL(int minlvlMonster, int maxlvlMonster, boolean hideNoDrops, String includeFlying,
			List<String> areas) {

		StringJoiner areaJoiner = new StringJoiner("%2c");
		for (String area : areas) {
			areaJoiner.add(area);
		}
		System.out.println(areaJoiner.toString());

		return "https://flyffipedia.com/monsters?min=" + minlvlMonster + "&drops=" + hideNoDrops + "&max="
				+ maxlvlMonster + "&sort=dmg-1&flying=" + includeFlying + "&rank=Normal%2cCaptain%2cSmall"
				+ (areas.isEmpty() ? "" : "&area=" + areaJoiner.toString());
	}

}
