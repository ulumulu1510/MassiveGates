package com.massivecraft.massivegates.engine;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.plugin.Plugin;

import com.massivecraft.massivecore.EngineAbstract;
import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.massivegates.MassiveGates;
import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.entity.GateColl;
import com.massivecraft.massivegates.entity.MConf;
import com.massivecraft.massivegates.event.GatePlayerWalkEvent;
import com.massivecraft.massivegates.event.GatePlayerWalkEvent.GatePlayerWalkType;
import com.massivecraft.massivegates.util.VisualizeUtil;

public class MainEngine extends EngineAbstract
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static MainEngine i = new MainEngine();
	public static MainEngine get() { return i; }
	private MainEngine() {}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public Plugin getPlugin()
	{
		return MassiveGates.get();
	}
	
	// -------------------------------------------- //
	// DISABLE VANILLA GATES CONFIG OPTION
	// -------------------------------------------- //
			
	@EventHandler(priority = EventPriority.NORMAL)
	public void disableVanillaGates(PlayerPortalEvent event)
	{
		if (MConf.get().disableVanillaGates)
		{
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void disableVanillaGates(EntityPortalEvent event)
	{
		if (MConf.get().disableVanillaGates)
		{
			event.setCancelled(true);
		}
	}
	
	// -------------------------------------------- //
	// REDSTONE
	// -------------------------------------------- //
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void monitorBlockPhysicsEvent(BlockPhysicsEvent event)
	{
		Block block = event.getBlock();
		PS coord = PS.valueOf(block);
		
		Gate gate = GateColl.get().getGateAtFrameCoord(coord);
		if (gate == null) return;
		
		if (block.isBlockIndirectlyPowered())
		{
			gate.powerAdd(coord);
		}
		else
		{
			gate.powerRemove(coord);
		}
	}
	
	// -------------------------------------------- //
	// PLAYER MOVE
	// -------------------------------------------- //
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerMove(PlayerMoveEvent event)
	{
		if (event.isCancelled()) return;
		
		Block blockFrom = event.getFrom().getBlock();
		Block blockTo = event.getTo().getBlock();
		
		if (blockFrom.equals(blockTo)) return;
		
		Player player = event.getPlayer();
		
		VisualizeUtil.clear(player);
		
		PS coordFrom = PS.valueOf(blockFrom);
		PS coordTo = PS.valueOf(blockTo);
		
		Gate gateFrom = GateColl.get().getGateAtContentCoord(coordFrom);
		Gate gateTo = GateColl.get().getGateAtContentCoord(coordTo);
		
		GatePlayerWalkEvent gateEvent;
		
		if (gateFrom != null & gateFrom == gateTo)
		{
			gateEvent = new GatePlayerWalkEvent(player, gateFrom, gateTo, GatePlayerWalkType.WITHIN);
			gateEvent.run();
			if (gateEvent.isCancelled())
			{
				event.setCancelled(true);
			}
			
			return;
		}
		
		if (gateFrom != null)
		{
			// OUT
			gateEvent = new GatePlayerWalkEvent(player, gateFrom, gateTo, GatePlayerWalkType.OUT);
			gateEvent.run();
			if (gateEvent.isCancelled())
			{
				event.setCancelled(true);
			}
		}
		
		if (gateTo != null)
		{
			// INTO
			gateEvent = new GatePlayerWalkEvent(player, gateFrom, gateTo, GatePlayerWalkType.INTO);
			gateEvent.run();
			if (gateEvent.isCancelled())
			{
				event.setCancelled(true);
			}
		}
	}
	
}
