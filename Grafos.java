/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author newbi
 */
public class Grafos {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     * @throws java.lang.CloneNotSupportedException
     */
    public static void main(String[] args) throws IOException, InterruptedException, CloneNotSupportedException {
        // TODO code application logic here
         // ***************************Geografico***************************/
        Geografico g = new Geografico(5,0.6,false);//vertexes, d, sameVertexEdge
        g.grafoNoDirigido();
        g.exportGraphVizND();
        ArrayList<Vertex> vtxsg = g.grafond.getVertexes();
        Dijkstra dg = new Dijkstra(g.grafond);
        System.out.println("*******execute**********");
        dg.execute(vtxsg.get(0));
        System.out.println("*******END execute**********");
        dg.exportGraphviz();
        
      //  ***************************Gilbert***************************/
     //   Gilbert gi = new Gilbert(5,0.6,false);//vertexes, d, sameVertexEdge
     //   gi.grafoNoDirigido();
     //   gi.exportGraphVizND();
     //   ArrayList<Vertex> vtxsgi = gi.grafond.getVertexes();
     //   Dijkstra dgi = new Dijkstra(gi.grafond);
     //   System.out.println("*******execute**********");
     //   dgi.execute(vtxsgi.get(0));
     //   System.out.println("*******END execute**********");
     //   dgi.exportGraphviz();
        
      
    }
    
}
