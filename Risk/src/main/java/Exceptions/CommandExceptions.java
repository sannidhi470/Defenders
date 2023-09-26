package Exceptions;

public class CommandExceptions extends Exception
	{  	
	    public CommandExceptions (String str)  
	    {    
	        super(str);  
	    }  
	}  
	     
class Exception1  
	{   
	    static void validate (String command) throws CommandExceptions{    
	      if(command == ""){  
	    	  throw new CommandExceptions("Enter a valid command!");    
	      }  
	      else {   
	        System.out.println("Continue...");   
	        }   
	     } 
}
