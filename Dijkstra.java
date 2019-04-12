/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
/**
 *
 * @author newbi
 */
public class Dijkstra {
    private Grafo grafo;
   
    private  List<Vertex> vertexes;
    private  List<Edge> edges;
    private Set<Vertex> visitedNodes;
    private Set<Vertex> noVisitedNodes;
    private Map<Vertex, Vertex> parents;
    private Map<Vertex, Float> distance;
    private Vertex source;
    Hashtable<String,ArrayList<Edge>> ht;

    Dijkstra(Grafo grafo) throws CloneNotSupportedException
    {
        this.grafo = grafo;
        this.vertexes =  new ArrayList<Vertex>(grafo.getVertexes());
        this.edges =  new ArrayList<Edge>(grafo.getEdges());
        
    }
    
    
    public void execute(Vertex source)
    {
        visitedNodes = new HashSet<Vertex>();
        noVisitedNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Float>();
        parents = new HashMap<Vertex, Vertex>();
        ht = new Hashtable<String,ArrayList<Edge>>();
        this.source = source;
        distance.put(source, 0f);
        System.out.println("source:"+source.getName());
        noVisitedNodes.add(source);
        //System.out.println("noVisitedNodes.size()="+noVisitedNodes.size());
        while (noVisitedNodes.size() > 0)
        {
            Vertex node = getMinimum(noVisitedNodes);
//            System.out.println("minNode:"+node.getName());
            visitedNodes.add(node);
            noVisitedNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Vertex node)
    {
        List<Vertex> adjacentNodes = getNeighbors(node);
        for (Vertex target : adjacentNodes)
        {
            
//            System.out.println("getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)\n"+getShortestDistance(target) +">"+ getShortestDistance(node) +"+"+ getDistance(node, target));
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target))
            {
//                System.out.println(""+target.getName()+"="+getShortestDistance(node) +"+"+ getDistance(node, target));
                distance.put(target, getShortestDistance(node) + getDistance(node, target));
                parents.put(target, node);
                noVisitedNodes.add(target);
            }
         
            
        }
//        System.out.println("**********PATH "+node.getName()+"**********");
//            for(Vertex v:parents.keySet())
//            {
//                System.out.println(""+parents.get(v).getName()+"->"+v.getName());
//            }
            
            ArrayList<Edge> edgs = printPathPredecessors(node,0f,new ArrayList<Edge>());
            if(ht.containsKey(node.getName()))
            {
                if(ht.get(node.getName()).get(ht.get(node.getName()).size()-1).getVertex2().getAcumaltedWeight() > edgs.get(edgs.size()-1).getVertex2().getAcumaltedWeight())
                {
                    System.out.println("EXCHANGE Path:"+ht.get(node.getName()).get(ht.get(node.getName()).size()-1).getVertex2().getAcumaltedWeight()
                    +" by Path:"+edgs.get(edgs.size()-1).getVertex2().getAcumaltedWeight());
                    ht.replace(node.getName(), edgs);
                }
            }
            else
            {
                System.out.println("Insert "+node.getName());
                ht.put(node.getName(), edgs);
            }
//            System.out.println("**********END PATH**********");
        
    }
    private ArrayList<Edge> printPathPredecessors(Vertex node,float acumulado,ArrayList<Edge> vpath)
    {
        if(parents.get(node)!=null)
        {
            System.out.println(parents.get(node).getName()+"->"+node.getName());
            Vertex v1 = new Vertex(parents.get(node));
            Vertex v2 = new Vertex(node);
            v2.setAcumaltedWeight(acumulado+getDistance(node,parents.get(node)));
            Edge ed = new Edge(v1,v2,getDistance(node,parents.get(node)));
            vpath.add(ed);
            return printPathPredecessors(parents.get(node),acumulado+getDistance(node,parents.get(node)),vpath);
        }else
        {
            System.out.println(""+acumulado);
            return vpath;
        }
    }
    private float getDistance(Vertex node, Vertex target)
    {
        for (Edge edge : edges)
        {
            if (edge.getVertex1().getName().equals(node.getName()) && edge.getVertex2().getName().equals(target.getName())
                ||                    edge.getVertex2().getName().equals(node.getName()) && edge.getVertex1().getName().equals(target.getName()))
            {
                return edge.getWeight();
            }
        }
        return Float.NEGATIVE_INFINITY;
    }
    
    private List<Vertex> getNeighbors(Vertex node)
    {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges)
        {
            if (edge.getVertex1().getName().equals(node.getName()) && !isVisited(edge.getVertex2()) //v1,v2
                    && (!edge.getVertex2().getName().equals( (parents.get(edge.getVertex1())==null)?"": parents.get(edge.getVertex1()).getName())) )//this line removes parents
            {
            neighbors.add(edge.getVertex2());
            }
            else if(edge.getVertex2().getName().equals(node.getName()) && !isVisited(edge.getVertex1()) //v2,v1
                    && (!edge.getVertex1().getName().equals( (parents.get(edge.getVertex2())==null)?"": parents.get(edge.getVertex2()).getName())) )//this line removes parents
            {
                neighbors.add(edge.getVertex1());
            }
        }
        return neighbors;
    }
    
    private Vertex getMinimum(Set<Vertex> vertexes)
    {
        Vertex minimum = null;
        for (Vertex vertex : vertexes)
        {
            if (minimum == null)
            {
            minimum = vertex;
            }
            else
            {
                if (getShortestDistance(vertex) < getShortestDistance(minimum))
                {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }
    
    private boolean isVisited(Vertex vertex)
    {
        return visitedNodes.contains(vertex);
    }

    private float getShortestDistance(Vertex destination)
    {
        Float f = distance.get(destination);
        if (f == null)
        {
            return Float.MAX_VALUE;
        }
        else
        {
            return f;
        }
    }
  
    public void exportGraphviz() throws IOException, InterruptedException
    {
        String dot = "";
        dot+="graph "+grafo.getName()+"Dijkstra"+"{ \n";
        for(String s:ht.keySet())
        {
            if(ht.get(s).size()>0)
            {
                float ac=0f;
                System.out.println(""+s+"="+ht.get(s).get(ht.get(s).size()-1).getVertex2().getAcumaltedWeight()+" ");
                
                ArrayList<Edge> edgeslist = ht.get(s);
                for(int i = 0; i < ht.get(s).size();i++)
                {
                    Edge e = edgeslist.get(ht.get(s).size()-i-1);
                    ac+=e.getWeight();
                    System.out.println(" v"+e.getVertex1().getName()+"-["+e.getWeight()+"]-v"+e.getVertex2().getName()+"["+(ac)+"]");
                    if(e.getVertex1().getName().equals(source.getName()))
                        dot+="\n"+e.getVertex1().getName()+" [label=\""+e.getVertex1().getName()+"\"]";
                    else
                        dot+="\n"+s+""+e.getVertex1().getName()+" [label=\""+e.getVertex1().getName()+"\"]";
                    dot+="\n"+s+""+e.getVertex2().getName()+" [label=\""+e.getVertex2().getName()+"\"]";
                    
                    if(e.getVertex1().getName().equals(source.getName()))
                        dot+="\n"+e.getVertex1().getName()+" -- "+s+""+e.getVertex2().getName()+" [label=\""+e.getWeight()+"\"]";
                    else
                        dot+="\n"+s+e.getVertex1().getName()+" -- "+s+""+e.getVertex2().getName()+" [label=\""+e.getWeight()+"\"]";
                    
                    dot+="\n"+s+e.getVertex2().getName()+" -- "+s+e.getVertex2().getName()+" [label=\""+ac+"\",penwidth=\"0.0\",fontsize=8]";
                    
                }
                
            }
            
        }
        dot+="\n}";
        grafo.setName(grafo.getName()+"Dijkstra");
        grafo.writeDotFile(dot);
    }
}
