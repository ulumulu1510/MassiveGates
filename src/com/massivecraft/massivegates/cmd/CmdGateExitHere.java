package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivecore.cmd.req.ReqIsPlayer;
import com.massivecraft.massivecore.ps.PS;
import com.massivecraft.massivegates.Perm;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.massivegates.entity.Gate;

public class CmdGateExitHere extends GateCommand
{
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateExitHere()
	{
		// Aliases
		this.addAliases("here", "set");
		
		// Requirements
		this.addRequirements(ReqIsPlayer.get(), ReqGateSelected.get());
		this.addRequirements(ReqHasPerm.get(Perm.EXIT_HERE.node));
	}
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public void perform()
	{
		// Args
		Gate gate = gsender.getSelectedGate();
		
		// Apply
		gate.setExit(PS.valueOf(me.getLocation()));
		
		// Inform
		this.msg("<i>Gate %s<i>: Exit location is now your position.", gate.getIdNameStringShort());
	}
	
}
