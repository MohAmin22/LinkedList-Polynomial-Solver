import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ILinkedList {
/**
* Inserts a specified element at the specified position in the list.
* @param index
* @param element
*/
public void add(int index, Object element);
/**
* Inserts the specified element at the end of the list.
* @param element
*/
public void add(Object element);
/**
* @param index
* @return the element at the specified position in this list.
*/
public Object get(int index);

/**
* Replaces the element at the specified position in this list with the
* specified element.
* @param index
* @param element
*/
public void set(int index, Object element);
/**
* Removes all of the elements from this list.
*/
public void clear();
/**
* @return true if this list contains no elements.
*/
public boolean isEmpty();
/**
* Removes the element at the specified position in this list.
* @param index
*/
public void remove(int index);
/**
* @return the number of elements in this list.
*/
public int size();
/**
* @param fromIndex
* @param toIndex
* @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
*/
public SingleLinkedList sublist(int fromIndex, int toIndex);
/**ILinkedList
* @param o
* @return true if this list contains an element with the same value as the specified element.
*/
public boolean contains(Object o);
}


public class SingleLinkedList implements ILinkedList {
    /* Implement your linked list class here*/

    class Snode {
        Object item;
        Snode next;
    }

    Snode head,tail;
    int size;

    public SingleLinkedList()
    {
        head = tail = null;
        size = 0;
    }
    public boolean isEmpty()
    {
        return (size == 0);
    }

    public void add(int index, Object element) throws ArrayIndexOutOfBoundsException
    {
        Snode current,node;
        node = new Snode();
        current = this.head;
        if(index == size) this.add(element);
        else if (index < 0 || index > size ){ 
            throw new ArrayIndexOutOfBoundsException("");
        }
        else if(index == 0){
            node.next =  head;
            node.item = element;
            head =  node;
    }
        else
        {   
            for(int i = 0 ; i < index - 1 ; i++){
                current = current.next;
            }
            node.item = element;
            node.next = current.next; //null pointer if index = 0
            current.next = node;
            size++;
            
        }
    }
    public void add(Object element)
    {
        Snode node = new Snode();
        node.item = element;
        if(this.isEmpty()){
            head = tail = node;
            node.next = null;
        }
        else
        {
            tail.next = node;
            tail = node;
            tail.next = null;
        }
        this.size++;
    }
    public Object get(int index) throws ArrayIndexOutOfBoundsException
    {   Snode Pointer = head;
        if(index >= 0 && index < size)
        {
        for(int i = 0 ; i < index ; i++){
            Pointer = Pointer.next;
        }
        Object item = Pointer.item;
        return item;
        }
        else
        {
            throw new ArrayIndexOutOfBoundsException("");
        }
    }
    public void set(int index, Object element) throws ArrayIndexOutOfBoundsException
    {   
        if(index >= 0 && index < size){

        Snode current = head;
        for(int i = 0 ; i < index ; i++){
            current = current.next;
        }
        current.item = element; 
        
        }else {
            throw new ArrayIndexOutOfBoundsException("");            
        }
    }
    public int size()
    {
        return size;
    }
    public void clear()
    {
        head = null;
        size = 0;
    }
   public void remove(int index){
    Snode prev,cur;
    prev = head;
    cur = head.next;
    if(index < 0 || index > size - 1 || size == 0) System.out.println("Error");
    else if (index == size - 1 ){
        for(int i = 1 ; i < index ; i++){
            prev = prev.next;
        }
        prev.next = null;
        tail = prev;
        size--;
        tostring(head);

    }else if( index == 0){
        head = cur;
        size --;
        tostring(head);
    }
    else 
    {
        for(int i = 0 ; i < index - 1  ; i++){
            cur = cur.next;
            prev = prev.next;
        }
        prev.next = cur.next;
        size--;
        tostring(head);
    }
   }
    public boolean contains(Object o)
    {  
         Snode curr = head;
         while(curr != null){
             if( curr.item == o ){
                return true;
             }
             curr = curr.next;
         }
         return false;
    }
    public SingleLinkedList sublist(int fromIndex, int toIndex) throws ArrayIndexOutOfBoundsException
    {
        if(size == 0)throw new ArrayIndexOutOfBoundsException("");
        else if(fromIndex >= 0 && fromIndex <=size - 1 && toIndex >= 0 && toIndex <=size - 1 && toIndex >= fromIndex){
            
            SingleLinkedList sub = new SingleLinkedList();
            
            for(int i = fromIndex; i <=toIndex; i++ ){
                sub.add(get(i));
            }
            return sub;
        }else throw new ArrayIndexOutOfBoundsException("");
    }

    public void tostring(Snode head){
        System.out.print("[");
        Snode ct = head;
        while(ct != null){
            if(ct.next != null) System.out.print(ct.item+", ");
            else System.out.print(ct.item);
            ct = ct.next;
        }
        System.out.print("]");
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
        Scanner input = new Scanner(System.in);
        String inp1 = input.nextLine();
        inp1 = inp1.replaceAll("\\[|\\]","");
    
        SingleLinkedList l1 = new SingleLinkedList();

        if(inp1.length() != 0){
            String[] inp2 = inp1.split(", ");
            for(int i = 0; i < inp2.length; i++){ l1.add(Integer.parseInt(inp2[i])); }
        }
        try {
            switch(input.nextLine()){
                case "add":
                    l1.add(input.nextInt());
                    l1.tostring(l1.head);
                break;
                case "addToIndex":
                    
                    l1.add(input.nextInt(), input.nextInt());
                     l1.tostring(l1.head);
                    
                   
                break;
                case "get":     
                        System.out.println(l1.get(input.nextInt()));              
                break;
                case "set":
                    
                        l1.set(input.nextInt(), input.nextInt());
                        l1.tostring(l1.head);
                break;
                case "clear":
                    l1.clear();
                    l1.tostring(l1.head);
                break;
                case "isEmpty":
                    if( l1.isEmpty() )System.out.println("True");
                    else System.out.println("False");
                break;
                case "remove":
                    l1.remove(input.nextInt());
                break;
                    case "sublist": 
                    SingleLinkedList l2 = l1.sublist(input.nextInt(), input.nextInt());   
                    l2.tostring(l2.head);;
                break;
                case "contains":
                    if( l1.contains(input.nextInt()) )System.out.println("True");
                    else System.out.println("False");
                break;
                case "size":
                    System.out.println(l1.size());
                break;
            }
        } catch (ArrayIndexOutOfBoundsException e) 
        {
                System.out.println("Error");
        }
    }
}