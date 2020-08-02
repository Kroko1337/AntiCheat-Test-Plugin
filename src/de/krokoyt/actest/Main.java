package de.krokoyt.actest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.elikill58.negativity.spigot.SpigotNegativity;
import com.elikill58.negativity.sponge.precogs.NegativityBypassService;
import com.elikill58.negativity.universal.translation.NegativityTranslationProviderFactory;

import de.krokoyt.actest.cmds.ac;
import de.krokoyt.actest.cmds.addperms;
import de.krokoyt.actest.cmds.heal;
import de.krokoyt.actest.cmds.sendmessage;
import fr.neatmonster.nocheatplus.NCPAPIProvider;
import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.hooks.NCPHookManager;
import me.konsolas.aac.api.AACAPI;
import me.konsolas.aac.api.AACAPIProvider;

public class Main extends JavaPlugin{
	
	public static String prefix = "§aCore §8>> §7";
	public static String flag = "§7[§3VL§7] §7";
	
	public Main() {
		instance = this;
	}
	
	public static Main instance;
	
	Plugin pl = this;
	
	public File dir = new File(getDataFolder() + "");
	public File f = new File(dir, "config.cfg");
	public FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
	
	public File perms = new File(dir, "perms.cfg");
	public FileConfiguration pcfg = YamlConfiguration.loadConfiguration(perms);
	
	@Override
	public void onEnable() {
		System.out.println("Loaded Anti Cheat Test Plugin");
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Events(), this);
		Bukkit.getPluginCommand("heal").setExecutor(new heal());	
		Bukkit.getPluginCommand("ac").setExecutor(new ac());
		Bukkit.getPluginCommand("addperms").setExecutor(new addperms());
		Bukkit.getPluginCommand("sendmessage").setExecutor(new sendmessage());
		
		if(AACAPIProvider.getAPI() != null) {
			pm.registerEvents(new AACEvents(), this);
		}
		
		if(NCPAPIProvider.getNoCheatPlusAPI() != null) {
			NCPHookManager.addHook(CheckType.ALL, new NCPEvents());
		}
		
		if(SpigotNegativity.getInstance().isInitialized()) {
			pm.registerEvents(new NegativityEvents(), this);

		}
		
		ArrayList<String> apis = new ArrayList<String>();
		for(Plugin pl : Bukkit.getPluginManager().getPlugins()) {
		if(pl.getName().contains("Lib")) {
			apis.add(pl.getName());
		}
		}
		
		if(!cfg.contains("API-List")) {
		cfg.set("API-List", apis);
		}
		
		
		try {
			cfg.save(f);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(!perms.exists()) {
			try {
				perms.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
	}

}
