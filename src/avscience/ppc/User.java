package avscience.ppc;
import avscience.wba.*;
import java.io.Serializable;

public class User extends avscience.wba.User implements Serializable
{
    private String state ="";
    public String fractureCat="Shear Quality";
    public String hardnessScaling="linear";
    
    public User(){super();}
    
    public User(String name, String email, String last, String first, String phone, String prof, String affil, String share)
    {
    	super(name, email, last, first, phone, prof, affil, share);
    }
    
    public String dataString()
    {
    	setAttributes();
    	avscience.ppc.AvScienceDataObject ao = new avscience.ppc.AvScienceDataObject();
    	ao.attributes = this.attributes;
    	return ao.dataString();
    }

    public User(String data)
    {
    	this();
    	this.popFromString(data);
	}
	
	public void popFromString(String data)
	{
		avscience.ppc.AvScienceDataObject ao = new avscience.ppc.AvScienceDataObject();
    	ao.popFromString(data);
    	this.attributes = ao.attributes;
    	getAttributes();
	}
	
    public void setAttributes()
    {
    	super.setAttributes();
        if (state!=null) attributes.put("state", state);
        attributes.put("useSymbols", "true");
        if (fractureCat!=null) attributes.put("fractureCat", fractureCat);
        if (hardnessScaling!=null) attributes.put("hardnessScaling", hardnessScaling);
    }
    
    public void getAttributes()
	{
    	super.getAttributes();
        state = (String) attributes.get("state"); 
        fractureCat=(String)attributes.get("fractureCat");
        hardnessScaling = (String) attributes.get("hardnessScaling");
        if ( hardnessScaling==null)hardnessScaling="linear";
        if ( fractureCat==null )fractureCat="Fracture Character";
    }
     
     public String getState()
     {
     	return state;
     }
     
     public void setState(String state)
     {
     	this.state=state;
     }
     
     public void setFractureCategory(String cat)
     {
     	fractureCat=cat;
     }
     
     public String getKey()
     {
     	return "F";
     }
     
     public String getFirst()
     {
     	String s = super.getFirst();
     	if (s==null) s="";
     	return s;
     }
     
     public String getLast()
     {
     	String s = super.getLast();
     	if (s==null) s="";
     	return s;
     }
     
     public String getDepthUnits()
     {
     	String s = super.getDepthUnits();
     	if (s==null) s="";
     	return s;
     }
}    
