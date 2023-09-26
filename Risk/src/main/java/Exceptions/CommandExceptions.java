package Exceptions;

public class CommandExceptions extends Exception
	{  	
	    public CommandExceptions (String p_str)  
	    {    
	        super(p_str);  
	    }  
	}  
	     
class Exception1  
	{   
	    static void validate (String p_command) throws CommandExceptions{    
	      if(p_command == ""){  
	    	  throw new CommandExceptions("Enter a valid command!");    
	      }  
	      else {   
	        System.out.println("Continue...");   
	        }   
	     } 
}
