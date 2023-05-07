package ganymedes01.aobd.ore;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ganymedes01.aobd.lib.CompatType;
import ganymedes01.aobd.lib.Reference;
import net.minecraft.util.StatCollector;

public class Ore {

	public static final ArrayList<Ore> ores = new ArrayList<>();

	private static final Map<String, String> defaultOres = new HashMap<String, String>();
	private static final Map<String, Color> defaultOreColours = new HashMap<String, Color>();

	static {
		defaultOres.put("Cobalt", "Iron");
		defaultOres.put("Ardite", "Gold");
		defaultOres.put("Aluminium", "Tin");
		defaultOres.put("Copper", "Gold");
		defaultOres.put("Tin", "Iron");
		defaultOres.put("Lead", "Silver");
		defaultOres.put("Iron", "Nickel");
		defaultOres.put("Silver", "Lead");
		defaultOres.put("Nickel", "Platinum");
		defaultOres.put("FzDarkIron", "Silver");

		defaultOreColours.put("Cobalt", new Color(0x2376DD));
		defaultOreColours.put("Ardite", new Color(0xF48A00));
		defaultOreColours.put("Aluminium", new Color(0xEDEDED));
		defaultOreColours.put("Tin", new Color(0xD8BFAA));
		defaultOreColours.put("Copper", new Color(0xEF6600));
	}

	public static Ore newOre(String name) {
		double e;
		if (name.equals("Cobalt") || name.equals("Ardite") || name.equals("FzDarkIron"))
			e = 3;
		else if (name.equals("Osmium") || name.equals("Tungsten"))
			e = 2;
		else
			e = 1;

		String extra = defaultOres.get(name);
		if (extra == null)
			extra = name;

		return new Ore(name, extra, e);
	}

	public static Ore newNetherOre(String name) {
		Ore ore = newOre(name);
		ore.energy *= 2;
		return ore;
	}

	public static Color getDefaultOreColour(String oreName) {
		return defaultOreColours.get(oreName);
	}

	private final String name;
	private String extra;
	private double energy;
	private final Set<CompatType> enabledTypes = new HashSet<CompatType>();
	private boolean disabled = false;
	private Color colour = Color.WHITE;

	protected Ore(String name, String extra, double energy) {
		this.name = name;
		this.extra = extra;
		this.energy = energy;
		ores.add(this);

		enabledTypes.addAll(Arrays.asList(CompatType.values()));
	}

	public String name() {
		return name;
	}

	public String translatedName() {
		String unloc = "metal." + Reference.MOD_ID + "." + name().toLowerCase() + ".name";
		return StatCollector.canTranslate(unloc) ? StatCollector.translateToLocal(unloc) : name();
	}

	public String extra() {
		return extra;
	}

	public double energy(double e) {
		return e * energy;
	}

	public void setEnergy(double e) {
		energy = e;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public boolean isCompatEnabled(CompatType type) {
		return isEnabled() && enabledTypes.contains(type);
	}

	public boolean isEnabled() {
		return !disabled;
	}

	public void configType(boolean enable, CompatType type) {
		if (enable)
			enabledTypes.add(type);
		else
			enabledTypes.remove(type);
	}

	public void setDisabled(boolean flag) {
		disabled = flag;
	}

	public int colour() {
		return colour.getRGB() & 0x00FFFFFF;
	}

	public Color getColour() {
		return colour;
	}

	public void setColour(Color colour) {
		if (colour != null)
			this.colour = colour;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Ore && name.equals(((Ore) obj).name);
	}

	@Override
	public String toString() {
		return name();
	}
}