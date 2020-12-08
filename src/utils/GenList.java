package utils;


import models.Candidate;
import models.Election;

public class GenList<G> {
    public  Node<G> head = null;

    public void addElement(G e){
        Node<G> n = new Node<G>();
        n.setContents(e);
        n.next=head;
        head=n;

    }

    public void removeElement(Node<G> toRemove, GenList<G> list) {
        Node<G> dummy = new Node<G>();
        Candidate dummy1 = new Candidate("n","n","n","n","n","n",1);
        dummy.setContents((G) dummy1);
        dummy.next = list.head;
        list.head = dummy;

        while(dummy.next!=null){
            if(dummy.next.getContents().equals(toRemove.getContents())){
                dummy.next = dummy.next.next;
            }
            else{
                dummy = dummy.next;
            }
        }
       list.head = list.head.next;

    }

    public int  getSize(){
        int counter = 0;
        for(Node x = head ; x!=null;x=x.next)
            counter+=1;

        return counter;
    }

    public Node<G> getAtIndex(int i){
        Node<G> stop = head;
        for(int j = 0; j< i;j++){
            stop = stop.next;
        }
        return stop;
    }
    public void setAt(int i, Node<G> node){
        G temp = getAtIndex(i).getContents();
        getAtIndex(i).setContents(node.getContents());
        node.setContents(temp);
    }
















}


