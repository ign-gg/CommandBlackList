package cbl;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.util.List;

public class CommandBlackList extends PluginBase implements Listener {

    Config c;

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig();
        c = getConfig();
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        List<String> cmds = c.getStringList("commands");
        if (cmds.contains(e.getMessage().toLowerCase()) && !e.getPlayer().isOp()) {
            e.setCancelled(true);
            sendMessage(e.getPlayer());
        }
    }

    public void sendMessage(Player p) {
        p.sendMessage(TextFormat.colorize('&', c.getString("message")));
    }
}
