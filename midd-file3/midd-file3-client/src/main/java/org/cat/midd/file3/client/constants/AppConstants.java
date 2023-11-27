package org.cat.midd.file3.client.constants;

public class AppConstants {
	
	public static final class Url {
		public static final String AVATAR_BASE_PATH="/api/v1/avatars";
		public static final String WIKI_BASE_PATH="/api/v1/wikis";
	}
	
	public static final String GROUP_NAME="arch-midd";
	public static final String FEIGN_NAME="app-file3-impl";
	public static final String FULL_FEIGN_NAME = GROUP_NAME+"."+FEIGN_NAME;
	
	public static final String FULL_FEIGN_AVATAR = GROUP_NAME+"."+FEIGN_NAME+".avatar";
	public static final String FULL_FEIGN_WIKI = GROUP_NAME+"."+FEIGN_NAME+".wiki";
}
