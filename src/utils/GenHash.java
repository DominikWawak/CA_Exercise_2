package utils;

import models.Candidate;
import models.NonCandidate;
import models.Politician;

public class GenHash
{
    private static final int MAX_PROBES = 25;
    Node[] hashTable;

    public GenHash(int size){

        hashTable= new Node [size]; //Zeros (default) indicate empty slots

    }
    public int hashFunction(String key){

        return key.hashCode()%hashTable.length;
    }
    public double loadFactor(){

        int totalAdds=0;

        for(Node x : hashTable) if(x!=null) totalAdds++;

        return totalAdds/(double)hashTable.length;

    }
    void rehash(){

        System.out.println("Rehashing...");

        Node[] hashTable2=hashTable;

        hashTable=new Node [(int)(hashTable.length*1.5)];

        for(Node x : hashTable2) add(x);

    }
    public int add(Node item){
        // if(loadFactor()>=0.6) rehash();​

        //hashTable[hashFunction(item)]=item;

        int loc=hashFunction(item.getKey());

        if(hashTable[loc]==null) hashTable[loc]=item;

        else { //Collision -> quadratic probe

            int home=loc,probeCount=1; //Keep home loc, probeCount is "n"

            do{

                System.out.println("Slot "+loc+" full. Quadratic probe number "+probeCount+"...");

                loc=(home+probeCount*probeCount)%hashTable.length; //Loc becomes home+n^2 with wraparound

                probeCount++;

                if(hashTable[loc]==null) { hashTable[loc]=item; return loc; }

            }while(probeCount<MAX_PROBES); //QP not quaranteed to find a slot, so stop after MAX_PROBES

            System.out.println("Add failed. Max probe count exceeded!");
            //rehash();

            //add(item);

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

    //============================================
    public static void main(String[] args) {
        GenHash h = new GenHash(10);


//        System.out.println("Stored item 77 in "+h.add(77));

//        System.out.println("Stored item 54 in "+h.add(54));

//        System.out.println("Stored item 222 in "+h.add(222));

//        System.out.println("Stored item 44 in "+h.add(44));

//        System.out.println("Stored item 4499 in "+h.add(4499));

//        System.out.println("Stored item 99 in "+h.add(99));

//        System.out.println("Stored item 123 in "+h.add(123));

//        System.out.println("Stored item 502 in "+h.add(502));

//        h.displayHashTable();
        /*Scanner k=new Scanner(System.in);
        do{
            System.out.print("Enter value: ");
            int x=k.nextInt();
            if(x==-1) break;
            System.out.println("Stored item "+x+" in "+h.add(x));
        }while(true);         */
        Politician pol = new NonCandidate("jon", "222", "", "Wat", "sddsdsdsds");

        Node<Politician> pl = new Node<Politician>();
        pl.setContents(pol);
        pl.setKey(pol.getName());

        pl.setContents(pol);
        System.out.println(pl.getContents().toString());
        h.add(pl);

        System.out.println(h.getValue(h.hashFunction(pl.getKey())).getContents().toString());
        h.displayHashTable();
    }
}