/*
Обработчик - цепочка объектов (экземпляров класса (instance))


*/

import java.util.Scanner;

abstract class Handler { 
	abstract public boolean handle(int request);
}

class DivisionChecker extends Handler {
	// поля
	private int value; // = 0
	private Handler next;
    // методы
    public DivisionChecker(int value, Handler _next) { // конструктор
		this.value = value;
		next = _next;
	}
	@Override
	public boolean handle(int request) {
		if (request % value == 0) {
		    System.out.println( String.format("%d%%%d==0 ==> %d isn't prime", request, value, request) );
			return true;
		} else {
		    System.out.print( String.format("%d%%%d!=0  ", request, value) );
			return next.handle(request);
		}
	}
	public String toString() { 
	    return String.format( "div %d; %s", value, next );  
	}
}

class DefaultHandler extends Handler {
    @Override
	public boolean handle(int request) {
		System.out.println( String.format(" ==> %d is prime", request) );
		return false;
	}
}
 

class DivisionCheckerTill extends Handler {
	// поля
	private int value; 
	private Handler next;
	// методы
    public DivisionCheckerTill(int maxPrimaDivider) {
        Handler queue = new DefaultHandler();
    	for (int i = 2; i < maxPrimaDivider; i++)  {
		    if (queue.handle(i) == false) {
			    queue = new DivisionChecker(i, queue);
		    }
	    }
	    value = maxPrimaDivider;
	    next = queue;
    }
    @Override
	public boolean handle(int request) {
		if (request % value == 0) {
		    System.out.println( String.format("%d%%%d==0 ==> %d isn't prime", request, value, request) );
			return true;
		} else {
		    System.out.print( String.format("%d%%%d!=0  ", request, value) );
			return next.handle(request);
		}
	}
	public String toString() { 
	    return String.format( "div %d; %s", value, next );  // this.value
	}
    
}

 
public class Main {
	public static void main(String[] args) {
		System.out.println("Hello World");
		Handler handl = new DivisionChecker(2, null);
		Handler queue = new DefaultHandler();
    	for (int i = 2; i < 30; i++)  {
			System.out.print( "check "+i+" :\t "+queue+"\n\t" );
		    if (queue.handle(i) == false) {
			    queue = new DivisionChecker(i, queue);
		    }
	    }
	    
	    
	  
	    System.out.println( "\n\n\n" );
	    DivisionCheckerTill handler = new DivisionCheckerTill(37); 
	    System.out.println( handler );
	    Scanner scan = new Scanner(System.in); 
	    int num = scan.nextInt();
	    // процедурный стиль вызова функции-метода
	    queue.handle( num ); 
	    num = scan.nextInt();
	    // процедурный стиль вызова функции-метода
	    queue.handle( num ); 
	    
	}
}



