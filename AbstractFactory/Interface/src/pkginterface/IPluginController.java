/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkginterface;

import java.util.List;

/**
 *
 * @author victor
 */
public interface IPluginController {
    public boolean initialize(ICore core);
    public List<IPlugin> getLoadedPlugins();
    public <T>List<T> getLoadedPluginsByType(Class <T> type);
}
