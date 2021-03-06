package com.massivecraft.massivegates.cmd;

import com.massivecraft.massivecore.cmd.req.ReqHasPerm;
import com.massivecraft.massivegates.Perm;


public class CmdGateFx extends GateCommand
{
	// -------------------------------------------- //
	// FIELDS
	// -------------------------------------------- //
	
	public CmdGateFxAlt cmdMassiveGatesFxAlt = new CmdGateFxAlt();
	public CmdGateFxTest cmdMassiveGatesFxTest = new CmdGateFxTest();
	
	// -------------------------------------------- //
	// CONSTRUCT
	// -------------------------------------------- //
	
	public CmdGateFx()
	{
		// Aliases
		this.addAliases("fx");
		
		// Subcommands
		this.addSubCommand(this.cmdMassiveGatesFxAlt);
		this.addSubCommand(this.cmdMassiveGatesFxTest);
		
		// Requirements
		this.addRequirements(ReqHasPerm.get(Perm.FX.node));
		
		// Help
		this.setHelp(
		"<i>This system is used by the triggeractions \"FXE\" and \"FXG\".",
		"<i>Effects are defined with a comma-list of base effects.",
		"<i>Some effects use a datavalue. Put an int after effect name.",
		"<i>Example: \"<h>smoke,potion5,fire<i>\". (potion got d�ata=5)"
		);
	}
	
}
