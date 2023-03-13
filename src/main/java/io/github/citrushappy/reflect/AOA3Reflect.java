package io.github.citrushappy.reflect;

import io.github.citrushappy.util.ReflectUtil;

import java.lang.reflect.Method;

public class AOA3Reflect {
	public final Class c_PlayerEvents;
	public final Method m_onPlayerTick;

	public AOA3Reflect() throws Exception
	{
		c_PlayerEvents = Class.forName("net.tslat.aoa3.event.PlayerEvents");
		m_onPlayerTick = ReflectUtil.findMethod(c_PlayerEvents, "onPlayerTick");
		//f_NameGenerator_usedNames = ReflectUtil.findField(c_NameGenerator, "usedNames");
	}
}
