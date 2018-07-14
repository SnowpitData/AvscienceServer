package avscience.wba;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ public class Location extends AvScienceDataObject
/*     */ {
/*     */   private String name="";
/*     */   private String state="";
/*     */   private String range="";
/*     */   private String lat="";
/*     */   private String longitude="";
/*     */   private String elv="";
/*     */   private String ID="";
/*     */   private String ew="";
/*     */   private String ns="";
/*     */   public String type="";
/*     */   public String zone="";
/*     */   public String east="";
/*     */   public String north="";
/*     */ 
/*     */   public void setAttributes()
/*     */   {
               System.out.println("Location::setAttributes()");
              /// if (attributes == null ) attributes = new Hashtable();
/*  21 */     if (ew!=null) attributes.put("ew", this.ew);
/*  22 */     if (ns!=null) attributes.put("ns", this.ns);
/*  23 */     if (name!=null)attributes.put("name", this.name);
/*  24 */     if (state!=null) attributes.put("state", this.state);
/*  25 */     if (range!=null)attributes.put("range", this.range);
/*  26 */     if (lat!=null) attributes.put("lat", this.lat);
/*  27 */     if (longitude!=null) attributes.put("longitude", this.longitude);
/*  28 */     if (elv!=null)attributes.put("elv", this.elv);
/*  29 */     if (ID!=null)attributes.put("ID", this.ID);
/*  30 */     if (type != null) attributes.put("type", this.type);
/*  31 */     if (zone!=null) attributes.put("zone", this.zone);
/*  32 */     if (east!=null) attributes.put("east", this.east);
/*  33 */     if (north != null) attributes.put("north", this.north);
/*     */   }
/*     */ 
/*     */   public Location()
/*     */   {
/*   5 */     this.name = "";
/*   6 */     this.state = "";
/*   7 */     this.range = "";
/*   8 */     this.lat = "";
/*   9 */     this.longitude = "";
/*  10 */     this.elv = "";
/*  11 */     this.ID = "";
/*  12 */     this.ew = "W";
/*  13 */     this.ns = "N";
/*  14 */     this.type = "LATLON";
/*  15 */     this.zone = "17T";
/*  16 */     this.east = "0";
/*  17 */     this.north = "0";
/*     */   }
/*     */ 
/*     */   public Location(User user)
/*     */   {
/*  41 */     this.ew = user.getLongType();
/*  42 */     this.ns = user.getLatType();
/*  43 */    // if (!(user.coordType.equals("UTM"))) return; this.type = "UTM";
/*     */   }
/*     */ 
/*     */   public Location(String data)
/*     */   {
/*  49 */     popFromString(data);
/*     */   }
/*     */ 
/*     */   public Location(User user, String name, String state, String range, String lat, String longitude, String elv, String id)
/*     */   {
/*   5 */     this.name = "";
/*   6 */     this.state = "";
/*   7 */     this.range = "";
/*   8 */     this.lat = "";
/*   9 */     this.longitude = "";
/*  10 */     this.elv = "";
/*  11 */     this.ID = "";
/*  12 */     this.ew = "W";
/*  13 */     this.ns = "N";
/*  14 */     this.type = "LATLON";
/*  15 */     this.zone = "17T";
/*  16 */     this.east = "0";
/*  17 */     this.north = "0";
/*     */ 
/*  54 */     this.name = name;
/*  55 */     this.state = state;
/*  56 */     this.range = range;
/*  57 */     this.lat = lat;
/*  58 */     this.longitude = longitude;
/*  59 */     this.elv = elv;
/*  60 */     this.ID = id;
/*  61 */     this.ew = user.getLongType();
/*  62 */     this.ns = user.getLatType();
/*  63 */     this.type = "LATLON";
/*     */   }
/*     */ 
/*     */   public Location(User user, String name, String state, String range, String zone, String east, String north, String elv, String id)
/*     */   {
/*   5 */     this.name = "";
/*   6 */     this.state = "";
/*   7 */     this.range = "";
/*   8 */     this.lat = "";
/*   9 */     this.longitude = "";
/*  10 */     this.elv = "";
/*  11 */     this.ID = "";
/*  12 */     this.ew = "W";
/*  13 */     this.ns = "N";
/*  14 */     this.type = "LATLON";
/*  15 */     this.zone = "17T";
/*  16 */     this.east = "0";
/*  17 */     this.north = "0";
/*     */ 
/*  68 */     this.name = name;
/*  69 */     this.state = state;
/*  70 */     this.range = range;
/*  71 */     this.zone = zone;
/*  72 */     this.east = east;
/*  73 */     this.north = north;
/*  74 */     this.elv = elv;
/*  75 */     this.ID = id;
/*  76 */     this.type = "UTM";
/*     */   }
/*     */ 
/*     */   public void getAttributes()
/*     */   {
/*  82 */     this.ew = ((String)this.attributes.get("ew"));
/*  83 */     this.ns = ((String)this.attributes.get("ns"));
/*  84 */     this.name = ((String)this.attributes.get("name"));
/*  85 */     this.state = ((String)this.attributes.get("state"));
/*  86 */     this.lat = ((String)this.attributes.get("lat"));
/*  87 */     this.longitude = ((String)this.attributes.get("longitude"));
/*  88 */     this.elv = ((String)this.attributes.get("elv"));
/*  89 */     this.range = ((String)this.attributes.get("range"));
/*  90 */     this.ID = ((String)this.attributes.get("ID"));
/*  91 */     this.type = ((String)this.attributes.get("type"));
/*  92 */     Object ozone = this.attributes.get("zone");
/*  93 */     if (ozone != null) this.zone = ozone.toString();
/*     */     else this.zone = "";
/*  95 */     Object oeast = this.attributes.get("east");
/*  96 */     if (oeast != null) this.east = oeast.toString();
/*     */     else this.east = "";
/*  98 */     Object onorth = this.attributes.get("north");
/*  99 */     if (onorth != null) this.north = onorth.toString();
/*     */     else this.north = "";
/*     */   }
/*     */ 
/*     */   public String getLocName()
/*     */   {
/* 105 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 110 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public void setRange(String range)
/*     */   {
/* 115 */     this.range = range;
/*     */   }
/*     */ 
/*     */   public void setState(String state)
/*     */   {
/* 120 */     this.state = state;
/*     */   }
/*     */ 
/*     */   public void setLat(String lat)
/*     */   {
/* 125 */     this.lat = lat;
/*     */   }
/*     */ 
/*     */   public void setLongitude(String longitude)
/*     */   {
/* 130 */     this.longitude = longitude;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 135 */     return this.name;
/*     */   }
/*     */ 
/*     */   public String getState()
/*     */   {
/* 140 */     return this.state;
/*     */   }
/*     */ 
/*     */   public String getLat()
/*     */   {
/* 145 */     return this.lat;
/*     */   }
/*     */ 
/*     */   public String getLongitude()
/*     */   {
/* 150 */     return this.longitude;
/*     */   }
/*     */ 
/*     */   public String getElv()
/*     */   {
/* 155 */     return this.elv;
/*     */   }
/*     */ 
/*     */   public String getRange()
/*     */   {
/* 160 */     return this.range;
/*     */   }
/*     */ 
/*     */   public String getLongType()
/*     */   {
/* 165 */     return this.ew;
/*     */   }
/*     */ 
/*     */   public String getLatType()
/*     */   {
/* 170 */     return this.ns;
/*     */   }
/*     */ 
/*     */   public String getID()
/*     */   {
/* 175 */     return this.ID;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 180 */     StringBuffer buffer = new StringBuffer();
/* 181 */     buffer.append(this.name);
/* 182 */     buffer.append(", State/Prov: " + this.state);
/* 183 */     buffer.append(", Range: " + this.range);
/* 184 */     buffer.append(", Lat N: " + this.lat);
/* 185 */     buffer.append(", Long. W: " + this.longitude);
/* 186 */     buffer.append(", Elevation: " + this.elv + " ");
/* 187 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */   public String getKey()
/*     */   {
/* 192 */     return new String("4");
/*     */   }
/*     */ }
