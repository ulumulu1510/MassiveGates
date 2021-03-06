package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdarg.ARGate;
import com.massivecraft.massivegates.entity.Gate;

public class CmdGateSel extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateSel()
	{
		// Aliases
		this.addAliases("sel");
		
		// Args
		this.addArg(ARGate.get(), "gate", "*get*");
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.SELECT.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform() throws MassiveException
	{
		// Args
		Gate gate = gsender.getSelectedGate();
		
		if ( ! this.argIsSet(0))
		{
			if (gate == null)
			{
				this.msg("<i>No gate selected.");
			}
			else
			{
				this.msg("<i>Currently selected: "+gate.getIdNameStringLong());
			}
			return;
		}
		
		gate = this.readArg();
		
		// Apply
		gsender.setSelectedGate(gate);
		if (me != null)
		{
			gate.visualizeFor(me);
		}
		
		// Inform
		this.msg("<i>Selected gate %s", gate.getIdNameStringLong());
	}
	
}
