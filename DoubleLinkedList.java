import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ILinkedList {

public void add(int index, Object element);

public void add(Object element);

public Object get(int index);

public void set(int index, Object element);

public void clear();

public boolean isEmpty();

public void remove(int index);

public int size();

public DoubleLinkedList sublist(int fromIndex, int toIndex);

public boolean contains(Object o);
}


public class DoubleLinkedList implements ILinkedList {
    
    public DoubleLinkedList(){ header.setNext(tailer); }
    class DNode{
        private Object s;
        private DNode prev;
        private DNode next;
    
        DNode(){ prev = null; next = null; }
        DNode(Object val ,DNode prev ,DNode next){ s = val; this.prev = prev; this.next = next; }
    
        public Object getS() { return s; }
        public DNode getPrev() { return prev; }
        public DNode getNext() { return next; }
        public void setS(Object s) { this.s = s; }
        public void setPrev(DNode prev) { this.prev = prev; }
        public void setNext(DNode next) { this.next = next; }
    }
    DNode header = new DNode(0,null,null);
    DNode tailer = new DNode(0,header,null);
    private int size = 0;
    boolean test = false;


    /*************************************************************************/
    public void add(int ind, Object element){
        DNode newnode = new DNode();
        newnode.setS(element);
        DNode ct = header;
        if(ind >=0  && ind <= size ){
            while( ind != 0 ){ ind--; ct = ct.getNext(); }
            newnode.setNext(ct.getNext());
            ct.getNext().setPrev(newnode);
            ct.setNext(newnode);
            newnode.setPrev(ct);
            size++;
            test = true;
        }else { System.out.println("Error"); test = false; }
    }

    public void add(Object element){ add(size, element); }
    
    public Object get(int index){
        DNode ct = header.getNext();
        if(index >= 0 && index <= size-1 ){
            while( index != 0 ){ index--; ct = ct.getNext(); }
            return ct.getS();
        }else return "Error";
    }

    //

    public void set(int index, Object element){
        DNode ct = header.getNext();
        if(index >= 0 && index <= size-1 ){
            while( index != 0 ){ index--; ct = ct.getNext(); }
            ct.setS(element);
            test = true;
        }else { System.out.println("Error"); test = false; }
    }

    //okk

    public void clear(){
        if(size == 0)return;
        else {
            DNode ct = header.getNext();
            while(ct != tailer){
                DNode temp = ct.getPrev();
                temp.setNext(null);
                ct.setPrev(null);
                ct = ct .getNext();
            }
            header.setNext(tailer);tailer.setPrev(header);
        }
    }

    //okk

    public boolean isEmpty(){ return (size == 0); }

    //okkk

    public void remove(int ind){
        DNode ct = header.getNext();
        if(size == 0)System.out.println("Error");
        else if(ind >= 0 && ind<=size - 1){
            while( ind != 0 ){ ind--; ct = ct.getNext(); }
            ct.getPrev().setNext(ct.getNext());
            ct.getNext().setPrev(ct.getPrev());
            ct.setPrev(null);ct.setNext(null);
            size--;
            tostring(header);
        }
        else { System.out.println("Error"); }
    }

    //okkk

    public int size(){ return size; }

    //okk

    public boolean contains(Object o){
        DNode ct = header.getNext();
        while(ct != tailer){
            if(ct.getS() == o)return true;
            ct = ct.getNext();
        }
        return false;
    }


    //okk
    public void tostring(DNode head){
        System.out.print("[");
        DNode ct = head.getNext();
        while(ct != tailer){
            if(ct.getNext() != tailer) System.out.print(ct.getS()+", ");
            else System.out.print(ct.getS());
            ct = ct.getNext();
        }
        System.out.print("]");
    }
    
    
    public DoubleLinkedList sublist(int fromIndex, int toIndex)throws RuntimeException
    {
    if(size == 0)throw new RuntimeException();
    else if(fromIndex >= 0 && fromIndex <=size - 1 && toIndex >= 0 && toIndex <=size - 1 && toIndex >= fromIndex){
        // System.out.print("[");
        // DNode ct = header.getNext();
        // int ind = 0;
        // while(ind != fromIndex){ ind++; ct = ct.getNext(); }
        // while(ind != toIndex+1){
        //     if(ind != toIndex) System.out.print(ct.getS()+", ");
        //     else System.out.print(ct.getS());
        //     ind++;
        //     ct = ct.getNext();
        // }
        // System.out.print("]");


        DoubleLinkedList sub = new DoubleLinkedList();
        for(int i = fromIndex;i <= toIndex; i++){
            sub.add(get(i));
        }
        return sub;

    }else   throw new RuntimeException();
        
    }

    


    /*****************************************************/
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String inp1 = input.nextLine();
        inp1 = inp1.replaceAll("\\[|\\]","");
        
        
        DoubleLinkedList dll = new DoubleLinkedList();

        if(inp1.length() != 0){
            String[] inp2 = inp1.split(", ");
            for(int i = 0; i < inp2.length; i++){ dll.add(Integer.parseInt(inp2[i])); }
        }


        try{
            switch(input.nextLine()){
                case "add":
                    dll.add(input.nextInt());
                    dll.tostring(dll.header);
                break;
                case "addToIndex":
                    dll.add(input.nextInt(), input.nextInt());
                    if(dll.test == true) dll.tostring(dll.header);
                break;
                case "get":
                    System.out.println(dll.get(input.nextInt()));
                break;
                case "set":
                    dll.set(input.nextInt(), input.nextInt());
                    if(dll.test == true) dll.tostring(dll.header);
    
                break;
                case "clear":
                    dll.clear();
                    dll.tostring(dll.header);
                break;
                case "isEmpty":
                    if( dll.isEmpty() )System.out.println("True");
                    else System.out.println("False");
                break;
                case "remove":
                    dll.remove(input.nextInt());
                break;
                case "sublist":
                    DoubleLinkedList dl = dll.sublist(input.nextInt(), input.nextInt());
                    dl.tostring(dl.header);
                break;
                case "contains":
                    if( dll.contains(input.nextInt()) )System.out.println("True");
                    else System.out.println("False");
                break;
                case "size":
                    System.out.println(dll.size());
                break;
            }
        }catch(Exception e){ System.out.println("Error"); }
        
    }
}