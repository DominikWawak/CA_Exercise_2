package utils;

import models.Candidate;
import models.NonCandidate;
import models.Politician;

public class GenHash<E>
{
    private static final int MAX_PROBES = 20;
    public Node<E>[] hashTable;

    public GenHash(int size){

        hashTable= (Node<E>[]) new Node [size]; //Zeros (default) indicate empty slots

    }
    public int hashFunction(String key){

        return Math.abs(key.hashCode())%hashTable.length;

    }
    public int hashFunction(int key){

        return Math.abs(key)%hashTable.length;
    }
    public double loadFactor(){

        int totalAdds=0;

        for(Node x : hashTable) if(x!=null) totalAdds++;

        return totalAdds/(double)hashTable.length;

    }
    public void rehash(){

        System.out.println("Rehashing...");

        Node[] hashTable2=hashTable;

        hashTable=new Node [(int)(hashTable.length*1.5)];

        for(Node x : hashTable2) {
            if(x!=null && x.getContents() != "empty")
                add(x);
        }

    }
    public int add(Node item){
        if(loadFactor()>=0.6)
            rehash();

        //hashTable[hashFunction(item)]=item;

        int loc=hashFunction((item.getKey()));

        if(hashTable[loc]==null)
            hashTable[loc]=item;

        else { //Collision -> quadratic probe

            int home=loc,probeCount=1; //Keep home loc, probeCount is "n"

            do{

                System.out.println("Slot "+loc+" full. Quadratic probe number "+probeCount+"...");

                loc=(home+probeCount*probeCount)%hashTable.length; //Loc becomes home+n^2 with wraparound
                //System.out.println(item.getKey());
                item.setKey(loc);
                //System.out.println(item.getKey());

                probeCount++;

                if(hashTable[loc]==null) { hashTable[loc]=item; return loc; }

            }while(probeCount<MAX_PROBES); //QP not quaranteed to find a slot, so stop after MAX_PROBES

            System.out.println("Add failed. Max probe count exceeded!");
            rehash();

            add(item);

            return -1; //Add failed

        }

        return loc;

    }
    public void displayHashTable(){

        System.out.println("\nHash Table (Load Factor: "+loadFactor()+")\n====================");

        for(int i=0;i<hashTable.length;i++)

            System.out.println("Slot "+i+": "+hashTable[i]+(hashTable[i]==null || hashFunction(hashTable[i].getContents().toString())==i ? "" : " *"));

    }

    public Node getValue(int key){

       return  hashTable[key];
    }

    public void remove(int key){

        hashTable[key].setContents((E) "empty");

    }

    public GenList<E> makeList(){
        GenList<E> toList = new GenList<>();
        for(Node x : hashTable) {
            if(x!=null && x.getContents() != "empty")
                toList.addElement((E) x.getContents());
        }
        return toList;

    }



}