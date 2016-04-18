/**
 * Copyright (c) 2015, MindFusion LLC - Bulgaria.
 */

package effects;

import com.mindfusion.drawing.Color;


class Preset
{
	@Override()
	public String toString()
	{
		if (Name == null)
			return "(untitled)";

		return Name;
	}

	public String Name;

	// Glass
	public boolean UseGlassEffect;
	public int GlassEffectType;
	public boolean UsePenAsGlow;
	public Color GlowColor;
	public Color ReflectionColor;

	// Aero
	public boolean UseAeroEffect;
	public int Opacity;
	public Color InnerOutlineColor;
	public Color ShadeColor;

	// Misc
	public boolean OverridesThemeSettings;
	public Color CalendarBackground;
	public Color CalendarBorder;
	public Color ItemBorder;
	public Color ItemBackground1;
	public boolean UseItemBackground2;
	public Color ItemBackground2;

	public Integer Theme;
}
