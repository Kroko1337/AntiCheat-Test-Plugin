package de.krokoyt.actest;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;

import fr.neatmonster.nocheatplus.NCPAPIProvider;
import fr.neatmonster.nocheatplus.checks.Check;
import fr.neatmonster.nocheatplus.checks.CheckType;
import fr.neatmonster.nocheatplus.checks.ViolationData;
import fr.neatmonster.nocheatplus.checks.ViolationHistory.ViolationLevel;
import fr.neatmonster.nocheatplus.checks.access.IViolationInfo;
import fr.neatmonster.nocheatplus.checks.moving.MovingConfig;
import fr.neatmonster.nocheatplus.checks.moving.MovingData;
import fr.neatmonster.nocheatplus.components.NoCheatPlusAPI;
import fr.neatmonster.nocheatplus.components.registry.feature.NCPListener;
import fr.neatmonster.nocheatplus.hooks.IFirst;
import fr.neatmonster.nocheatplus.hooks.IStats;
import fr.neatmonster.nocheatplus.hooks.NCPHook;
import fr.neatmonster.nocheatplus.hooks.NCPHookManager;
import fr.neatmonster.nocheatplus.logging.LogManager;
import fr.neatmonster.nocheatplus.players.DataManager;
import fr.neatmonster.nocheatplus.players.PlayerData;
import fr.neatmonster.nocheatplus.utilities.CheckUtils;

public class NCPEvents implements NCPHook, IStats, IFirst, Listener{

	@Override
	public String getHookName() {
		// TODO Auto-generated method stub
		return Main.instance.getName();
	}

	@Override
	public String getHookVersion() {
		// TODO Auto-generated method stub
		return Main.instance.pl.getDescription().getVersion();
	}
	

	@Override
	public boolean onCheckFailure(CheckType arg0, Player arg1, IViolationInfo arg2) {
		ViolationData data = (ViolationData) arg2;
		LogManager lm = NCPAPIProvider.getNoCheatPlusAPI().getLogManager();
		PlayerData pd = DataManager.getPlayerData(arg1);
		CheckUtils cu = new CheckUtils(); 
//		arg1.sendMessage(CheckUtils.getLogMessagePrefix(arg1, CheckType.ALL));
		
		arg1.sendMessage(Main.flag + "§7" +arg1.getName() + " §ffailed §b" + arg0.name() + " §f(+" + data.getAddedVl()+") Details: \n");
		return false;
	}
	
	
	
	

}
