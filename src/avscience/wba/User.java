package avscience.wba;

public class User extends AvScienceDataObject
{
    private String name = "";
    private String email = "";
    private String last = "";
    private String first = "";
    private String phone = "";
    private String prof = "false";
    private String affil= "";
    private String share = "true";
    private String tempUnits = "C";
    private String depthUnits = "cm";
    private String elvUnits = "m";
    private String rhoUnits = "kg/cubic_m";
    private String measureFrom = "top";
    private String longType = "W";
    private String latType = "N";
    private String useSymbols="use grain type symbols";
    private String state ="MT";
    
    public User(){super();}
    
    public User(String data)
    {
    	this();
    	popFromString(data);
	}
    
    public User(String name, String email, String last, String first, String phone, String prof, String affil, String share)
    {
    	this();
        this.name = name;
        this.email = email;
        this.last = last;
        this.first = first;
        this.phone = phone;
        this.prof = prof;
        this.affil = affil;
        this.share = share;
    }
    
    public void setAttributes()
    {
    	if (share!=null) attributes.put("share", share);
    	if (affil!=null)attributes.put("affil", affil);
    	if (prof!=null)attributes.put("prof", prof);
    	if (name!=null) attributes.put("name", name);
    	if (email!=null)attributes.put("email", email);
    	if (last!=null)attributes.put("last", last);
    	if (first!=null) attributes.put("first", first);
    	if (phone!=null)attributes.put("phone", phone);
    	if (tempUnits!=null) attributes.put("tempUnits", tempUnits);
    	if (depthUnits!=null) attributes.put("depthUnits", depthUnits);
    	if (elvUnits!=null) attributes.put("elvUnits", elvUnits);
    	if (rhoUnits!=null)attributes.put("rhoUnits", rhoUnits);
    	if (measureFrom!=null) attributes.put("measureFrom", measureFrom);
    	if (latType!=null) attributes.put("latType", latType);
    	if (longType!=null) attributes.put("longType", longType);
        if (useSymbols!=null) attributes.put("useSymbols", useSymbols);
        if (state!=null) attributes.put("state", state);
    }
    
    public void getAttributes()
    {
    	share = (String) attributes.get("share");
    	affil = (String) attributes.get("affil");
    	prof = (String) attributes.get("prof");
    	name = (String) attributes.get("name");
    	email = (String) attributes.get("email");
    	last = (String) attributes.get("last");
    	first = (String) attributes.get("first");
    	phone = (String) attributes.get("phone");
    	tempUnits = (String) attributes.get("tempUnits");
    	depthUnits = (String) attributes.get("depthUnits");
    	elvUnits = (String) attributes.get("elvUnits");
    	rhoUnits = (String) attributes.get("rhoUnits");
    	measureFrom = (String) attributes.get("measureFrom");
    	latType = (String) attributes.get("latType");
    	longType = (String) attributes.get("longType");
        useSymbols = (String) attributes.get("useSymbols");
        state = (String) attributes.get("state");
     }
     
    public boolean getProf()
    { 
    	if ( prof==null ) return false;
    	if (prof.equals("true")) return true;
    	else return false;
    }
    
    public boolean getShare()
    { 
    	if ( prof==null ) return true;
    	if (share.equals("true")) return true;
    	else return false;
    }
    
    public String getAffil()
    { 
    	if ( affil==null ) affil="";
    	return affil;
    }
    
    public String getName() { return name; }
    
    public String getEmail() { return email; }
    
    public String getLast() { return last; }
    
    public String getFirst() { return first; }
    
    public String getPhone() { return phone ; }
    
    public String getDepthUnits() { return depthUnits; }
    
    public String getTempUnits() { return tempUnits; }
    
    public String getElvUnits() { return elvUnits; }
    
    public String getRhoUnits() { return rhoUnits; }
    
    public String getMeasureFrom(){ return measureFrom; }
   
    public String getLatType(){ return latType; }
    
    public String getLongType(){ return longType; }
    
    public String getUseSymbolsText(){ return useSymbols;}
    
    public String getState(){ return state; }
    
    public void setState(String state)
    {
    	this.state = state;
    }
    
    public void setProf(String prof)
    {
    	this.prof = prof;
    }
    
    public void setAffil(String affil)
    {
    	this.affil = affil;
    }
    
    public void setShare(String share)
    {
    	this.share = share;
    }
    
    public void setName(String name)
    {
        this.name =name;
    }
    
    public void setEmail(String email)
    {
        this.email =email;
    }
    
    public void setFirst(String first)
    {
        this.first =first;
    }
    
    public void setLast(String last)
    {
        this.last =last;
    }
    
    public void setPhone(String phone)
    {
        this.phone =phone;
    }
    
    
    public boolean getUseSymbols()
    { 
        if ( useSymbols==null ) useSymbols = "use grain type symbols";
        if (useSymbols.equals("use grain type symbols")) return true;
        else return false; 
    }
    
    public void setDepthUnits(String depthUnits)
    {
        this.depthUnits = depthUnits;
    }
    
    public void setTempUnits(String tempUnits)
    {
        this.tempUnits = tempUnits;
    }
    
    public void setElvUnits(String elvUnits)
    {
        this.elvUnits = elvUnits;
    }
    
    public void setRhoUnits(String rhoUnits)
    {
        this.rhoUnits = rhoUnits;
    }
    
    public void setMeasureFrom(String measureFrom)
    {
        this.measureFrom = measureFrom;
    }
    
    public void setLatType(String latType)
    {
        this.latType = latType;
    }
    
    public void setLongType(String longType)
    {
        this.longType = longType;
    }
    
    public void setUseSymbols(String useSymbols)
    {
        this.useSymbols = useSymbols;
    }
    
    public String getKey()
    {
        return new String("8");
    }   
  
}
