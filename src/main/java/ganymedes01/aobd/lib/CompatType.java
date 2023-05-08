package ganymedes01.aobd.lib;

import cpw.mods.fml.common.Loader;
import ganymedes01.aobd.recipes.RecipesModule;
import ganymedes01.aobd.recipes.modules.AuraCascade;
import ganymedes01.aobd.recipes.modules.BuildCraftAdditions;
import ganymedes01.aobd.recipes.modules.ElectricalAge;
import ganymedes01.aobd.recipes.modules.EngineersToolbox;
import ganymedes01.aobd.recipes.modules.Factorization;
import ganymedes01.aobd.recipes.modules.GanysNether;
import ganymedes01.aobd.recipes.modules.Hydraulicraft;
import ganymedes01.aobd.recipes.modules.ImmersiveEngineering;
import ganymedes01.aobd.recipes.modules.Mekanism;
import ganymedes01.aobd.recipes.modules.ModularSystems;
import ganymedes01.aobd.recipes.modules.NetherOres;
import ganymedes01.aobd.recipes.modules.Railcraft;
import ganymedes01.aobd.recipes.modules.SimpleOreGrinder;
import ganymedes01.aobd.recipes.modules.Thaumcraft;

public enum CompatType {
	RAILCRAFT("Railcraft", Railcraft.class, "crushed"),
	MEKANISM("Mekanism", Mekanism.class, "clump", "crystal", "shard", "dustDirty", "dust"),
	THAUMCRAFT("Thaumcraft", Thaumcraft.class, "cluster", "nugget"),
	FACTORISATION("factorization", Factorization.class, "crystalline", "cleanGravel", "reduced", "dirtyGravel"),
	GANYS_NETHER("ganysnether", GanysNether.class, "nugget"),
	MODULAR_SYSTEMS("modularsystems", ModularSystems.class, "dust"),
	NETHER_ORES("NetherOres", NetherOres.class, "ore"),
	HYDRAULICRAFT("HydCraft", Hydraulicraft.class, "dust", "chunk"),
	SIMPLE_ORE_GRINDER("simpleoregrinder", SimpleOreGrinder.class, "dust"),
	BC_ADDITIONS("bcadditions", BuildCraftAdditions.class, "dust"),
	ELECTRICAL_AGE("Eln", ElectricalAge.class, "dust"),
	AURA_CASCADE("aura", AuraCascade.class, "dust"),
	ENGINEERS_TOOLBOX("eng_toolbox", EngineersToolbox.class, "ground", "dustImpure", "dust"),
	IMMERSIVE_ENGINEERING("ImmersiveEngineering", ImmersiveEngineering.class, "dust");

	final String modid;
	final String[] prefixes;
	final Class<? extends RecipesModule> module;
	boolean enabled = true;

	CompatType(String modid, Class<? extends RecipesModule> module, String... prefixes) {
		this.modid = modid;
		this.prefixes = prefixes;
		this.module = module;
	}

	public String modID() {
		return modid;
	}

	public String[] prefixes() {
		return prefixes;
	}

	public RecipesModule getModule() throws InstantiationException, IllegalAccessException {
		return module.newInstance();
	}

	public void setStatus(boolean status) {
		enabled = status;
	}

	public boolean isEnabled() {
		return enabled && Loader.isModLoaded(modID());
	}
}