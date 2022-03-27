// import java.io.*;
import java.util.*;
// import java.text.*;
// import java.math.*;
// import java.util.regex.*;

import javax.management.RuntimeErrorException;


interface IPolynomialSolver {
    /**
    * Set polynomial terms (coefficients & exponents)
    * @param poly: name of the polynomial
    * @param terms: array of [coefficients][exponents]
    */
    public void setPolynomial(char poly, int[][] terms);
  
    /**
    * Print the polynomial in ordered human readable representation
    * @param poly: name of the polynomial
    * @return: polynomial in the form like 27x^2+x-1
    */
    public String print(char poly);
  
    /**
    * Clear the polynomial
    * @param poly: name of the polynomial
    */
      void clearPolynomial(char poly);
  
    /**
    * Evaluate the polynomial
    * @param poly: name of the polynomial
    * @param value: the polynomial constant value
    * @return the value of the polynomial
    */
    float evaluatePolynomial(char poly, float value);
  
    /**
    * Add two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial
    */
    int[][] add(char poly1, char poly2);
  
    /**
    * Subtract two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial*/
    int[][] subtract(char poly1, char poly2);
  
    /**
    * Multiply two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return: the result polynomial
    */
    int[][] multiply(char poly1, char poly2);
}


public class PolynomialSolver implements IPolynomialSolver{


  DoubleLinkedList A = new DoubleLinkedList();
  DoubleLinkedList B = new DoubleLinkedList();
  DoubleLinkedList C = new DoubleLinkedList();
  DoubleLinkedList R = new DoubleLinkedList();

  DoubleLinkedList I1 = new DoubleLinkedList();
  DoubleLinkedList I2 = new DoubleLinkedList();

/*****************************/
public void setPolynomial(char poly, int[][] terms) throws RuntimeException
{
  if(poly == 'A' && terms.length != 0){
    for(int i = 0;i < terms.length; i++){ A.add(terms[i][0], terms[i][1]); }
 }else if(poly == 'B' && terms.length != 0){
    for(int i = 0;i < terms.length; i++){ B.add(terms[i][0], terms[i][1]); }
 }else if(poly == 'C' && terms.length != 0){
    for(int i = 0;i < terms.length; i++){ C.add(terms[i][0], terms[i][1]); }
 }else {
  throw new RuntimeException();
 }

}

public String print(char poly) throws RuntimeException
{
  if(poly == 'A' && !A.isEmpty()){ return A.print(); }
  else if(poly == 'B' && !B.isEmpty()){ return B.print(); }
  else if(poly == 'C' && !C.isEmpty()){ return C.print(); }
  else{
    throw new RuntimeException();
  }
}


public void clearPolynomial(char poly){
  if(poly == 'A'){ A.clear(); System.out.println("[]"); }
  else if(poly == 'B'){ B.clear(); System.out.println("[]"); }
  else if(poly == 'C'){ C.clear(); System.out.println("[]"); }
}
//change void to 
public int[][] add(char poly1, char poly2) throws RuntimeException
{
  if(poly1 == 'A' && poly2 == 'B' || poly1 == 'B' && poly2 == 'A'){I1 = A;I2 = B;}
  if(poly1 == 'C' && poly2 == 'B' || poly1 == 'B' && poly2 == 'C'){I1 = C;I2 = B;}
  if(poly1 == 'A' && poly2 == 'C' || poly1 == 'C' && poly2 == 'A'){I1 = A;I2 = C;}
  if(!I1.isEmpty() && !I2.isEmpty()){
    
      
      
      int elem_size;
      if(I1.size() > I2.size()){ elem_size = I2.size(); }
      else elem_size = I2.size();
      int i,val;
      for(i = 0;i < elem_size;i++){
        val = (Integer)I1.get(i)+(Integer)I2.get(i);
        R.add(R.size(),val,i);
      }

      if(I1.size() > I2.size()){
        for(int j = i;j < I1.size(); j++){
          R.add(R.size(),I1.get(j),j);
        }
      }
      
      else if(I2.size() > I1.size()){
        for(int j = i;j < I2.size(); j++){
          R.add(R.size(),I2.get(j),j);
        }
      }
      int[][] res = new int[R.size()][2];
      for(int j = 0; j < R.size(); j++){
        res[j][1] = (Integer)R.get(j);
      }
      
      System.out.println(R.print());
      return res;
  }else {
    throw new RuntimeException();
  }
      
  
  
  
}
public int[][] subtract(char poly1, char poly2) throws RuntimeException
{
  if(poly1 == 'A' && poly2 == 'B' ){I1 = A;I2 = B;}
  if(poly1 == 'B' && poly2 == 'A' ){I1 = B;I2 = A;}
  if(poly1 == 'C' && poly2 == 'B' ){I1 = C;I2 = B;}
  if(poly1 == 'B' && poly2 == 'C' ){I1 = B;I2 = C;}
  if(poly1 == 'C' && poly2 == 'A' ){I1 = C;I2 = A;}
  if(poly1 == 'A' && poly2 == 'C' ){I1 = A;I2 = C;}
  
  if(!I1.isEmpty() && !I2.isEmpty()){

  
  
  int elem_size;
  if(I1.size() > I2.size()){ elem_size = I2.size(); }
  else elem_size = I2.size();
  int i,val;
  for(i = 0;i < elem_size;i++){
    val = (Integer)I1.get(i)-(Integer)I2.get(i);
    R.add(R.size(),val,i);
  }

  if(I1.size() > I2.size()){
    for(int j = i;j < I1.size(); j++){
      R.add(R.size(),I1.get(j),j);
    }
  }
  
  else if(I2.size() > I1.size()){
    for(int j = i;j < I2.size(); j++){
      val = -(Integer)I2.get(j);
      R.add(R.size(),val,j);
    }
  }
  int[][] res = new int[R.size()][2];
  for(int j = 0; j < R.size(); j++){
    res[j][1] = (Integer)R.get(j);
  }
  
  System.out.println(R.print());
  return res;  
  }else {
    throw new RuntimeException();
  }
  
}

public int[][] multiply(char poly1, char poly2) throws RuntimeException
{
  if(poly1 == 'A' && poly2 == 'B' || poly1 == 'B' && poly2 == 'A'){I1 = A;I2 = B;}
  if(poly1 == 'C' && poly2 == 'B' || poly1 == 'B' && poly2 == 'C'){I1 = C;I2 = B;}
  if(poly1 == 'A' && poly2 == 'C' || poly1 == 'C' && poly2 == 'A'){I1 = A;I2 = C;}

  if(!I1.isEmpty() && !I2.isEmpty()){
    int sizeR = I1.size() + I2.size() - 1;
    for(int i = 0; i < sizeR; i++){ R.add(0, i); }
    
    for(int i = 0 ; i < I1.size(); i++ ){
      for(int j = 0 ; j < I2.size(); j++){
        int value = (Integer)R.get(i + j) + (Integer)I1.get(i) * (Integer)I2.get(j);
        R.set(i + j, value);
      }
    }
    int[][] res = new int[R.size()][2];  
    for(int j = 0; j < R.size(); j++){
      res[j][1] = (Integer)R.get(j);
    }
    System.out.println(R.print());
    return res;
  }else{
    throw new RuntimeException();
  }
}

public float evaluatePolynomial(char poly, float value) throws RuntimeException
{
  if(poly == 'A')I1 = A;
  else if(poly == 'B')I1 = B;
  else if(poly == 'C')I1 = C;
  if( !I1.isEmpty() ){
    float result = 0;
    for(int i = 0; i < I1.size(); i++ ){
      result = result + ((Integer)I1.get(i) * (float)Math.pow(value, i));
    }
    return result;
  }else{
  throw new RuntimeException();
}
}



//x^3-9x^2+26x-24

/*****************************/
    public static void main(String[] args) {
      try{
        //DoubleLinkedList dll = new DoubleLinkedList();
      DoubleLinkedList inp = new DoubleLinkedList();
      Scanner in = new Scanner(System.in);
      PolynomialSolver p = new PolynomialSolver();
      
      try{
        while(true){
          inp.add(inp.size(), in.nextLine(),0);
      }
      } catch(Exception a){  }
        
        
        while(inp.size() != 0){
          String s = inp.get(0).toString();
          
          //case set:
          
          if(s.equals("set")){
            String inpa = inp.get(2).toString();
            inpa = inpa.replaceAll("\\[|\\]","");
            String[] sp_val = inpa.split(",");
            int[][] coffs = new int[sp_val.length][2];
            for(int i = 0; i < sp_val.length; i++){ 
              coffs[i][0] = Integer.parseInt(sp_val[sp_val.length-i-1]); 
              coffs[i][1] = i;
            }
            String a = inp.get(1).toString();
            p.setPolynomial(a.charAt(0), coffs);
            inp.remove(0);
            inp.remove(0);
            inp.remove(0);
          
          //case print:  

          }
          else if(s.equals("print")){
            String a = inp.get(1).toString();
            System.out.println(p.print(a.charAt(0)));
            inp.remove(0);inp.remove(0);
          
          //case add:

          }
          else if(s.equals("add")){
            p.add( inp.get(1).toString().charAt(0) , inp.get(2).toString().charAt(0));
            
            inp.remove(0);inp.remove(0);inp.remove(0);
          //case sub:
          }
          else if(s.equals("sub")){
            p.subtract(inp.get(1).toString().charAt(0), inp.get(2).toString().charAt(0));

            inp.remove(0);inp.remove(0);inp.remove(0);
          
          //case mult:
          
          } 
          else if(s.equals("mult")){
            p.multiply(inp.get(1).toString().charAt(0), inp.get(2).toString().charAt(0));

            inp.remove(0);inp.remove(0);inp.remove(0);
          //case clear:

          } 
          else if(s.equals("clear")){
            p.clearPolynomial(inp.get(1).toString().charAt(0));
            
            inp.remove(0);inp.remove(0);
          
          //case evaluate:
          
          }
           else if(s.equals("eval")){
            String tem = inp.get(2).toString();
            try{
              
              System.out.println((int)p.evaluatePolynomial(inp.get(1).toString().charAt(0), Integer.parseInt(tem) ));
            }catch(NumberFormatException e){
              System.out.println(p.evaluatePolynomial(inp.get(1).toString().charAt(0), Float.parseFloat(tem) ));
            }
            inp.remove(0);inp.remove(0);inp.remove(0);
          } else {
            throw new InputMismatchException();
          }
        }


      in.close();
      }catch(Exception e){ System.out.println("Error"); }
  }
}

/******************** */

class DoubleLinkedList{ 
  public DoubleLinkedList(){ header.setNext(tailer); }
  class DNode{
      private Object coff;
      private int exp;
      private DNode prev;
      private DNode next;
  
      DNode(){ prev = null; next = null; }
      DNode(Object coff ,DNode prev ,DNode next){ this.coff = coff; this.prev = prev; this.next = next; }
  
      public Object getcoff() { return coff; }
      public int getexp() { return exp; }
      public DNode getPrev() { return prev; }
      public DNode getNext() { return next; }
      public void setexp(int exp) { this.exp = exp; }
      public void setcoff(Object coff) { this.coff = coff; }
      public void setPrev(DNode prev) { this.prev = prev; }
      public void setNext(DNode next) { this.next = next; }
  }
  DNode header = new DNode(0,null,null);
  DNode tailer = new DNode(0,header,null);
  private int size = 0;
  boolean test = false;

  public void add(int ind, Object element, int exp){
      DNode newnode = new DNode();
      newnode.setcoff(element);
      newnode.setexp(exp);
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

  public void add(Object element, int exp){ add(size, element, exp); }
  
  public Object get(int index){
      DNode ct = header.getNext();
      if(index >= 0 && index <= size-1 ){
          while( index != 0 ){ index--; ct = ct.getNext(); }
          return ct.getcoff();
      }else return 0;
  }
  public int getexps(int index){
    DNode ct = header.getNext();
    if(index >= 0 && index <= size-1 ){
        while( index != 0 ){ index--; ct = ct.getNext(); }
        return ct.getexp();
    }else return 0;
}

  //

  public void set(int index, Object element){
      DNode ct = header.getNext();
      if(index >= 0 && index <= size-1 ){
          while( index != 0 ){ index--; ct = ct.getNext(); }
          ct.setcoff(element);
          test = true;
      }else { System.out.println("Error"); test = false; }
  }

  public void setexp(int index, int expo){
    DNode ct = header.getNext();
    if(index >= 0 && index <= size-1 ){
        while( index != 0 ){ index--; ct = ct.getNext(); }
        ct.setexp(expo);
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
      }
      else { System.out.println("Error"); }
  }

  public int size(){ return size; }


  public String print(){
    DNode ct = header.getNext();
    String out = "";
    while(ct != tailer){
      //used if coff != 0
      if((Integer)ct.getcoff() != 0 ){
        //case exp > 1
            if(ct.getexp() > 1 ){
              //coff != 1 or -1 
              // if((Integer)ct.getcoff() != 1 && (Integer)ct.getcoff() != -1){
              //       if((Integer)ct.getcoff() > 0 && ct.getNext() != tailer)  
              //         { out = "+" + ct.getcoff() + "x^" + Integer.toString(ct.getexp()) + out; }
              //       else   
              //         out = ct.getcoff() + "x^" + Integer.toString(ct.getexp()) + out;
              // }//coff = 1
              // else if((Integer)ct.getcoff() == 1){
              //       if((Integer)ct.getcoff() > 0 && ct.getNext() != tailer) 
              //         { out = "+" + "x^" + Integer.toString(ct.getexp()) + out; }
              //       else 
              //         out = "x^" + Integer.toString(ct.getexp()) + out;
              // }else if((Integer)ct.getcoff() == -1){
              //       out =  "-x^" + Integer.toString(ct.getexp()) + out;
              // }

              //coff != 1 
              if((Integer)ct.getcoff() != 1){
                if((Integer)ct.getcoff() > 0 && ct.getNext() != tailer)  
                  { out = "+" + ct.getcoff() + "x^" + Integer.toString(ct.getexp()) + out; }
                else   
                  out = ct.getcoff() + "x^" + Integer.toString(ct.getexp()) + out;
              }//coff = 1
              else {
                if((Integer)ct.getcoff() > 0 && ct.getNext() != tailer) 
                  { out = "+" + "x^" + Integer.toString(ct.getexp()) + out; }
                else 
                  out = "x^" + Integer.toString(ct.getexp()) + out;
              }
            }
        //case exp = 1
          else if(ct.getexp() == 1){
            //coff != 1
            if((Integer)ct.getcoff() != 1 && (Integer)ct.getcoff() != -1){
                  if((Integer)ct.getcoff() > 0 && ct.getNext() != tailer)  
                    { out = "+" + ct.getcoff() + "x" + out; }
                  else   
                    out = ct.getcoff() + "x" + out;
            }//coff = 1
            else if((Integer)ct.getcoff() == 1){
                  if((Integer)ct.getcoff() > 0 && ct.getNext() != tailer) 
                    { out = "+" + "x" + out; }
                  else 
                    out = "x" + out;
            }else if((Integer)ct.getcoff() == -1){
                  out = "-x" + out;
            }
          }
        //case exp = 0
          else{
            if((Integer)ct.getcoff() > 0 && ct.getNext() != tailer)
              out = "+" + ct.getcoff() + out;
            else
              out = ct.getcoff() + out;
          }
      }
        
      ct = ct.getNext();
    }
    if(out.charAt(0) == '+') out = out.substring(1);
    return out;
  }


  public void tstring(DNode head){
      System.out.print("[");
      DNode ct = head.getNext();
      while(ct != tailer){
          if(ct.getNext() != tailer) System.out.print(ct.getcoff()+", ");
          else System.out.print(ct.getcoff());
          ct = ct.getNext();
      }
      System.out.print("]");
  }
}