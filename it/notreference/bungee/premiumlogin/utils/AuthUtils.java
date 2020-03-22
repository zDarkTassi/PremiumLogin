package it.notreference.bungee.premiumlogin.utils;


import java.util.ArrayList;
import java.util.List;

import it.notreference.bungee.premiumlogin.PremiumLoginMain;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class AuthUtils  {

	
	//Codes:
	//0 - UUID Error
	//1 - Success
	//2 - No Premium
	//3 - Not Online
	//4 - No Premium Connection but is premium.
	//5 - Arleady logged in.
	//6 - Other error..
	//7 - User logged in legacy mode && non consentito nel config.
	
	//3 + 6 (9) = combo of Not Online // Other error.
	
	private static List<ProxiedPlayer> loggedin_premium = new ArrayList<ProxiedPlayer>();
	
	@Deprecated
	public static List<ProxiedPlayer> getLogged() {
		return loggedin_premium;
	}
	
	@Deprecated
	public static boolean isPremiumLogged(ProxiedPlayer p) {
		return loggedin_premium.contains(p);
	}
	
	@Deprecated
	public static void clearLoggedIn() {
		for(ProxiedPlayer player: loggedin_premium) {
			player.disconnect(new TextComponent(ConfigUtils.getConfStr("kick-reload")));
		}
		loggedin_premium.clear();
	}
	
	public static int login(ProxiedPlayer p, AuthKey key) {
		
		//if(isPremiumLogged(p)) {
			//Messages.logConsole("arleady_logged_user: " + p.getName());
			//return 5;
		//}
		
		//UUID Check 1
		//String q = p.getUniqueId().toString().substring(0, 4);
		//String qk = key.getUUID().toString().substring(0, 4);
		//if(!q.equalsIgnoreCase(qk)) {
			//UUID Error
			//return 0;
		//}
		
		
		if(!ConfigUtils.hasPremiumAutoLogin(p)) {
			return 6;
		}
		
		if(!UUIDVerify.isPremium(p)) {
			Messages.logConsole("nopremium_user: " + p.getName());
			return 2;
		}
		
		if(key.getConType() == TipoConnessione.LEGACY) {
		
			if(!ConfigUtils.allowLegacy()) {
				Messages.logConsole("[[nolegacy]] connectionblock_user: " + p.getName());
				return 7;
			}
		if(!UUIDVerify.isPremiumConnectionLegacy(p) && !UUIDVerify.isPremiumConnection(p)) {
			Messages.logConsole("no_launcher_user: " + p.getName());
			return 4;
		}
		} else {
			
			if(!UUIDVerify.isPremiumConnection(p)) {
				Messages.logConsole("no_launcher_user: " + p.getName());
				return 4;
			}
		}
		
		if(!p.isConnected()) {
			return 3;
		}
		
		//Forcelogghiamo.
		
		Messages.logConsole("forcelogging_user: " + p.getName());
		try {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
         out.writeUTF("AuthMe.v2");
         out.writeUTF("perform.login");
         out.writeUTF(p.getName());
         key.playerServer().sendData("BungeeCord", out.toByteArray());
         PremiumLoginMain.i().getProxy().getServerInfo(ConfigUtils.getConfStr("auth-server")).sendData("BungeeCord", out.toByteArray());
         Messages.logConsole("[[premium:forcelogin] donelogin_user: " + p.getName());
         if(key.getAuthType() == AuthType.AUTO) {
        	 Messages.logConsole(p.getName() + " has been forcelogged (premium mode)."); 
        	 Messages.sendParseColors(p, PremiumLoginMain.i().getConfig().getString("auto-login-premium"));
        	 Messages.logStaff(ConfigUtils.getConfStr("user-forcelogged"), new PlaceholderConf(p.getName(), p.getUniqueId(), p.getAddress().getHostName()));
            // getLogged().add(p);
         } else {
        	 Messages.logConsole(p.getName() + " has been forcelogged using /premium (premium mode)."); 
        	 Messages.sendParseColors(p, PremiumLoginMain.i().getConfig().getString("login-premium"));
        	 Messages.logStaff(ConfigUtils.getConfStr("user-forcelogged"), new PlaceholderConf(p.getName(), p.getUniqueId(), p.getAddress().getHostName()));
            // getLogged().add(p);
         }
         return 1;
		} catch(Exception exc) {
			Messages.sendParseColors(p, PremiumLoginMain.i().getConfig().getString("error-generic"));
			exc.printStackTrace();
			return 6;
		}
	}

	
	
	
}
