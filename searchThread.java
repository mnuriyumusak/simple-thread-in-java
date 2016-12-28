package hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class searchThread<T> extends Thread {
	
	private T needle;
    private T[] haystack;
    private int start, end;
    private int result = -1;
    private boolean isOtherFound;
    
    public searchThread(T needle, T[] haystack, int start, int end, boolean isOtherFound)
    {
        this.needle = needle;
        this.haystack = haystack;
        this.start = start;
        this.end = end;
        this.isOtherFound = isOtherFound;
    }
    
    public void setisOtherFound(boolean isOtherFound)
    {
    	this.isOtherFound = isOtherFound;
    }
    
    public int getResult()
    {
        return result;
    }
 
    // @override
    public void run() 
    {
        for (int i=start; i<end; ++i)
        {
        	if(!isOtherFound)
        	{
                if (haystack[i].equals(needle))
                {
                	result = i;
                	break;
                } 
        	}
        	else
        	{
        		break;
        	}
        		
        }
    }   
}
