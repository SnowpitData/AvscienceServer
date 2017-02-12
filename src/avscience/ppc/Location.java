package avscience.ppc;

public class Location extends avscience.ppc.AvScienceDataObject
{
    private String name="";
    private String state="MT";
    private String range="";
    private String lat="0.0";
    private String longitude="0.0";
    private String elv="0";
    private String ID="";
    private String ew="W";
    private String ns="N";
    public String type="LATLON";
    public String zone="17T";
    public String east="";
    public String north="";
    
        public Location(){}
    
	public Location(String data) throws Exception
        {
            super(data);
	}
        
        public Location(avscience.ppc.User user)
        {
             this.ew = user.getLongType();
             this.ns = user.getLatType();
             if ((user.getCoordType().equals("UTM"))) this.type = "UTM";
             else this.type = "LATLON";
        }

        /// for Lat / Lon. 
       public Location(avscience.ppc.User user, String name, String state, String range, String lat, String longitude, String elv, String id)
       {
             this.ew = user.getLongType();
             this.ns = user.getLatType();
             this.name = name;
             this.state = state;
             this.range = range;
             this.lat = lat;
             this.longitude = longitude;
             this.elv = elv;
             this.ID=id;
             this.type = "LATLON";
               
        
         }
         /// for UTM coords
         public Location(avscience.ppc.User user, String name, String state, String range, String zone, String east, String north, String elv, String id)
         {
            this.name = name;
            this.state = state;
            this.range = range;
            this.elv = elv;
            this.ID = id;
            this.type = "UTM";
            this.zone = zone;
            this.east = east;
            this.north = north;
        }
	
        public void writeAttributes()
        { 
            System.out.println("Location:WriteAttributes");
            try
            {
                put("name", name);
                put("state", state);
                put("range", range);
                put("lat", lat);
                put("longitude", longitude);
                put("elv", elv);
                put("ID", ID);
                put("ew", ew);
                put("ns", ns);
                put("zone", zone);
                put("east", east);
                put("north", north);
                put("type", type);
            }
            catch(Exception e)
            {
                System.out.println(e.toString());
            }
        }
        
        public void popAttributes()
        {
            try
            {
                name = getString("name");
                state = getString("state");
                range = getString("range");
                lat = getString("lat");
                longitude = getString("longitude");
                elv = getString("elv");
                ID = getString("id");
                ew = getString("ew");
                ns = getString("ns");
                zone = getString("zone");
                east = getString("east");
                north = getString("north");
                type = getString("type");
            }
            catch(Exception e)
            {
                System.out.println(e.toString());
            }
        }
        
	public String getName()
	{
		return name;
	}
        
        public String getLocName()
        {
            return this.name;
        }

        public void setName(String name)
        {
            this.name = name;
        }
 
        public void setRange(String range)
        {
            this.range = range;
        }
 
        public void setState(String state)
        {
            this.state = state;
        }

        public void setLat(String lat)
        {
            this.lat = lat;
        }
 
        public void setLongitude(String longitude)
        {
            this.longitude = longitude;
        }

        public String getState()
        {
            return this.state;
        }
 
        public String getLat()
        {
            return this.lat;
        }

        public String getLongitude()
        {
            return this.longitude;
        }
 
        public String getElv()
        {
            return this.elv;
        }

        public String getRange()
        {
            return this.range;
        }
 
        public String getLongType()
        {
            return this.ew;
        }

        public String getLatType()
        {
            return this.ns;
        }
 
        public String getID()
        {
            return this.ID;
        }

        public String toString()
        {
            StringBuffer buffer = new StringBuffer();
            buffer.append(this.name);
            buffer.append(", State/Prov: " + this.state);
            buffer.append(", Range: " + this.range);
            buffer.append(", Lat N: " + this.lat);
            buffer.append(", Long. W: " + this.longitude);
            buffer.append(", Elevation: " + this.elv + " ");
           return buffer.toString();
   }
}