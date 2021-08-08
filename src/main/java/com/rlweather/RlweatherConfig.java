package com.rlweather;

import com.sun.org.apache.xpath.internal.operations.Bool;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import java.awt.Color;

@ConfigGroup("Weather")
public interface RlweatherConfig extends Config
{
	// LOCATION AWARENESS
	@ConfigItem(
			keyName = "lockedtomusic",
			name = "Locked to location",
			description = "eg, it will rain at Barbarian village but snow at Ice Mountain"
	)
	default boolean lockedtomusicEnabled()
	{
		return true;
	}

	// LIGHTNING
	@ConfigItem(
			keyName = "lightningenabled",
			name = "Lightning Enabled",
			description = "Is it really horrendous out?"
	)
	default boolean lightningEnabled()
	{
		return false;
	}
	@ConfigItem(
			keyName = "lightningfrequency",
			name = "Lightning Frequency (WARNING BRIGHT FLASHES)",
			description = "The rough frequency of the lightning in ticks"
	)
	default int lightningFrequency()
	{
		return 100;
	}
	@ConfigItem(
			keyName = "lightningcolor",
			name = "Lightning Color",
			description = "The color of the lightning"
	)
	default Color lightningColor()
	{
		return new Color(255, 255, 253);
	}


	// RAIN
	@ConfigItem(
			keyName = "rainenabled",
			name = "Rain Enabled",
			description = "Is it raining?"
	)
	default boolean rainEnabled()
	{
		return true;
	}

	@ConfigItem(
			keyName = "raincolor",
			name = "Rain Color",
			description = "The color of the rain"
	)
	default Color rainColor()
	{
		return new Color(136, 151, 240);
	}

	@ConfigItem(
			keyName = "rainthickness",
			name = "Rain Thickness",
			description = "The thickness of the rain"
	)
	default int rainThickness()
	{
		return 1;
	}

	@ConfigItem(
			keyName = "rainwind",
			name = "Rain Wind Speed",
			description = "The wind affecting the rain"
	)
	default int rainWind()
	{
		return 2;
	}

	@ConfigItem(
			keyName = "raingravity",
			name = "Rain Gravity",
			description = "The speed of the rain"
	)
	default int rainGravity()
	{
		return 8;
	}

	@ConfigItem(
			keyName = "raindiv",
			name = "Rain Dither",
			description = "The dither of the rain (zig-zagging)"
	)
	default int rainDiv()
	{
		return 4;
	}


	// SNOW
	@ConfigItem(
			keyName = "snowenabled",
			name = "Snow Enabled",
			description = "Is it snowing?"
	)
	default boolean snowEnabled()
	{
		return false;
	}

	@ConfigItem(
			keyName = "snowcolor",
			name = "Snow Color",
			description = "The color of the snow"
	)
	default Color snowColor()
	{
		return new Color(255, 255, 255);
	}

	@ConfigItem(
			keyName = "snowthickness",
			name = "Snow Thickness",
			description = "The thickness of the snow"
	)
	default int snowThickness()
	{
		return 4;
	}

	@ConfigItem(
			keyName = "snowwind",
			name = "Snow Wind Speed",
			description = "The wind affecting the snow"
	)
	default int snowWind()
	{
		return 2;
	}

	@ConfigItem(
			keyName = "snowgravity",
			name = "Snow Gravity",
			description = "The speed of the snow"
	)
	default int snowGravity()
	{
		return 3;
	}

	@ConfigItem(
			keyName = "snowdiv",
			name = "Snow Dither",
			description = "The dither of the snow (zig-zagging)"
	)
	default int snowDiv()
	{
		return 3;
	}
}
