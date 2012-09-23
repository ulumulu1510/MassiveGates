package com.massivecraft.massivegates.cmdarg;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import com.massivecraft.massivegates.GPlayer;
import com.massivecraft.massivegates.GPlayers;
import com.massivecraft.massivegates.Gate;
import com.massivecraft.massivegates.Gates;
import com.massivecraft.mcore4.cmd.MCommand;
import com.massivecraft.mcore4.cmd.arg.ArgReader;
import com.massivecraft.mcore4.cmd.arg.ArgResult;
import com.massivecraft.mcore4.util.Txt;

public class ARGate implements ArgReader<Gate>
{
	@Override
	public ArgResult<Gate> read(String str, MCommand mcommand)
	{
		ArgResult<Gate> result = new ArgResult<Gate>();
		
		// Is this by chance a "that" request
		if (str.equalsIgnoreCase("that"))
		{
			if ( ! (mcommand.sender instanceof Player))
			{
				result.getErrors().add("<b>You must be ingame player to use \"that\" gate detection.");
				return null;
			}
			else
			{
				Player me = (Player)mcommand.sender;
				GPlayer gme = GPlayers.i.get(me);
				result.setResult(gme.getThatGate(false));
				if (result.hasResult() == false)
				{
					result.getErrors().add("<b>No gate in sight.");
				}
				return result;
			}
		}
		
		// Then we attempt to get by id.
		result.setResult(Gates.i.get(str));
		if (result.hasResult()) return result;
		
		// No matching id huh?... Lets test against the gate's name then:
		// Build a name to gate map:
		Map<String, Gate> name2gate = new HashMap<String, Gate>();
		for (Gate g : Gates.i.getAll())
		{
			if (g.getName() == null) continue;
			name2gate.put(g.getName(), g);
		}
		
		// Find the best name
		String bestName = Txt.getBestCIStart(name2gate.keySet(), str);
		if (bestName != null)
		{
			result.setResult(name2gate.get(bestName));
		}
		
		if (result.hasResult() == false)
		{
			result.getErrors().add("<b>No gate matching \"<p>"+str+"<b>\".");
		}
		
		return result;
	}
	
	// -------------------------------------------- //
	// INSTANCE
	// -------------------------------------------- //
	
	private static ARGate i = new ARGate();
	public static ARGate get() { return i; }
}