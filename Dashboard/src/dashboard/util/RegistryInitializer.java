package dashboard.util;

import javax.servlet.ServletContext;

import dashboard.registry.AchievementRegistry;

public class RegistryInitializer {

	private static boolean initialized;
	
	static{
		initialized = false;
	}
	
	public static void initialize(ServletContext context){
		initialized = true;
		AchievementRegistry.init(context);
	}
	
	public static boolean initialized(){
		return initialized;
	}
}
