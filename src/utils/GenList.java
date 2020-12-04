package utils;

public class GenList<G> {
    public  Node<G> head = null;

    public void addElement(G e){
        Node<G> n = new Node<G>();
        n.setContents(e);
        n.next=head;
        head=n;

    }

    public void removeElement(Node<G> toRemove, GenList<G> head){
        Node<G> previous = null;

            for (Node<G> i = head.head; i != null; i = i.next) {
                if (i.next != null && i.next.equals(toRemove)) {
                    i.next = i.next.next; //skip

                }
                if(i.equals(toRemove)){
                   toRemove=toRemove.next;
                }

            }
        }



}


