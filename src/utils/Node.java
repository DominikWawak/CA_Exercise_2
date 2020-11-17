package utils;

public class Node <E> {

    private E contents;

    public Node<E> next = null;

    public E getContents() {
        return contents;
    }

    public void setContents(E contents) {
        this.contents = contents;
    }
}
