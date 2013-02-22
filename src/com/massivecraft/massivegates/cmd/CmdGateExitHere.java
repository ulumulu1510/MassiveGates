package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore.PS;
import com.massivecraft.mcore.cmd.req.ReqHasPerm;
import com.massivecraft.mcore.cmd.req.ReqIsPlayer;

public class CmdGateExitHere extends GateCommand
{
	public CmdGateExitHere()
	{
		super();
		this.addAliases("here", "set");
		this.addRequirements(ReqIsPlayer.get(), ReqGateSelected.get());
		this.addRequirements(new ReqHasPerm(Permission.EXIT_HERE.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		gate.setExit(new PS(me));
		this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>now has this as exit location.");
	}
}