/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pkginterface.ICore;
import pkginterface.IPlugin;
import pkginterface.IPluginController;

/**
 *
 * @author victor
 */
public class PluginController implements IPluginController {

    private List<IPlugin> loadedPlugins = new ArrayList<IPlugin>();
    
    @Override
    public boolean initialize(ICore core) {
        File currentDir = new File("./plugins");
        String []plugins = currentDir.list();
        int i;
        URL[] jars = new URL[plugins.length];
        for (i = 0; i < plugins.length; i++)
            try {
                jars[i] = (new File("./plugins/" + plugins[i])).toURL();
            } catch (MalformedURLException ex) {
                Logger.getLogger(PluginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        URLClassLoader ulc = new URLClassLoader(jars);
        for (i = 0; i < plugins.length; i++) {
            String pluginName = plugins[i].split("\\.")[0];
            IPlugin plugin = null;
            try {            
                plugin = (IPlugin) Class.forName(pluginName.toLowerCase() + "." + pluginName, true, ulc).newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                try{
                    Method getInstance = Class.forName(pluginName.toLowerCase() + "." + pluginName, true, ulc).getMethod("getInstance");
                    plugin = (IPlugin) getInstance.invoke(pluginName.toLowerCase() + "." + pluginName);
                }catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    Logger.getLogger(PluginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            if (plugin != null)
                if (plugin.initialize(core))
                    loadedPlugins.add(plugin);
        
        }

        return true;
    }

    @Override
    public List<IPlugin> getLoadedPlugins() {
        return loadedPlugins;
    }

    @Override
    public <T> List<T> getLoadedPluginsByType(Class<T> type) {
        List<T> loadedPluginsByType = new ArrayList<>();
        for(IPlugin plugin : loadedPlugins){
            if(type.isInstance(plugin))
                loadedPluginsByType.add((T)plugin);
        }
    return loadedPluginsByType;
    }
    
}
