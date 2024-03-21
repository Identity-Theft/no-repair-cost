package identitytheft.noxpanvils;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoXpAnvils implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("no_xp_anvils");

	@Override
	public void onInitialize() {
		LOGGER.info("Starting No XP Anvils!");
	}
}