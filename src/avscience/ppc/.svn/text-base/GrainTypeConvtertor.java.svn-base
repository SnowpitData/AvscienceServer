
 
package avscience.ppc;

import java.util.*;

public class GrainTypeConvtertor 
{
	Hashtable<String, String> types = new Hashtable<String, String>();
	Hashtable<String, String> lwc = new Hashtable<String, String>();
	
    public GrainTypeConvtertor() 
    {
    	types.put("Graupel", "PPgp");
    	types.put("Precipitation particles", "PP");
    	types.put("Decomposing fragmented particles","DFdc");
    	types.put("Highly broken particles", "DFbk");
    	types.put("Rounded grains", "RG");
    	types.put("Mixed forms rounded","RGmx");
    	types.put("Faceted crystals", "FCfa");
    	types.put("Small faceted cystals", "FCsf");
    	types.put("Mixed forms faceted", "FCmx");
    	types.put("Cup-shaped crystals/depth hoar", "DHcp");
    	types.put("Columns of depth hoar", "DHdh");
    	types.put("Wet Grains", "WG");
    	types.put("Clustered rounded grains", "WGcl");
    	types.put("Rounded poly-crystals", "WGmf");
    	types.put("Slush", "WGsl");
    	types.put("Surface hoar", "SHsh");
    	types.put("Ice layer", "IMil");
    	types.put("Surface deposits and crusts", "CR");
    	types.put("Rime", "CRrm");
    	types.put("Rain crust", "CRrc");
    	types.put("Sun crust", "CRsc");
    	types.put("Wind crust", "CRwc");
    	types.put("Melt-freeze crust", "CRmfc");
    	
    	lwc.put("Dry", "D");
    	lwc.put("Moist", "M");
    	lwc.put("Wet", "W");
    	lwc.put("Very Wet", "U");
    }
    
    public String getCAAMLType(String grainType)
    {
    	grainType=grainType.trim();
    	return types.get(grainType);
    }
    
    public String getLWC(String splwc)
    {
    	String s = lwc.get(splwc);
    	if (s==null) s="unknown";
    	return s;
    }
}