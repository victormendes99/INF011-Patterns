/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import pkginterface.ICore;


/**
 *
 * @author victor
 */
public class Core implements ICore{
   
    
    public Core() {
        FactoryControl factoryControl = new FactoryControl();
        factoryControl.initialize(this);
    }
    
    @Override
    public boolean initialize() {
        return true;
    }
    
}
