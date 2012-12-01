package com.sumioturk.sashimi.android;

import java.util.HashMap;
/**
 * A enum represents SashimiApi and resolves its parameters  
 * @author sean
 */
public enum SashimiApi {
	join, login, oauth_url, oauth, update_sashimi, update_escape_term, toggle, eight, nine;
	public HashMap<String, String> getParams() {
		HashMap<String, String> hmap = new HashMap<String, String>();
		switch (this.ordinal()) {
		case 0:
			hmap.put("name", "");
			hmap.put("pass", "");
			hmap.put("sashimi", "");
			return hmap;
		case 1:
			hmap.put("name", "");
			hmap.put("pass", "");
			return hmap;
		case 2:
			hmap.put("key", "");
			return hmap;
		case 3:
			hmap.put("key", "");
			hmap.put("oauth_token", "");
			hmap.put("oauth_verifier", "");
			return hmap;
		case 4:
			hmap.put("key", "");
			hmap.put("sashimi", "");
			return hmap;
		case 5:
			hmap.put("key", "");
			hmap.put("escape_term", "");
			return hmap;
		case 6:
			hmap.put("key", "");
			return hmap;
		case 7:
			hmap.put("key", "");
			return hmap;
		case 8:
			hmap.put("key", "");
			return hmap;
		default:
			return hmap;
		}
	}
}
