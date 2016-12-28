package hw3;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Searcher {

	public static <T> int search(T needle, T[] haystack, int numThreads)
	{
		int result = -1;
        ArrayList<searchThread<T>> searchers = new ArrayList<searchThread<T>>();
        int numItemsPerThread = haystack.length / numThreads;
        int extraItems = haystack.length - numItemsPerThread*numThreads;
        for (int i=0, start=0; i<numThreads; ++i) {
            int numItems = (i<extraItems) ? (numItemsPerThread+1) : numItemsPerThread;
            searchers.add(new searchThread<T>(needle, haystack, start, start+numItems, false));
            start += numItems;
        }
        
        for (searchThread<T> searcher : searchers) 
            searcher.run();

        boolean getOut = false;
        while(!getOut)
        {
            for (searchThread<T> searcher : searchers)
                if(!searcher.isAlive())
                {
                	result = searcher.getResult();
                	for (searchThread<T> searcher2 : searchers)
                	{
                		searcher2.setisOtherFound(true);
                		searcher2.interrupt();
                	}
                	getOut = true;
                	break;
                }
        }

        return result;
	}
	
	public static void main(String[] args) throws InterruptedException
    {
 
        int numItems = 10000;
        Integer[] haystack = new Integer[numItems];
        int domainSize = 1000;
        for (int i=0; i<numItems; ++i) 
        	haystack[i] = (int)(Math.random() * domainSize);

        int needle = 10;

        int index  = Searcher.search(needle, haystack, 4);
        if(index != -1)
        	System.out.println("index: "+index+", value: "+haystack[index]);
        else
        	System.out.println("index: null"+", value: null");
    }
}
