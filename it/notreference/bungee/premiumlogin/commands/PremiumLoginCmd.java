package it.notreference.bungee.premiumlogin.commands;

import it.notreference.bungee.premiumlogin.utils.ConfigUtils;
import it.notreference.bungee.premiumlogin.utils.Messages;
import it.notreference.bungee.premiumlogin.utils.UUIDVerify;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PremiumLoginCmd extends Command{

	public PremiumLoginCmd() {
		super("premiumlogin");
	}

	private static String by = "§c§l(!) §7This server is using §bPremiumLogin §e1.0 §7by §3NotReference§7.";
	
	public static String getByMessage() {
		return by;
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		
		ProxiedPlayer p = (ProxiedPlayer) sender;
		Messages.send(p, by);
		Messages.send(p, "§c§l(!) §7Your status / info:");
		if(UUIDVerify.isPremium(p)) {
		Messages.send(p, "§c§l(!) §7Premium:§a Yes");
		} else {
			Messages.send(p, "§c§l(!) §7Premium:§c No");
		}
		if(UUIDVerify.isPremiumConnection(p)) {
			Messages.send(p, "§c§l(!) §7Using Premium Launcher:§a Yes");
		} else {
			Messages.send(p, "§c§l(!) §7Using Premium Launcher:§c No");
		}
		if(ConfigUtils.hasPremiumAutoLogin(p)) {
			Messages.send(p, "§c§l(!) §7Default login system: §aPremiumLogin");
		} else {
			Messages.send(p, "§c§l(!) §7Default login system: §eAuthMe");
		}
		
	}
	
}
