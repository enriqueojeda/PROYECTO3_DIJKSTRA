/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.util.ArrayList;

/**
 *
 * @author newbi
 */
public class Edge {
   private Vertex vertex1;
   private Vertex vertex2;
   private ArrayList<String> properties;//graphviz properties
   private float weight;
   public Edge(){}
   
   public Edge(Edge e) throws CloneNotSupportedException
   {
       this.vertex1 = e.getVertex1();
       this.vertex2 = e.getVertex2();
       this.properties = new ArrayList<String>(e.properties);
       this.weight = e.weight;
   }
   
   public Edge(Vertex v1, Vertex v2,float w)
   {
       this.vertex1 = v1;
       this.vertex2 = v2;
       this.weight = w;
   }
   
   public Edge(Vertex vertex1,Vertex vertex2)
   {
       this.vertex1 = vertex1;
       this.vertex2 = vertex2;
       properties = new ArrayList<String>();
   }
   
   public void ExchangeVertex()
   {
       Vertex tmp = vertex1;
       vertex1=vertex2;
       vertex2=tmp;
   }
    /**
     * @return the vertex1
     */
    public Vertex getVertex1() {
        return vertex1;
    }

    /**
     * @param vertex1 the vertex1 to set
     */
    public void setVertex1(Vertex vertex1) {
        this.vertex1 = vertex1;
    }

    /**
     * @return the vertex2
     */
    public Vertex getVertex2() {
        return vertex2;
    }

    /**
     * @param vertex2 the vertex2 to set
     */
    public void setVertex2(Vertex vertex2) {
        this.vertex2 = vertex2;
    }

    /**
     * @return the properties
     */
    public ArrayList<String> getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(ArrayList<String> properties) {
        this.properties = properties;
    }
    
    /**
     * 
     * @param property 
     * add property verifying if does not exists
     */
    public void addProperty(String property)
    {
        if(!properties.contains(property))
            properties.add(property);
    }

    /**
     * @return the weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }
}
