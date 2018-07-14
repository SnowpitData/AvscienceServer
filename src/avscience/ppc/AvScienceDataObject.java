package avscience.ppc;

import avscience.wba.StringSerializable;
import avscience.wba.StringNumConvertor; 
import java.util.*;

public class AvScienceDataObject extends avscience.wba.AvScienceDataObject
{
	private static final avscience.ppc.AvScienceObjectTypes types = avscience.ppc.AvScienceObjectTypes.getInstance();
    private Vector delims = new Vector();
   
	public String dataString()
	{
		setAttributes();
		String data = null;
		StringBuffer buffer = new StringBuffer();
		Enumeration e = attributes.keys();
		
		while ( e.hasMoreElements() )
		{
			String s = (String) e.nextElement();
			Object o = attributes.get(s);
		
			 if ( o instanceof Integer ) 
			 {
			 	Integer I = (Integer) o;
			 	if ( I.intValue() < 255 ) addInteger(buffer, s, I);
			 	if ( I.intValue() >= 255 ) addBigInteger(buffer, s, I);
			 }
			 
			 if ( o instanceof java.lang.String ) addString(buffer, s, (String) o);
			 if ( o instanceof avscience.wba.AvScienceDataObject) addAvSerializable(buffer, s, (avscience.wba.AvScienceDataObject) o);
			 if ( o instanceof avscience.ppc.AvScienceDataObject) addAvSerializable(buffer, s, (avscience.ppc.AvScienceDataObject) o);
			 
			 if ( o instanceof avscience.util.Vector ) addVector(buffer, s, (Vector) o);
			 if ( o instanceof avscience.util.Hashtable ) addHashtable(buffer, s, (Hashtable) o);
			 if ( o instanceof java.util.Vector ) addVector(buffer, s, (java.util.Vector) o);
			 if ( o instanceof java.util.Hashtable ) addHashtable(buffer, s, (java.util.Hashtable) o);
			 o = null;
			 s = null;
		} 
		return buffer.toString();
	}
	
	private void addAvSerializable(StringBuffer buffer, String key, avscience.ppc.AvScienceDataObject avo)
	{
		buffer.append(key);
		buffer.append(keyDelim);
		buffer.append(AVSCIENCE_OBJ);
		buffer.append(avo.getObjectType());
	  	buffer.append(avo.dataString());
		buffer.append(aoDelim);
	}
	
	public void setAttributes(){System.out.print("avsci.ppc:getAtrributes()");}
	public void getAttributes(){}
	public String getKey(){return "-1";}
	
	private int extractAvSerializable(String key, String data, int start)
	{
		String type= data.substring(start, start + 1);
		int end = data.indexOf(aoDelim, start + 1);
		String s = data.substring(start + 1, end);
		
		StringSerializable ao = (StringSerializable) types.get(type);
		StringSerializable o = null;
		try
		{
			   o = (StringSerializable) ao.getClass().newInstance();
		}
		catch(Throwable t){}
		o.popFromString(s);
		attributes.put(key, o);
		return end + 1;
	}
	
	private int extractVector(String key, String data, int start)
	{
		java.util.Vector v = new java.util.Vector(0);
		int end = data.indexOf(countDelim, start);
		String s = data.substring(start, end);
		int n=0;
		String ss="";
	
		byte [] bytes = s.getBytes();
		byte b = bytes[0];
		n = (int) b;
		start = end + 1;

		v = new java.util.Vector(n);
		int i = 0;
		String type = null;
		String aType = null;
	
		for ( i = 0; i < n; i++ )
		{
			type = data.substring(start, start + 1);
			start = start + 1;
			if ( type.equals(STRING_OBJ))
			{
				end = data.indexOf(stringDelim, start);
				ss = data.substring(start, end);
				v.insertElementAt(ss, i);
				start = end + 1;
			}
			
			if ( type.equals(SMALL_INTEGER_OBJ))
			{
				ss = data.substring(start, start + 1);
				Integer I = new Integer(ss);
				v.insertElementAt(I, i);
				start = start + 1;
			}
			
			if ( type.equals(BIG_INTEGER_OBJ))
			{
				end = data.indexOf(intDelim, start);
				ss = data.substring(start, end);
		
				int ii = conv.getNumFromDisplayString(ss);
				avscience.pda.Integer I = new avscience.pda.Integer(ii);
				v.insertElementAt(I, i);
				start = end + 1;
			}
			
			if ( type.equals(AVSCIENCE_OBJ))
			{
				aType = data.substring(start, start + 1);
				start = start + 1;
				end = data.indexOf(aoDelim, start);
				ss = data.substring(start, end);
				StringSerializable ao = (StringSerializable) types.get(aType);
				StringSerializable o = null;
				try
				{
					 o = (StringSerializable) ao.getClass().newInstance();
				}
				catch(Throwable t){}
				o.popFromString(ss);
				v.insertElementAt(o, i);
			    
				start = end + 1;
				ao = null;
			}
		}
		attributes.put(key, v);
		return start;
	}
	
	private int extractHashtable(String key, String data, int start)
	{
		java.util.Hashtable ht = new java.util.Hashtable();
		String key1 = key + "1";
		String key2 = key + "2";
		
		int end = data.indexOf(keyDelim, start);
		start = extractVector(key1, data, end + 2);
		end = data.indexOf(keyDelim, start);
		start = extractVector(key2, data, end + 2);
		
		java.util.Vector v1 = (java.util.Vector) attributes.get(key1);
		java.util.Vector v2 = (java.util.Vector) attributes.get(key2);
		
		int size = v1.size();
		int i = 0;
		
		for ( i = 0; i < size; i++ )
		{
			ht.put(v1.elementAt(i), v2.elementAt(i));
		}
		attributes.put(key, ht);
		attributes.remove(key1);
		attributes.remove(key2);
		return start;	
	}

	private int extractString(String key, String data, int start)
	{
		if ( data==null ) return start;
		int end = data.indexOf(stringDelim, start);
		//System.out.println("extract STRING: "+ key + " start: "+start+" end: "+end);
		if ( end > start )
		{
			String extract = data.substring(start, end);
			if ( extract!=null) attributes.put(key, extract);
		}
              //  System.out.println("extract STRING:complete");
		return end + 1;
	}
	
	private int extractInteger(String key, String data, int start)
	{
		String s = data.substring(start, start + 1);
		Integer I = new Integer(s);
	
		attributes.put(key, I);
		return start + 2;
	}
	
	private int extractBigInt(String key, String data, int start)
	{
		int end = data.indexOf(intDelim, start);
		String extract = data.substring(start, end);
		int i = conv.getNumFromDisplayString(extract);
		avscience.pda.Integer I = new avscience.pda.Integer(i);
		attributes.put(key, I);
		return end + 1;
	}
	
	public void popFromString(String data)
	{
		System.out.println("popFromString()");
		if ( data==null ) return;
		int end = 0;
		int start = 0;
	
		String key = null;
		String objType = null;

        try
        {
	        while ( true )
	        {
	        	end = data.indexOf(keyDelim, start);
	            if ( end < 1 ) break;
	        	key = data.substring(start, end);
             
	        	objType = data.substring(end + 1, end + 2);
       			System.out.println("Object type: "+objType);
	        	
	        	//if (( objType!=null ) && ( objType.trim().length()>0))
	        	{
		        	if ( objType.equals(STRING_OBJ) ) start = extractString(key, data, end + 2);
		        	
			  		if ( objType.equals(VECTOR_OBJ) ) start = extractVector(key, data, end + 2);
			  		
		        	if ( objType.equals(AVSCIENCE_OBJ) ) start = extractAvSerializable(key, data, end + 2);
		        	
		        	if ( objType.equals(HASHTABLE_OBJ) ) start = extractHashtable(key, data, end + 2);
		        	
		        	if ( objType.equals(SMALL_INTEGER_OBJ) ) start = extractInteger(key, data, end + 2);
		        	
		        	if ( objType.equals(BIG_INTEGER_OBJ) ) start = extractBigInt(key, data, end + 2);
		        }
		        	
	    	}
                System.out.println("popFromString():finished");
	 	}
	 	catch(Throwable t){System.out.println(t.toString());}
	 		
	 	data = null;
	 	//System.out.println(".wba.AvscienceDataObject::getAttributes()");
	 	//getAttributes();	
	 
	}
	
	private String removeDelims(String data)
	{
		Enumeration e = delims.elements();
		
		while ( e.hasMoreElements())
		{
			String s = (String) e.nextElement();
			data = data.replace(s.charAt(0), replaceDelim.charAt(0));
		}
		
		return data;	
	}
	
	public String getObjectType()
	{
		return types.getKey(this);
	}
	
	
	private void addVector(StringBuffer buffer, String key, Vector v)
	{
		buffer.append(key);
		buffer.append(keyDelim);
		buffer.append(VECTOR_OBJ);
		int n = v.size();
        byte[] bytes = new byte[1];
        bytes[0] = (byte) n;
        String s = new String(bytes);
        buffer.append(s);
        buffer.append(countDelim);
        avscience.wba.AvScienceDataObject ao = null;
        avscience.ppc.AvScienceDataObject apo = null;
        int size = v.size();
        int ii = 0;
        
        for ( ii = 0; ii < size; ii++ ) 
        {
            Object o = v.elementAt(ii);
            if ( o  instanceof avscience.pda.Integer)                  
            {
            	avscience.pda.Integer I = (avscience.pda.Integer) o;
            	int i = I.intValue();
            	buffer.append(BIG_INTEGER_OBJ);
	            buffer.append(I.toString());
	            buffer.append(intDelim);
            }
            
            if ( o  instanceof java.lang.String)                  
            {
            	 buffer.append(STRING_OBJ);
            	 buffer.append((String) o);
            	 buffer.append(stringDelim);
            }
            
            if ( o  instanceof avscience.wba.AvScienceDataObject)  
            {
            	
            	ao = (avscience.wba.AvScienceDataObject) o;
            	buffer.append(AVSCIENCE_OBJ);
            	buffer.append(ao.getObjectType());
            	buffer.append( ((StringSerializable)ao).dataString());
            	buffer.append(aoDelim);
            }  
            
            if ( o  instanceof avscience.ppc.AvScienceDataObject)  
            {
            	apo = (avscience.ppc.AvScienceDataObject) o;
            	buffer.append(AVSCIENCE_OBJ);
            	buffer.append(ao.getObjectType());
            	buffer.append( ((StringSerializable)ao).dataString());
            	buffer.append(aoDelim);
            }  
        }
	}
	
	private void addVector(StringBuffer buffer, String key, avscience.util.Vector v)
	{
		buffer.append(key);
		buffer.append(keyDelim);
		buffer.append(VECTOR_OBJ);
		int n = v.size();
        byte[] bytes = new byte[1];
        bytes[0] = (byte) n;
        String s = new String(bytes);
        buffer.append(s);
        buffer.append(countDelim);
        avscience.wba.AvScienceDataObject ao = null;
        avscience.ppc.AvScienceDataObject apo = null;
        int size = v.size();
        int ii = 0;
        
        for ( ii = 0; ii < size; ii++ ) 
        {
            Object o = v.elementAt(ii);
            if ( o  instanceof avscience.pda.Integer)                  
            {
            	avscience.pda.Integer I = (avscience.pda.Integer) o;
            	int i = I.intValue();
            	buffer.append(BIG_INTEGER_OBJ);
	            buffer.append(I.toString());
	            buffer.append(intDelim);
            }
            
            if ( o  instanceof java.lang.String)                  
            {
            	 buffer.append(STRING_OBJ);
            	 buffer.append((String) o);
            	 buffer.append(stringDelim);
            }
            if ( o  instanceof avscience.wba.AvScienceDataObject)  
            {
            	
            	ao = (avscience.wba.AvScienceDataObject) o;
            	buffer.append(AVSCIENCE_OBJ);
            	buffer.append(ao.getObjectType());
            	buffer.append( ((StringSerializable)ao).dataString());
            	buffer.append(aoDelim);
            }  
            
            
        }
	}
	
	private void addHashtable(StringBuffer buffer, String key, Hashtable h)
	{
		buffer.append(key);
		buffer.append(keyDelim);
		buffer.append(HASHTABLE_OBJ);
		Vector keys = new Vector();
		Vector els = new Vector();
		
		Enumeration e = h.keys();
		
		while ( e.hasMoreElements() )
		{
			Object o = e.nextElement();
			keys.addElement(o);
			Object oe = h.get(o);
			els.addElement(oe);
		}
		
		String key1 = key + "1";
		String key2 = key + "2";
		
		addVector(buffer, key1, keys);
		addVector(buffer, key2, els);
	}
	
	/*private void addHashtable(StringBuffer buffer, String key, Hashtable h)
	{
		buffer.append(key);
		buffer.append(keyDelim);
		buffer.append(HASHTABLE_OBJ);
		Vector keys = new Vector();
		Vector els = new Vector();
		
		java.util.Enumeration e = h.keys();
		
		while ( e.hasMoreElements() )
		{
			Object o = e.nextElement();
			keys.addElement(o);
			Object oe = h.get(o);
			els.addElement(oe);
		}
		
		String key1 = key + "1";
		String key2 = key + "2";
		
		addVector(buffer, key1, keys);
		addVector(buffer, key2, els);
	}*/
	
	private void addString(StringBuffer buffer, String key, String s)
	{
		buffer.append(key);
		buffer.append(keyDelim);
		buffer.append(STRING_OBJ);
		s = removeDelims(s);
		buffer.append(s);
		buffer.append(stringDelim);
	}
	
	private void addAvSerializable(StringBuffer buffer, String key, avscience.wba.AvScienceDataObject avo)
	{
		buffer.append(key);
		buffer.append(keyDelim);
		buffer.append(AVSCIENCE_OBJ);
		buffer.append(avo.getObjectType());
	  	buffer.append(avo.dataString());
		buffer.append(aoDelim);
	}

	
	private void addInteger(StringBuffer buffer, String key, Integer I)
	{
		buffer.append(key);
		buffer.append(keyDelim);
		buffer.append(SMALL_INTEGER_OBJ);
		//String s = stringFromInteger(I);
                String s = I.toString(); 
		buffer.append(s);
	}
	
	private void addBigInteger(StringBuffer buffer, String key, Integer I)
	{
		buffer.append(key);
		buffer.append(keyDelim);
		buffer.append(BIG_INTEGER_OBJ);
		String s = I.toString();
		buffer.append(s);
		buffer.append(intDelim);
	}
	
	private static final String stringDelim = "`";
    private static final String intDelim = "&";
    private static final String countDelim = "$";
    private static final String keyDelim = "~";
    private static final String replaceDelim = " ";
    private static final String aoDelim = "|";
    public static final String pitDelim = "#";
    public static final String occDelim = "^";
    private static final String STRING_OBJ = "1";
    private static final String VECTOR_OBJ = "2";
    private static final String AVSCIENCE_OBJ = "3";
    private static final String HASHTABLE_OBJ = "4";
    private static final String SMALL_INTEGER_OBJ = "5";// LT 256
    private static final String BIG_INTEGER_OBJ = "6";//GT 255
    static final transient StringNumConvertor conv = StringNumConvertor.getInstance();
    

}