package avscience.pc;

import java.util.*;

import java.io.*;
import avscience.wba.*;
import avscience.util.*;
import avscience.desktop.Logger;
import avscience.ppc.*;

public class SPDataStore implements java.io.Serializable
{
    private static final SPDataStore instance  = new SPDataStore();
    private java.util.Hashtable Pits = new java.util.Hashtable();
    private java.util.Hashtable PitIndex = new java.util.Hashtable();
    private java.util.Hashtable OccIndex = new java.util.Hashtable();
	private java.util.Hashtable Occs = new java.util.Hashtable();
	private java.util.Hashtable Users = new java.util.Hashtable();
	private java.util.Hashtable Locations = new java.util.Hashtable();
	String username="";
    public static SPDataStore getInstance()
	{
		return instance;
	}
	
  	private SPDataStore()
  	{
  		super();
  		//System.out.println("SPDataStore");
  		Properties props = System.getProperties();
    	username = (String) props.get("user.name");
  	}
  	
  /*	public void writePitToXML(avscience.ppc.PitObs pit)
  	{
  	//	avscience.ppc.PitObs pit = getPit(serial);
  		System.out.println("writePitToXML");
		Element e = getElementFromObject(pit);
		Document doc = new Document(e);
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		try
		{
			File f = new File("PitObs-"+pit.getSerial()+".xml");
			outputter.output(doc, new FileOutputStream(f));
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
  	}
  	
  	public Element getElementFromObject(avscience.wba.AvScienceDataObject oo)
	{
		System.out.println("getElementFromObject");
		oo.setAttributes();
		String name = avscience.ppc.AvScienceObjectTypes.getInstance().getName(oo.getKey());
		Element e = new Element(name);
		avscience.util.Enumeration en = oo.attributes.keys();
		while ( en.hasMoreElements())
		{
			Object att = en.nextElement();
			System.out.println("att: "+att.toString());
			Object o = oo.attributes.get(att);
			if (o instanceof String)
			{
				Attribute a = new Attribute(att.toString(), o.toString());
				e.setAttribute(a);
			}
			
			if  (o instanceof avscience.wba.AvScienceDataObject)
			{
				Element ell = getElementFromObject((avscience.wba.AvScienceDataObject)o);
				e.addContent(ell);
			}
			
			if ( o instanceof java.util.Vector )
			{
				java.util.Vector v = (java.util.Vector) o;
				Iterator it = v.iterator();
				while (it.hasNext())
				{
					Object ooo = it.next();
					if ( ooo instanceof avscience.wba.AvScienceDataObject)
					{
						Element elll = getElementFromObject((avscience.wba.AvScienceDataObject)ooo);
						e.addContent(elll);
					}
				}
				
			}		
		}
		return e;
	}*/
  	
  	public java.util.Vector getPits()
  	{
  		java.util.Enumeration e = Pits.keys();
  		java.util.Vector v = new java.util.Vector();
  		while (e.hasMoreElements())
  		{
  			String serial = (String) e.nextElement();
	  		avscience.ppc.PitObs pit = getPit(serial);
	  		v.add(pit);
	  	}
  		return v;
  	}
  	
  	public java.util.Enumeration getOccs()
  	{
  		java.util.Enumeration e = Occs.keys();
  		java.util.Vector v = new java.util.Vector();
  		while (e.hasMoreElements())
  		{
  			String serial = (String) e.nextElement();
	  		avscience.ppc.AvOccurence occ = getOcc(serial);
	  		v.add(occ);
	  	}
  		return v.elements();
  	}
  	
  	public void addPit(String data)
  	{
  		
  	//	System.out.println("addPit:: ");
  		if ((data!=null) && (data.trim().length()>2))
  		{
	  		avscience.ppc.PitObs pit = new avscience.ppc.PitObs(data);
	  		if ( pit!=null )
	  		{
		  	
		  		String serial = pit.getSerial();
		  		if ((serial==null) || (serial.trim().length()<1))
		  		{
		  			serial = getNewSerial();
		  			pit.setSerial(serial);
		  		}
		  	//	else removePit(serial);
		  		
		  		
		  		Pits.put(serial, pit.dataString());
		  	//	System.out.println("PC PIT added: "+pit.getName()+" serial: "+serial);
		  	}
	  	}
  	}
  	
  	/*public void addLocation(avscience.pc.Location l)
  	{
  		Locations.put(l.getName().trim()+" "+l.getID(), l);
  	}*/
  	
  	public void addLocation(String data)
  	{
  		avscience.wba.Location l = new avscience.wba.Location(data);
  		Locations.put(l.getName().trim(), l);
  	}
  	
  	public avscience.wba.Location getLocation(String name)
  	{
  		Object o = null;
  		if ((name!=null) && (name.trim().length()>0)) o = Locations.get(name);
  		avscience.wba.Location l = null;
  		if (o!=null) l = (avscience.wba.Location)  o;
  		if ( l==null ) l = new avscience.wba.Location();
  		return l;
  	}
  	
  	public void removeLocation(String name)
  	{
  		Locations.remove(name);
  	}
  	
    public void addOcc(String data)
  	{
  		if ((data!=null) && (data.trim().length()>2))
  		{
	  		avscience.ppc.AvOccurence occ = new avscience.ppc.AvOccurence(data);
	  		if (occ!=null)
	  		{
		  	//	System.out.println("addOcc: "+occ.getPitName());
		  		//occ = new CharacterCleaner().cleanStrings(occ);
		  		String serial = occ.getSerial();
		  		
		  		if ((serial==null) || (serial.trim().length()<1))
		  		{
		  			serial = getNewSerial();
		  			occ.setSerial(serial);
		  		}
		  	//	else removeOcc(serial);
		  		Occs.put(serial, occ.dataString());
		  	
		  	}
	  	}
  	}
  	
  	public avscience.ppc.PitObs getPitByName(String name)
  	{
  		if ((name==null) || (name.trim().length()<2)) return null;
  		System.out.println("getPitByName: "+name);
  		java.util.Vector v = getPits();
  		java.util.Enumeration e = v.elements();
  		while ( e.hasMoreElements())
  		{
  			avscience.ppc.PitObs pit = (avscience.ppc.PitObs) e.nextElement();
  			if (pit.getName().equals(name)) 
  			{
  			//	writePitToXML(pit);
  				return pit;
  			}
  		}
  		return null;
  	}
  	
  	public avscience.ppc.PitObs getPitByArchName(String name)
  	{
  		if ((name==null) || (name.trim().length()<2)) return null;
  		System.out.println("getPitByArchName: "+name);
  		java.util.Enumeration e = getPits().elements();
  		while ( e.hasMoreElements())
  		{
  			avscience.ppc.PitObs pit = (avscience.ppc.PitObs) e.nextElement();	
  			if (pit.getArchName().equals(name))
  			{
  				System.out.println("returning pit: serial "+pit.getSerial()+" name: "+name);
  				return pit;
  			}
  			
  		}
  		return null;
  	}
  	
  	public avscience.ppc.AvOccurence getOccByName(String name)
  	{
  		if ((name==null) || (name.trim().length()<2)) return null;
  		java.util.Enumeration e = getOccs();
  		while ( e.hasMoreElements())
  		{
  			avscience.ppc.AvOccurence occ = (avscience.ppc.AvOccurence) e.nextElement();
  			if (occ.getPitName().equals(name)) return occ;
  		}
  		return null;
  	}
  	
  	public avscience.ppc.PitObs getPit(String serial)
  	{
  		if (( serial==null ) || ( serial.trim().length()<2 )) return null;
  		System.out.println("getPit: serial: "+serial);
  		if ( serial!=null )
  		{
	  		String data = (String) Pits.get(serial);
	  		if ((data!=null) && (data.trim().length()>0))
	  		{
	  			avscience.ppc.PitObs pit = new avscience.ppc.PitObs(data);
	  		//	writePitToXML(pit);
	  			return pit;
	  		}
	  	}
	  	return null;
  	}
  
  	public avscience.ppc.AvOccurence getOcc(int idx)
  	{
  		String serial = getOccSerial(idx);
  		return getOcc(serial);
  	}
  	
  	public avscience.ppc.PitObs getPit(int index)
  	{
  		System.out.println("getPit idx: "+index);
  		String serial = getSerial(index);
  		return getPit(serial);
  	}
  	
  	public void deletePit(int index)
  	{
  		
  		String serial = getSerial(index);
  		removePit(serial);
  	}
  	
  	public void deleteOcc(int index)
  	{
  		String serial = getOccSerial(index);
  		removeOcc(serial);
  		removePit(serial);
  	}
  	
  	public boolean hasPit(String serial)
  	{
  		if (( serial==null ) || ( serial.trim().length()<2 )) return false;
  		
  		String data = (String) Pits.get(serial);
  		if ( data!=null ) return true;
  		else return false;
  	}
  	
  	public avscience.ppc.AvOccurence getOcc(String serial)
  	{
  		System.out.println("getOcc() "+serial);
  		if (( serial==null ) || ( serial.trim().length()<2 )) return null;
  		
  		String data=null;
  		
  		data = (String) Occs.get(serial);
  		//System.out.println("data: "+data);
  		avscience.ppc.AvOccurence pocc = null;
  		if (data==null)
  		{
  			System.out.println("data null.");
  			return null;
  		}
  		else
  		{
  			try
  			{
  				pocc = new avscience.ppc.AvOccurence(data);
  			}
  			catch(Throwable t){System.out.println(t.toString());}
  		}
  		return pocc;
  	}
  	
  	public void removePit(String serial)
  	{
  		if (( serial==null ) || ( serial.trim().length()<2 ));
  		
  		if ( serial!=null )
  		{
	  		System.out.println("removePit: "+serial);
	  		Object o = Pits.remove(serial);
	  		if (o!=null ) System.out.println(serial+" :removed");
	  		else System.out.println("failed to remove pit: "+serial);
	  	}
  	}
  	
  	public void removeOcc(String serial)
  	{
  		if (( serial==null ) || ( serial.trim().length()<2 )) return;
  		
  		if (serial!=null)
  		{
	  		Occs.remove(serial);
	  		Pits.remove(serial);
	  	}
  	}
  	
  	public void addUser(avscience.ppc.User user)
  	{
  		Users.put(user.getName(), user);
  	}
  	
  	public avscience.ppc.User getUser(String name)
  	{
  		avscience.ppc.User u = new avscience.ppc.User();
  		if ( ( name!=null ) && ( name.trim().length()>0) )
  		{
	  		u = (avscience.ppc.User) Users.get(name);
	  		if ( u==null ) u = new avscience.ppc.User();
	  	} 
  		return u;
    }
    
    public void removeUser(String name)
    {
    	if ( ( name!=null ) && ( name.trim().length()>0) )
  		{
	    	System.out.println("remove User: "+name);
	   
	    	Users.remove(name);	
    	}	
    	
    }
    
    public String[] getUserNames()
    {
    	String[] users = new String[Users.size()];
    	if (( Users!=null ) && ( Users.size()> 0 ))
    	{
    	//	users[0]=" ";
    		java.util.Enumeration e = Users.keys();
    		int i=0;
    		while ( e.hasMoreElements() )
    		{
    			users[i]=(String) e.nextElement();
    			i++;
    		}
    	}
    	return users;
    }
    
    public String[] getLocationNames()
    {
    	String[] locations = new String[Locations.size()+1];
    	if (( Locations!=null ) && ( Locations.size()> 0 ))
    	{
    		java.util.Enumeration e = Locations.keys();
    		locations[0]=" ";
    		int i=1;
    		while ( e.hasMoreElements() )
    		{
    			locations[i]=(String) e.nextElement();
    			i++;
    		}
    	}
    	return locations;
    }
    
    java.util.Vector getPits(boolean crowns)
    {
    	java.util.Enumeration e = Pits.keys();
  		java.util.Vector v = new java.util.Vector();
  		while (e.hasMoreElements())
  		{
  		    String serial = (String) e.nextElement();
  		    avscience.ppc.PitObs pit = getPit(serial);
  		    if (!crowns)
  		    {
  		    	if (!pit.getCrownObs()) v.add(pit);
  		    }
  		   
  		}
  		v=sortPitsByTime(v);
  		return v;
    }
    
    public java.util.Vector sortPitsByTime(java.util.Vector pits)
    {
    	boolean sorted = false;
        int length = pits.size();
        //Vector v = new Vector(length);
        int i = 0;
        avscience.ppc.PitObs pit;
        avscience.ppc.PitObs pitInc;

        if (length > 0)
        {
            while (!sorted)
            {
                sorted = true;
                for(i=0; i<length - 1; i++)
                {
                    pit = (avscience.ppc.PitObs) pits.elementAt(i);
                    long time = pit.getTimestamp();
                    pitInc = (avscience.ppc.PitObs) pits.elementAt(i+1);
                    long timeinc = pitInc.getTimestamp();
                  
                    if ( timeinc > time )
                    {
                            pits.setElementAt(pitInc, i);
                            pits.setElementAt(pit, i+1);
                            sorted = false;
                    }
                }
            }
        }
        return pits;
    }
  	
  	public String[] getPitNames(boolean crowns)
  	{
  		System.out.println("getPitNames()");
  		PitIndex = new java.util.Hashtable();
  		java.util.Vector pts = getPits(crowns);
  		String[] pits = new String[pts.size()+1];
  		pits[0]=" ";
  		System.out.println("# of pits: "+pts.size());
        if ((pts!=null) && (pts.size()>0))
        {
      		java.util.Enumeration e = pts.elements();
      		int i = 1;
      		while (e.hasMoreElements())
      		{
      		    //String serial = (String) e.nextElement();
      		   // avscience.ppc.PitObs pit = getPit(serial);
      		   	avscience.ppc.PitObs pit = (avscience.ppc.PitObs)e.nextElement();
      		   	String serial = pit.getSerial();
      		    pits[i]=pit.getName();
      		    System.out.println("Idx: "+i+" Pit Name: "+pits[i]+" Serial: "+serial);
      		    PitIndex.put(new Integer(i), serial);
      		    i++;
      		}
      	}
  		return pits;
  	}
  	
  	public String[] getPitNames()
  	{
  		System.out.println("getPitNames()");
  		PitIndex = new java.util.Hashtable();
  		java.util.Vector pts = getPits();
  		String[] pits = new String[pts.size()];
  		
        if ((pts!=null) && (pts.size()>0))
        {
      		java.util.Enumeration e = pts.elements();
      		int i = 0;
      		while (e.hasMoreElements())
      		{
      		    //String serial = (String) e.nextElement();
      		   // avscience.ppc.PitObs pit = getPit(serial);
      		   	avscience.ppc.PitObs pit = (avscience.ppc.PitObs)e.nextElement();
      		   	String serial = pit.getSerial();
      		    pits[i]=pit.getName();
      		    System.out.println("Idx: "+i+" Pit Name: "+pits[i]+" Serial: "+serial);
      		    PitIndex.put(new Integer(i), serial);
      		    i++;
      		}
      	}
  		return pits;
  	}
  	
  	void updatePitIndexes()
  	{
  		System.out.println("updatePitIndexes()");
  		PitIndex = new java.util.Hashtable();
  		java.util.Vector v = getPits();
  		java.util.Enumeration e = v.elements();
  		String[] pits = new String[v.size()];
        if ((pits!=null) && (pits.length>0))
        {
      		int i = 0;
      		while (e.hasMoreElements())
      		{
      		   	avscience.ppc.PitObs pit = (avscience.ppc.PitObs)e.nextElement();
      		   	String serial = pit.getSerial();
      		    pits[i]=pit.getName();
      		    System.out.println("Idx: "+i+" Pit Name: "+pits[i]+" Serial: "+serial);
      		    PitIndex.put(new Integer(i), serial);
      		    i++;
      		}
      	}
  	}
  	
  	public String[] getPitSerials()
  	{
  		getPitNames();
  		String[] serials = new String[PitIndex.size()];
  		if (PitIndex.size()>0)
  		{
  			java.util.Enumeration e = PitIndex.elements();
  			int i=0;
  			while (e.hasMoreElements())
  			{
  				String s = (String) e.nextElement();
  				serials[i]=s;
  				i++;
  			}
  		}
  		return serials;	
  	}
  	
  	public String[] getOccNames()
  	{
  		OccIndex = new java.util.Hashtable();
  		String[] occs = new String[Occs.size()+1];
  		occs[0]=" ";
        if ((Occs!=null) && (Occs.size()>0))
        {
      		java.util.Enumeration e = Occs.keys();
      		int i = 1;
      		while (e.hasMoreElements())
      		{
      		    String serial = (String) e.nextElement();
      		    avscience.ppc.AvOccurence occ = getOcc(serial);
      		    occs[i]=occ.getPitName();
      		    OccIndex.put(new Integer(i), serial);
      		    i++;
      		}
      	}
  		return occs;
  	}
  	
  	public String[] getOccSerials()
  	{
  		getOccNames();
  		String[] serials = new String[OccIndex.size()];
  		if (OccIndex.size()>0)
  		{
  			java.util.Enumeration e = OccIndex.elements();
  			int i=0;
  			while (e.hasMoreElements())
  			{
  				String s = (String) e.nextElement();
  				serials[i]=s;
  				i++;
  			}
  		}
  		return serials;	
  	}
  	
  	private String getOccSerial(int index)
  	{
  		Integer I = new Integer(index);
  		String serial = (String) OccIndex.get(I);
  		return serial;
  	}
  	
  	
  	private String getSerial(int index)
  	{
  		System.out.println("getSerial: idx: "+index);
  		Integer I = new Integer(index);
  		String serial = (String) PitIndex.get(I);
  		System.out.println("serial: "+serial);
  		return serial;
  	}
  	
    public String getNewSerial()
    {
    	System.out.println("getNewSerial()");
    	long time = System.currentTimeMillis();
    	String serial = username+time;
    	while ( Pits.containsKey(serial)) 
    	{
    		time = System.currentTimeMillis();
    		serial = username+time;
    	}
    	System.out.println("serial: "+serial);
    	return serial;
    }
}