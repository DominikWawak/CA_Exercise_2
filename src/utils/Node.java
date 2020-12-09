package utils;

public class Node <E> {

    private E contents;
    //private String key;
    public int k;

    public Node<E> next = null;

    public E getContents() {
        return contents;
    }


    public void setContents(E contents) {
        this.contents = contents;
    }


    public int getKey() {
        return k;
    }

    public void setKey(int k) {
        this.k = k;
    }


}
