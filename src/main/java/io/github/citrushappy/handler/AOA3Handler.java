package io.github.citrushappy.handler;

import io.github.citrushappy.CitrusThings;
import io.github.citrushappy.reflect.AOA3Reflect;
import io.github.citrushappy.util.CompatUtil;
import io.github.citrushappy.util.CriticalException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class AOA3Handler {
	AOA3Reflect reflector;

	public AOA3Handler()
	{
		try
		{
			this.reflector = new AOA3Reflect();

			//remove old handler
			CompatUtil.findAndRemoveHandlerFromEventBus(TickEvent.PlayerTickEvent.class.getName(), "onPlayerTick");

			//TODO: add our own handler, currently some mechanics from onPlayerTick we need to recreate, see the method below
			//CompatUtil.subscribeEventManually(reflector.c_PlayerEvents, this, reflector.);
		}
		catch (Exception e)
		{
			CitrusThings.logger.error("Failed to setup AOA3Handler!", e);

			//Crash on Critical
			if(e instanceof CriticalException)
				throw new RuntimeException(e);
		}
	}

	@SubscribeEvent
	public void onPlayerTick(final TickEvent.PlayerTickEvent ev) {
		/*
        if (ev.phase == TickEvent.Phase.END) {
            if (!ev.player.world.isRemote) {
                PlayerDataManager plData = PlayerUtil.getAdventPlayer(ev.player);

                plData.tickPlayer();
            }

            if (ev.player.dimension == ConfigurationUtil.MainConfig.dimensionIds.shyrelands) {
                if (!ev.player.world.isRemote)
                    ShyrelandsEvents.doPlayerTick(ev.player);
            }
            else if (ev.player.dimension == ConfigurationUtil.MainConfig.dimensionIds.lelyetia) {
                LelyetiaEvents.doPlayerTick(ev.player);
            }
            else if (ev.player.dimension == ConfigurationUtil.MainConfig.dimensionIds.voxPonds) {
                if (!ev.player.world.isRemote)
                    VoxPondsEvents.doPlayerTick(ev.player);
            }
        }

		 */
	}
}
