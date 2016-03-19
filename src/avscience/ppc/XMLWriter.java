/**
 * @(#)XMLWriter.java
 *
 *
 * @author 
 * @version 1.00 2009/6/22
 */

package avscience.ppc;

import java.util.*;
import java.io.*;
import avscience.wba.*;
import avscience.util.*;
import avscience.ppc.*;
import org.jdom.*;
import org.jdom.output.*;
import avscience.pc.Sorter;

public class XMLWriter 
{
	File file = new File("PitObs.xml");
        Document doc;
	public XMLWriter(){}
    public XMLWriter(File file) 
    {
    	this.file = file;
    }
    
    public Document getDocumentFromPit(PitObs pit)
    {
        avscience.ppc.PitObs tpit = new avscience.ppc.PitObs(pit.dataString());
  	tpit = Sorter.sortPit(tpit);
  		
	Element e = getElementFromObject(tpit);
	doc = new Document(e);
        return doc;
    }
    
    public void writePitToXML(avscience.ppc.PitObs pit)
  	{
  	//	avscience.ppc.PitObs pit = getPit(serial);
  		avscience.ppc.PitObs tpit = new avscience.ppc.PitObs(pit.dataString());
  		tpit = Sorter.sortPit(tpit);
  		
  		System.out.println("writePitToXML");
		Element e = getElementFromObject(tpit);
		doc = new Document(e);
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		try
		{
		//	File f = new File(filename);
			outputter.output(doc, new FileOutputStream(file));
		}
		catch(Exception ex)
		{
			System.out.println(ex.toString());
		}
  	}
  	
  	Element addProfileFromTable(avscience.util.Hashtable table, Element el)
  	{
  		//int i=0;
  		avscience.util.Enumeration e = table.keys();
  		StringBuffer buffer = new StringBuffer();
  		while (e.hasMoreElements())
  		{
  			Object o = e.nextElement();
  			buffer.append(o);
  			buffer.append("_");
  			Object oo = table.get(o);
  			buffer.append(oo);
  			buffer.append(";");
  			
  		}
  		String s = buffer.toString();
  		String ss = s.substring(0, s.length()-1);
  		Attribute a = new Attribute("profile", ss);
  		el.setAttribute(a);
  		return el;
  	}
  	
  	
  	
  	public Element getElementFromObject(avscience.wba.AvScienceDataObject oo)
	{
		System.out.println("getElementFromObject");
		oo.setAttributes();
		String name = avscience.ppc.AvScienceObjectTypes.getInstance().getName(oo.getKey());
		Element e = new Element(name);
		if ( oo instanceof PitObs )
		{
			PitObs pit = (PitObs) oo;
			Attribute a = new Attribute("activities", pit.getActivitiesString());
			e.setAttribute(a);
		}
		
		if ( oo instanceof TempProfile )
		{
			TempProfile tp = (TempProfile) oo;
			if (tp.hasPoints())
			{
				Attribute a = new Attribute("tempUnits", tp.getTempUnits());
				e.setAttribute(a);
				Attribute aa = new Attribute("depthUnits", tp.getDepthUnits());
				e.setAttribute(aa);
				avscience.util.Hashtable table = (avscience.util.Hashtable) tp.attributes.get("profile");
				addProfileFromTable(table, e);
			}
			return e;
		}
		////////////
		if ( oo instanceof DensityProfile )
		{
			DensityProfile tp = (DensityProfile) oo;
			if (tp.hasPoints())
			{
				Attribute a = new Attribute("rhoUnits", tp.getDensityUnits());
				e.setAttribute(a);
				Attribute aa = new Attribute("depthUnits", tp.getDepthUnits());
				e.setAttribute(aa);
				avscience.util.Hashtable table = (avscience.util.Hashtable) tp.attributes.get("profile");
				addProfileFromTable(table, e);
			}
			return e;
		}
		/////////////
	/**/
		avscience.util.Enumeration en = oo.attributes.keys();
		if ( oo instanceof PitObs ) 
		{
			PitObs pit = (PitObs)oo;
			en = pit.exportAttributes().keys();
		}
		while ( en.hasMoreElements())
		{
			Object att = en.nextElement();
			System.out.println("att: "+att.toString());
			Object o = oo.attributes.get(att);
			/*if (att.equals("activities"))
			{
				System.out.println("Getting activities...  ");
				java.util.Vector acts = (java.util.Vector) o;
				StringBuffer buffer = new StringBuffer();
				java.util.Enumeration ae = acts.elements();
				while (ae.hasMoreElements())
				{
					buffer.append(ae.toString());
					buffer.append(";");
				}
				Attribute a = new Attribute(att.toString(), buffer.toString());
				e.setAttribute(a);
			}*/
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
	}
	

  	
}