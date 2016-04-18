/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grid;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JRootPane;

/**
 *
 * @author Samy
 */
public class ComponentLinker extends JComponent{
    private Map<JComponent, JComponent> linked;
    private Paint var;
    
    public ComponentLinker()
    {
        super ();
        linked = new HashMap<JComponent, JComponent> ();
    }
    public void link ( JComponent c1, JComponent c2 )
    {
        linked.put ( c1, c2 );
        repaint ();
    }
    public void unlink ( JComponent c1, JComponent c2 )
    {
        linked.remove(c1, c2);
        repaint();
    }

    /**
     *
     */
    public void setColor(Paint p){
        var=p;
    }
    @Override
    protected void paintComponent ( Graphics g )
    {
        Graphics2D g2d = ( Graphics2D ) g;
        g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        g2d.setPaint ( var );
        for ( JComponent c1 : linked.keySet () )
        {
            Point p1 = getRectCenter ( getBoundsInWindow ( c1 ) );
            Point p2 = getRectCenter ( getBoundsInWindow ( linked.get ( c1 ) ) );
            g2d.drawLine ( p1.x, p1.y, p2.x, p2.y );
        }
    }
    
    private Point getRectCenter ( Rectangle rect )
    {
        return new Point ( rect.x + rect.width / 2, rect.y + rect.height / 2 );
    }
    
    private Rectangle getBoundsInWindow ( Component component )
    {
        return getRelativeBounds ( component, getRootPaneAncestor ( component ) );
    }

    private Rectangle getRelativeBounds ( Component component, Component relativeTo )
    {
        return new Rectangle ( getRelativeLocation ( component, relativeTo ),
                component.getSize () );
    }
    private Point getRelativeLocation ( Component component, Component relativeTo )
    {
        Point los = component.getLocationOnScreen ();
        Point rt = relativeTo.getLocationOnScreen ();
        return new Point ( los.x - rt.x, los.y - rt.y );
    }
    private JRootPane getRootPaneAncestor ( Component c )
    {
        for ( Container p = c.getParent (); p != null; p = p.getParent () )
        {
            if ( p instanceof JRootPane )
            {
                return ( JRootPane ) p;
            }
        }
        return null;
    }
    @Override
    public boolean contains ( int x, int y )
    {
        return false;
    }
}