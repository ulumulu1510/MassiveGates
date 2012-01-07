package com.massivecraft.massivegates.when;

import java.util.Arrays;
import java.util.List;

public abstract class BaseAction implements Action
{
	protected final String id;
	@Override public String getId() { return id; }
	
	protected final String name;
	@Override public String getName() { return name; }
	
	protected final String desc;
	@Override public String getDesc() { return desc; }
	
	protected BaseAction(final String id, final String name, final String desc)
	{
		this.id = id;
		this.name = name;
		this.desc = desc;
	}
	
	
	public final static List<String> errorsNoneExpected = Arrays.asList("<b>No argument expected for this action.");
	@Override
	public List<String> checkArg(String arg)
	{
		if (arg == null) return null;
		return errorsNoneExpected;
	}
	
	@Override
	public boolean equals(Object that)
	{
		if (that == null) return false;
        if (this.getClass() != that.getClass()) return false;
        return this.getId().equals(((BaseAction)that).getId());
	}
	
	@Override
	public int hashCode()
	{
		return this.getId().hashCode();
	}
}
