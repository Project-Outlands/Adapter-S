package top.outlands;

import net.minecraftforge.fml.common.EntrypointRegistrar;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.IDeclarativeEntrypoint;

/**
 * Adapter-S's declarative entry point.
 *
 * <p>Adapter-S has no mod class and no loading plugin: the loader reads the {@code mcmod.info} {@code jar} section,
 * turns the declared {@code scala} provider into a language adapter when a mod asks for it, and builds this container
 * from the declared entry point. Registering here only exists so the provider shows up as an active mod; it needs no
 * phase listeners of its own.
 */
public final class AdapterSEntrypoint implements IDeclarativeEntrypoint {

    @Override
    public void register(EntrypointRegistrar registrar) {
        FMLLog.log.info("{} {} is providing the 'scala' language adapter", Reference.MOD_NAME, Reference.VERSION);
    }
}
