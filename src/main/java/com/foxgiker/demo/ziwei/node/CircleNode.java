package com.foxgiker.demo.ziwei.node;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class CircleNode<T> {


    private T data;

    @JsonIgnore
    private CircleNode prevNode;

    @JsonIgnore
    private CircleNode nextNode;

    public  CircleNode(T t){
        data = t;
    }

    public CircleNode(){};

    public T data(){ return data;};
    public T getData(){ return data;}

    public CircleNode  next(){
        return nextNode;
    }

    public  CircleNode prev(){return  prevNode;}

    void setPrev(CircleNode t){
        prevNode = t;
    }

    void setNext(CircleNode t){
        nextNode = t;
    }

    void setData(T t){
        data = t;
    }


    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if(obj instanceof CircleNode<?>){
            var n = (CircleNode) obj;
            return n.data().hashCode() == hashCode();
        }else{

            return obj.hashCode() == hashCode();
        }
    }
}
