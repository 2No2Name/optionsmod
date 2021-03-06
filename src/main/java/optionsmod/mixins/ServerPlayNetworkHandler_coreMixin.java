package optionsmod.mixins;

import optionsmod.OptionsmodServer;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandler_coreMixin {
    @Shadow
    public ServerPlayerEntity player;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onPlayerConnect(MinecraftServer server, ClientConnection client, ServerPlayerEntity player, CallbackInfo ci) {
        OptionsmodServer.onPlayerLoggedIn(player);
    }

    @Inject(method = "onDisconnected", at = @At("HEAD"))
    private void onPlayerDisconnect(Text reason, CallbackInfo ci) {
        OptionsmodServer.onPlayerLoggedOut(this.player);
    }
}
