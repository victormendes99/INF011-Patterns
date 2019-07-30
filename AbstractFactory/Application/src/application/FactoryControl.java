/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.ArrayList;
import java.util.List;
import pkginterface.ICore;
import pkginterface.ITireFactory;

/**
 *
 * @author victor
 */
public class FactoryControl {
    
    List<ITireFactory> loadedPluginsByType = new ArrayList<>();
    
    public boolean initialize(ICore core){
        
        PluginController pluginController = new PluginController();
        pluginController.initialize(core);
        castPluginsList(pluginController.getLoadedPluginsByType(ITireFactory.class));
        for(ITireFactory plugin: loadedPluginsByType){
            if(plugin != null)
                plugin.createTire("pneu").name();
        }
            
        return true;
    }
    
    private <T>List<T> castPluginsList(List<T> loadedPlugins){
        for(T plugin: loadedPlugins){
            loadedPluginsByType.add((ITireFactory)plugin);
        }
        return loadedPlugins;
    }
    
    private String getPluginName(ITireFactory plugin){
         return plugin.getClass().getName().split("\\.")[1];
    }
}
