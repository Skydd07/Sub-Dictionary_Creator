import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class SubDictionaryCreator {
	
	/**
	 * 
	 * @param pw File on which the printing is to be done
	 * @param arr a list of words
	 * @param c char all the words from list whose fist character is c are to be found
	 * 
	 * This method finds all the words from the list whose first letter is c and prints them to fileoutput stream object
	 */
	public static void printWords(PrintWriter pw,ArrayList<String> arr, char c ) {
		pw.println("");
		char r;
		pw.println(c);
		pw.println("==");
		for(String t: arr) {
			r=t.charAt(0);
			if(r==c) 
				pw.println(t);
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keyboard=new Scanner(System.in);
		String fi;
		String word = null;
		System.out.println("Welcome to SubDictionary Creator");
		System.out.println("");
		System.out.print("Enter the name of file: ");
		fi=keyboard.next();
		
		//initialising dict arraylist to store words of input file
		ArrayList<String> dict=new ArrayList<String>(200);
		// initialising dictsort arraylist to store lexicographically sorted words
		ArrayList<String> dictsort=new ArrayList<String>(200);
		// initialising scanner and printwriter
		Scanner sc = null;
		PrintWriter pw=null;
		
		//opening the input file to scan
		try {
		  sc=new Scanner(new FileInputStream(fi));
		}
		catch(FileNotFoundException e){
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println("Program will terminate");
			System.exit(0);
		}
		
		// opening the output file to print
		try {
			pw=new PrintWriter(new FileOutputStream("SubDictionary.txt"));
		}
		catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		// storing the desired words in the dict arraylist until the end of file
		while(sc.hasNext()) {
			word=sc.next();
			if(word.endsWith("?")||word.endsWith(":")||word.endsWith(",")||word.endsWith(";")||word.endsWith("!")||word.endsWith("."))
			{word=word.substring(0,(word.length()-1));
			  word=word.toUpperCase();
			    
			  if(word.endsWith("'s")||word.endsWith("'m")||word.endsWith("'S")||word.endsWith("'M"))
				{word=word.substring(0,word.length()-2);
				  word=word.toUpperCase();}
			  
			  if(word.contains("0")||word.contains("1")||word.contains("2")||word.contains("3")||word.contains("4")||word.contains("5")||word.contains("6")||word.contains("7")||word.contains("8")||word.contains("9"))
			     continue;
		  
			  dict.add(word);
			  continue;
			 }
			else if(word.endsWith("’s")||word.endsWith("’m")||word.endsWith("’S")||word.endsWith("’M"))
			{word=word.substring(0,word.length()-2);
			  word=word.toUpperCase();
			  dict.add(word);
			  continue;
			}
			else if(word.contains("0")||word.contains("1")||word.contains("2")||word.contains("3")||word.contains("4")||word.contains("5")||word.contains("6")||word.contains("7")||word.contains("8")||word.contains("9"))
			 { 
				if(word.endsWith("?")||word.endsWith(":")||word.endsWith(",")||word.endsWith(";")||word.endsWith("!")||word.endsWith("."))
				{word=word.substring(0,(word.length()-1));
				  word=word.toUpperCase();
				  dict.add(word);      
				  
				continue;}
				continue;
			 }
			else if(word.equalsIgnoreCase("a")||word.equalsIgnoreCase("i"))
			 {	 word=word.toUpperCase();
			      dict.add(word);
			      continue;
              }
			else if(word.length()==1) {
		    	 if(word.equalsIgnoreCase("a")||word.equalsIgnoreCase("i"))
			    	{word=word.toUpperCase();
			     	  dict.add(word);
			    	}
		    	 else continue;
		    	 continue;
		     }

			 word=word.toUpperCase();
			 dict.add(word);
		     continue;
			}
	
	//converting the arraylist to array to perform sorting
	String[] dictn=new String[dict.size()];
	dict.toArray(dictn);
	
	//sorting
	for(int a=0;a<dictn.length-1;a++) {
		for(int b=a+1;b<dictn.length;b++) {
			if((dictn[a].compareTo(dictn[b]))>0) {
				String temp=dictn[a];
				dictn[a]=dictn[b];
				dictn[b]=temp;
			}	
		}
	}
	
	//removing duplicate elements
	for(String s: dictn) {
		if(!(dictsort.contains(s))) {
				dictsort.add(s);
	  }}
	
	
	//initializing and creating an array of alphabets
	char[] alpha = new char[26];
	char x='A';
	for(int i = 0; i < 26; i++)
		    alpha[i] = x++;
				
    pw.println("The given file produced this sub-dictionary which contains "+dictsort.size()+" words");
	
    //printWords method called for every alphabet i.e every element in the array of alphabets
    for(char t: alpha) 
		printWords(pw, dictsort, t);
    
    
	System.out.println("");
    System.out.println("Sub-Dictionary has been created. It contains "+dictsort.size()+" words.");
	//closed the scanner and printwriter
    pw.close();
	sc.close();
	keyboard.close();
		
		
		
		
		
	}
}
	
