package com.foxgiker.demo.ziwei.node;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.LinkedList;

public class CircleLinkedList<T extends CircleNode > implements Serializable {

    private static final long serialVersionUID = 1L;

    private LinkedList<T> list = new LinkedList<T>();



    public LinkedList<T> getList(){
        return list;
    }

    public static CircleLinkedList of(CircleNode[] nodes){
        CircleLinkedList list = new CircleLinkedList();

        for(CircleNode node : nodes){
            list.addLast(node);
        }
        return list;
    }

    public T get(Integer i){
        return (T) list.get(i);
    }

    public T getByData(Object obj){
        CircleNode node = new CircleNode();
        for (CircleNode nd: list){
            if(nd.equals(obj)){
                node = nd;
                break;
            }
        }
        return (T) node;
    };

    public void addFirst(T t){
        if(list.isEmpty()){
            list.addFirst(t);
            t.setNext(t);
            t.setPrev(t);
        }else{
            if(list.size()==1){
                ((T)list.getFirst()).setPrev(t);
                ((T)list.getFirst()).setNext(t);

                t.setPrev((T)list.getFirst());
                t.setNext((T)list.getFirst());
            }else{
                ((T)list.getFirst()).setPrev(t);
                ((T)list.getLast()).setNext(t);

                t.setPrev((T)list.getLast());
                t.setNext((T)list.getFirst());
            }
            list.addFirst(t);
        }
    }

    public void addLast(T t){
        if(list.isEmpty()){
            list.addFirst(t);
            t.setNext(t);
            t.setPrev(t);
        }else{
            if(list.size()==1){
                ((T)list.getFirst()).setPrev(t);
                ((T)list.getFirst()).setNext(t);

                t.setPrev((T)list.getFirst());
                t.setNext((T)list.getFirst());
            }else{

                ((T)list.getFirst()).setPrev(t);
                ((T)list.getLast()).setNext(t);

                t.setPrev((T)list.getLast());
                t.setNext((T)list.getFirst());
            }
            list.addLast(t);
        }
    }

    @JsonIgnore
    public T getFirst(){
        return (T)list.getFirst();
    }

    @JsonIgnore
    public T getLast(){
        return (T) list.getLast();
    }
}
