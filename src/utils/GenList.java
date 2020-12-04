package utils;

import javafx.scene.Camera;
import models.Candidate;

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















        /*

        if (head.head != null) {
            for (Node<G> i = head.head; i != null; i = i.next) {
                System.out.println(i.getContents().toString());
                if (i.getContents().equals(toRemove)) {
                    toRemove = null;
                    break;
                }
                if (i.next.getContents().equals(toRemove)) {
                    i.next = (i.next.next != null) ? i.next.next: null; //skip
                    break;

                }


            }
        }*/



}


