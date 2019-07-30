/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirefactory;

import java.util.ArrayList;
import java.util.List;
import pkginterface.ICore;
import pkginterface.IPlugin;
import pkginterface.ITire;
import pkginterface.ITireFactory;

/**
 *
 * @author victor
 */
public class TireFactory implements IPlugin ,ITireFactory{
    
    private int qtdCreate;
    private static int count = 0;
    private List<ITire> ListTires = new ArrayList<ITire>();
    
    private static TireFactory tireFactory = null;
    
    private TireFactory(){}
    
    public static TireFactory getInstance(){
        if(tireFactory == null)
            tireFactory = new TireFactory();
        return tireFactory;
    }
    
    @Override
    public boolean initialize(ICore core) {
        return true;
    }

    @Override
    public ITire createTire(String string) {
        
        return new Tire();
    }

    @Override
    public void addPrototype(String string, int qtd) {
        qtdCreate = qtd;
    }

       
}
