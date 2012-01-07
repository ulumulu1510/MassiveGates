package com.massivecraft.massivegates.when;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;

import com.massivecraft.massivegates.Gate;

public class ActionUse extends BaseAction
{
	protected static ActionUse instance = new ActionUse();
	public static ActionUse getInstance() { return instance; }
	protected ActionUse()
	{
		super("mgcore_use", "Use", "Make the entity use the gate.");
	}
	
	@Override
	public void perform(String arg, Gate gate, Entity entity, Cancellable cancellable)
	{
		if (gate == null) return;
		if (entity == null) return;
		gate.use(entity);
	}
}