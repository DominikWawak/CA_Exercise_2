package utils;

public class GenList<G> {
    public  Node<G> head = null;

    public void addElement(G e){
        Node<G> n = new Node<G>();
        n.setContents(e);
        n.next=head;
        head=n;

    }
}
