package cbl;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

public class CommandBlackList extends PluginBase implements Listener {

    private Config c;

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        c = getConfig();
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        String cmd = e.getMessage().toLowerCase().replaceAll("\\s+","");
        for (String str : c.getStringList("commands")) {
            if (cmd.startsWith(str) && !e.getPlayer().hasPermission("commandblacklist.ignore")) {
                e.setCancelled(true);
                sendMessage(e.getPlayer());
                return;
            }
        }
    }

    private void sendMessage(Player p) {
        p.sendMessage(TextFormat.colorize('&', c.getString("message")));
    }
}
