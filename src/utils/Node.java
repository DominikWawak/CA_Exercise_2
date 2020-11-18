package utils;

public class Node <E> {

    private E contents;
    private String key;

    public Node<E> next = null;

    public E getContents() {
        return contents;
    }


    public void setContents(E contents) {
        this.contents = contents;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
