package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.GateCommand;
import com.massivecraft.massivegates.Permission;
import com.massivecraft.massivegates.cmdreq.ReqGateSelected;
import com.massivecraft.mcore4.cmd.arg.ARString;
import com.massivecraft.mcore4.cmd.req.ReqHasPerm;
import com.massivecraft.mcore4.cmd.req.ReqIsPlayer;

public class CmdGateTargetRubberserver extends GateCommand
{
	public CmdGateTargetRubberserver()
	{
		super();
		this.addAliases("rubberserver");
		this.addRequiredArg("name");
		this.addRequirements(ReqIsPlayer.getInstance(), ReqGateSelected.getInstance());
		this.addRequirements(new ReqHasPerm(Permission.TARGET_RUBBERSERVER.node));
	}
	
	@Override
	public void perform()
	{
		Gate gate = gme.getSelectedGate();
		
		String name = this.arg(0, ARString.get());
		if (name == null) return;
		
		gate.getTarget().setRubberServer(name);
		this.msg("<i>Gate "+gate.getIdNameStringShort()+" <i>now has target rubberserver <h>"+name+"<i>.");
	}
}