package it.notreference.bungee.premiumlogin.commands;

import it.notreference.bungee.premiumlogin.PremiumLoginMain;
import it.notreference.bungee.premiumlogin.utils.ConfigUtils;
import it.notreference.bungee.premiumlogin.utils.Messages;
import it.notreference.bungee.premiumlogin.utils.UUIDVerify;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class PremiumLookUpCmd extends Command{

	public PremiumLookUpCmd() {
		super("premiumlookup");
	}
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		
		ProxiedPlayer p = (ProxiedPlayer) sender;
		if(p.hasPermission("premiumlogin.staff")) {
			if(args.length == 1) {
				ProxiedPlayer target = PremiumLoginMain.i().getProxy().getPlayer(args[0]);
				if(!target.isConnected() || target == null) {
					Messages.send(p, "§e§l(!) §b" + args[0] + " §7è §coffline.");
					Messages.send(p, "§c§l(!) §7Offline Info:");
					if(!ConfigUtils.hasPremiumAutoLogin(target)) {
					Messages.send(p, "§c§l(!) §7Default Login System:§b AuthMe");
					} else {
						Messages.send(p, "§c§l(!) §7Default Login System:§b PremiumLogin");
					}
					if(UUIDVerify.isPremium(target)) {
					Messages.send(p, "§c§l(!) §7Premium:§a Yes");
					} else {
						Messages.send(p, "§c§l(!) §7Premium: §cNo");
					}
					if(!UUIDVerify.isPremium(target)) {
					Messages.send(p, "§c§l(!) §7Recommended Login System:§e AuthMe");
					} else {
						Messages.send(p, "§c§l(!) §7Recommended Login System: §ePremiumLogin");
					}
					if(UUIDVerify.isPremium(target)) {
					Messages.send(p, "§c§l(!) §7UUID:§f " + UUIDVerify.getPremiumUUID(target.getName()));
					} else {
						Messages.send(p, "§c§l(!) §7UUID:§f " + UUIDVerify.getCrackedUUID(target.getName()));
					}
				} else {
					Messages.send(p, "§2§l(!) §b" + args[0] + " §7è §aonline§7 su§b " + target.getServer().getInfo().getName() + " §7.");
					Messages.send(p, "§c§l(!) §7Info:");
					if(ConfigUtils.hasPremiumAutoLogin(target)) {
						Messages.send(p, "§c§l(!) §7Logged in with:§a PremiumLogin");
						Messages.send(p, "§c§l(!) §7Used Launcher:§a Minecraft ");
					} else {
						Messages.send(p, "§c§l(!) §7Logged in with:§b AuthMe");
					}
					if(!ConfigUtils.hasPremiumAutoLogin(target)) {
					Messages.send(p, "§c§l(!) §7Default Login System:§b AuthMe");
					} else {
						Messages.send(p, "§c§l(!) §7Default Login System:§b PremiumLogin");
					}
					if(UUIDVerify.isPremium(target)) {
					Messages.send(p, "§c§l(!) §7Premium:§a Yes");
					} else {
						Messages.send(p, "§c§l(!) §7Premium: §cNo");
					}
					if(!UUIDVerify.isPremium(target)) {
					Messages.send(p, "§c§l(!) §7Recommended Login System:§e AuthMe");
					} else {
						Messages.send(p, "§c§l(!) §7Recommended Login System: §ePremiumLogin");
					}
					if(UUIDVerify.isPremium(target)) {
					Messages.send(p, "§c§l(!) §7UUID:§f " + UUIDVerify.getPremiumUUID(target.getName()));
					} else {
						Messages.send(p, "§c§l(!) §7UUID:§f " + UUIDVerify.getCrackedUUID(target.getName()));
					}
				}
			} else {
				Messages.send(p, "§c§l(!) §7Correct Usage:§f /premiumlookup (player)");
			}
		} else {
			Messages.send(p, ConfigUtils.getConfStr("no-perms"));
		}
		
	}
	
}
