/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhocg;

/**
 *
 * @author pedro
 */
public class Ponto2D {
    
    public double coord[];
    
    public Ponto2D(int x, int y) {
        coord = new double[2];
        coord[0] = x;
        coord[1] = y;
    }
    
    public double getX() { return coord[0]; }
    public double getY() { return coord[1]; }
    
    public boolean equals(Ponto2D other) {
        return coord[0] == other.coord[0] && coord[1] == other.coord[1];
    }
    
    
    
}
