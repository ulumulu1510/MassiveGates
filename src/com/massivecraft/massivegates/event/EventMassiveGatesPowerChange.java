package com.massivecraft.massivegates.event;

import org.bukkit.event.HandlerList;

import com.massivecraft.massivegates.entity.Gate;
import com.massivecraft.massivegates.event.abs.EventMassiveGatesSingle;

public class EventMassiveGatesPowerChange extends EventMassiveGatesSingle
{
	// -------------------------------------------- //
	// REQUIRED EVENT CODE
	// -------------------------------------------- //
	
	private static final HandlerList handlers = new HandlerList();
	public HandlerList getHandlers() { return handlers; }
	public static HandlerList getHandlerList() { return handlers; }
	
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	// FIELD: power
	protected boolean power;
	public boolean powerHas() { return this.power; }
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public EventMassiveGatesPowerChange(Gate gate, boolean power)
	{
		super(gate);
		this.power = power;
	}
	
}
