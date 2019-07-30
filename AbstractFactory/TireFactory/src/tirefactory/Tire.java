/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tirefactory;

import pkginterface.ITire;

/**
 *
 * @author victor
 */
public class Tire implements ITire{
    
    @Override
    public String name(){
        System.out.println("Pneu novoooooo");
        return "Pneu novo";
    }
}
