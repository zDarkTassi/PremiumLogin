package it.notreference.bungee.premiumlogin.utils;

import java.util.UUID;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class AuthenticationBuilder {

	private AuthType type;
	private ProxiedPlayer p;
	private String name;
	private TipoConnessione cont;
	private UUID unique;
	private ServerInfo madonna;
	
	
	public AuthenticationBuilder setName(String n) {
		name = n;
		return this;
	}
	
	public AuthenticationBuilder setConnectionType(TipoConnessione con) {
		cont = con;
		return this;
	}
	
	public AuthenticationBuilder setAuthType(AuthType typ) {
		type = typ;
		return this;
	}
	
	public AuthenticationBuilder setPlayer(ProxiedPlayer ple) {
		p = ple;
		return this;
	}

	public AuthenticationBuilder setUUID(UUID merda) {
		unique = merda;
		return this;
	}
	
	public AuthenticationBuilder setServer(ServerInfo serv) {
		madonna = serv;
		return this;
	}
	
	public AuthKey build() {
		return new AuthKey(unique, p, madonna, name, cont, type);
	}
	
	
}
