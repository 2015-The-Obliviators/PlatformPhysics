/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformphysics;

//import java.awt.Point;

import java.awt.Rectangle;


/**
 *
 * @author kevin.lawrence
 */
public interface ConnectionListenerIntf {
    public void onUpdate(Rectangle rect);
//    public void onUpdate(ConnectionProviderIntf connectionProvider);
}
