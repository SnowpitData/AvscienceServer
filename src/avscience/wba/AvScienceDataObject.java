/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package avscience.wba;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;


public abstract class AvScienceDataObject
  implements StringSerializable
{
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
  private static final String SMALL_INTEGER_OBJ = "5";
  private static final String BIG_INTEGER_OBJ = "6";
  private static final AvScienceObjectTypes types = AvScienceObjectTypes.getInstance();
  
  static final transient StringNumConvertor conv = StringNumConvertor.getInstance();
  

  private Vector delims = new Vector();
  
  public Hashtable attributes = new Hashtable();
  public AvScienceDataObject()
  {
      buildDelimList();
  }
  
  private void buildDelimList()
  {
    delims.addElement("$");
    delims.addElement("~");
    delims.addElement("`");
    delims.addElement("|");
    delims.addElement("&");
    delims.addElement(" ");
    delims.addElement("#");
    delims.addElement("^");
  }
  
  public String dataString()
  {
    setAttributes();
    Object localObject1 = null;
    
    StringBuffer localStringBuffer = new StringBuffer();
   
    Enumeration localEnumeration = attributes.keys();
    
    while (localEnumeration.hasMoreElements())
    {
      String str = (String)localEnumeration.nextElement();
      Object localObject2 = attributes.get(str);
      
      if ((localObject2 instanceof Integer))
      {
        Integer localInteger = (Integer)localObject2;
        if (localInteger.intValue() < 255) addInteger(localStringBuffer, str, localInteger);
        if (localInteger.intValue() >= 255) { addBigInteger(localStringBuffer, str, localInteger);
        }
      }
      if ((localObject2 instanceof String)) addString(localStringBuffer, str, (String)localObject2);
      if ((localObject2 instanceof AvScienceDataObject)) addAvSerializable(localStringBuffer, str, (AvScienceDataObject)localObject2);
      if ((localObject2 instanceof Vector)) addVector(localStringBuffer, str, (Vector)localObject2);
      if ((localObject2 instanceof Hashtable)) addHashtable(localStringBuffer, str, (Hashtable)localObject2);
      localObject2 = null;
      str = null;
    }
    
    return localStringBuffer.toString();
  }
  
  public void popFromString(String paramString)
  {
    int i = 0;
    int j = 0;
    
    String str1 = null;
    String str2 = null;
    
    try
    {
      for (;;)
      {
        i = paramString.indexOf("~", j);
        

        if (i < 1) break;
        str1 = paramString.substring(j, i);
        
        str2 = paramString.substring(i + 1, i + 2);
        
        if (str2.equals("1")) { j = extractString(str1, paramString, i + 2);
        }
        if (str2.equals("2")) { j = extractVector(str1, paramString, i + 2);
        }
        if (str2.equals("3")) { j = extractAvSerializable(str1, paramString, i + 2);
        }
        if (str2.equals("4")) { j = extractHashtable(str1, paramString, i + 2);
        }
        if (str2.equals("5")) { j = extractInteger(str1, paramString, i + 2);
        }
        if (str2.equals("6")) { j = extractBigInt(str1, paramString, i + 2);
        }
      }
    }
    catch (Throwable localThrowable) {}
    

    paramString = null;
   
    getAttributes();
  }
  


  private void addString(StringBuffer paramStringBuffer, String paramString1, String paramString2)
  {
    paramStringBuffer.append(paramString1);
    paramStringBuffer.append("~");
    paramStringBuffer.append("1");
    paramString2 = removeDelims(paramString2);
    paramStringBuffer.append(paramString2);
    paramStringBuffer.append("`");
  }
  

  private void addInteger(StringBuffer paramStringBuffer, String paramString, Integer paramInteger)
  {
    paramStringBuffer.append(paramString);
    paramStringBuffer.append("~");
    paramStringBuffer.append("5");
    String str = stringFromInteger(paramInteger);
    paramStringBuffer.append(str);
  }
  

  private void addBigInteger(StringBuffer paramStringBuffer, String paramString, Integer paramInteger)
  {
    paramStringBuffer.append(paramString);
    paramStringBuffer.append("~");
    paramStringBuffer.append("6");
    String str = paramInteger.toString();
    paramStringBuffer.append(str);
    paramStringBuffer.append("&");
  }
  


  private int extractInteger(String paramString1, String paramString2, int paramInt)
  {
    String str = paramString2.substring(paramInt, paramInt + 1);
    Integer localInteger = IntegerFromString(str);
    attributes.put(paramString1, localInteger);
    return paramInt + 2;
  }
  


  private int extractBigInt(String paramString1, String paramString2, int paramInt)
  {
    int i = paramString2.indexOf("&", paramInt);
    String str = paramString2.substring(paramInt, i);
    int j = conv.getNumFromDisplayString(str);
    Integer localInteger = new Integer(j);
    attributes.put(paramString1, localInteger);
    return i + 1;
  }
  

  private void addVector(StringBuffer paramStringBuffer, String paramString, Vector paramVector)
  {
    paramStringBuffer.append(paramString);
    paramStringBuffer.append("~");
    paramStringBuffer.append("2");
    
    int i = paramVector.size();
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = ((byte)i);
    String str = new String(arrayOfByte);
    paramStringBuffer.append(str);
    paramStringBuffer.append("$");
    AvScienceDataObject localAvScienceDataObject = null;
    
    int j = paramVector.size();
    int k = 0;
    
    for (k = 0; k < j; k++)
    {
      Object localObject = paramVector.elementAt(k);
      if ((localObject instanceof Integer))
      {
        Integer localInteger = (Integer)localObject;
        int m = localInteger.intValue();
        paramStringBuffer.append("6");
        paramStringBuffer.append(localInteger.toString());
        paramStringBuffer.append("&");
      }
      
      if ((localObject instanceof String))
      {
        paramStringBuffer.append("1");
        paramStringBuffer.append((String)localObject);
        paramStringBuffer.append("`");
      }
      
      if ((localObject instanceof AvScienceDataObject))
      {
        localAvScienceDataObject = (AvScienceDataObject)localObject;
        paramStringBuffer.append("3");
        paramStringBuffer.append(localAvScienceDataObject.getObjectType());
        paramStringBuffer.append(localAvScienceDataObject.dataString());
        paramStringBuffer.append("|");
      }
    }
  }
  



  private void addHashtable(StringBuffer paramStringBuffer, String paramString, Hashtable paramHashtable)
  {
    paramStringBuffer.append(paramString);
    paramStringBuffer.append("~");
    paramStringBuffer.append("4");
    Vector localVector1 = new Vector();
    Vector localVector2 = new Vector();
    
    Enumeration localEnumeration = paramHashtable.keys();
    
    while (localEnumeration.hasMoreElements())
    {
      Object localObject1 = localEnumeration.nextElement();
      localVector1.addElement(localObject1);
      Object localObject2 = paramHashtable.get(localObject1);
      localVector2.addElement(localObject2);
    }
    
    Object localObject1 = paramString + "1";
    Object localObject2 = paramString + "2";
    
    addVector(paramStringBuffer, (String)localObject1, localVector1);
    addVector(paramStringBuffer, (String)localObject2, localVector2);
  }
  



  private int extractHashtable(String paramString1, String paramString2, int paramInt)
  {
    Hashtable localHashtable = new Hashtable();
    String str1 = paramString1 + "1";
    String str2 = paramString1 + "2";
    
    int i = paramString2.indexOf("~", paramInt);
    paramInt = extractVector(str1, paramString2, i + 2);
    i = paramString2.indexOf("~", paramInt);
    paramInt = extractVector(str2, paramString2, i + 2);
    
    Vector localVector1 = (Vector)attributes.get(str1);
    Vector localVector2 = (Vector)attributes.get(str2);
    
    int j = localVector1.size();
    int k = 0;
    
    for (k = 0; k < j; k++)
    {
      localHashtable.put(localVector1.elementAt(k), localVector2.elementAt(k));
    }
    
    attributes.put(paramString1, localHashtable);
    

    attributes.remove(str1);
    attributes.remove(str2);
    return paramInt;
  }
  


  private void addAvSerializable(StringBuffer paramStringBuffer, String paramString, AvScienceDataObject paramAvScienceDataObject)
  {
    paramStringBuffer.append(paramString);
    paramStringBuffer.append("~");
    paramStringBuffer.append("3");
    paramStringBuffer.append(paramAvScienceDataObject.getObjectType());
    paramStringBuffer.append(paramAvScienceDataObject.dataString());
    paramStringBuffer.append("|");
  }
  



  private int extractString(String paramString1, String paramString2, int paramInt)
  {
    int i = paramString2.indexOf("`", paramInt);
    String str = paramString2.substring(paramInt, i);
    attributes.put(paramString1, str);
    return i + 1;
  }
  



  private int extractAvSerializable(String paramString1, String paramString2, int paramInt)
  {
    String str1 = paramString2.substring(paramInt, paramInt + 1);
    int i = paramString2.indexOf("|", paramInt + 1);
    String str2 = paramString2.substring(paramInt + 1, i);
    AvScienceDataObject localAvScienceDataObject1 = (AvScienceDataObject)types.get(str1);
    AvScienceDataObject localAvScienceDataObject2 = null;
    try
    {
      localAvScienceDataObject2 = (AvScienceDataObject)localAvScienceDataObject1.getClass().newInstance();
    }
    catch (Throwable localThrowable) {}
    localAvScienceDataObject2.popFromString(str2);
    attributes.put(paramString1, localAvScienceDataObject2);
    return i + 1;
  }
  




  private int extractVector(String paramString1, String paramString2, int paramInt)
  {
    int i = paramString2.indexOf("$", paramInt);
    String str1 = paramString2.substring(paramInt, i);
    paramInt = i + 1;
    byte[] arrayOfByte = str1.getBytes();
    int j = arrayOfByte[0];
    Vector localVector = new Vector(j);
    int k = 0;
    String str2 = null;
    String str3 = null;
    
    for (k = 0; k < j; k++)
    {
      str2 = paramString2.substring(paramInt, paramInt + 1);
      paramInt += 1;
      if (str2.equals("1"))
      {
        i = paramString2.indexOf("`", paramInt);
        str1 = paramString2.substring(paramInt, i);
        localVector.insertElementAt(str1, k);
        paramInt = i + 1;
      }
      
      if (str2.equals("5"))
      {
        str1 = paramString2.substring(paramInt, paramInt + 1);
        Integer localInteger = IntegerFromString(str1);
        localVector.insertElementAt(localInteger, k);
        paramInt += 1;
      }
      Object localObject;
      if (str2.equals("6"))
      {
        i = paramString2.indexOf("&", paramInt);
        str1 = paramString2.substring(paramInt, i);
        
        int m = conv.getNumFromDisplayString(str1);
        localObject = new Integer(m);
        localVector.insertElementAt(localObject, k);
        paramInt = i + 1;
      }
      
      if (str2.equals("3"))
      {
        str3 = paramString2.substring(paramInt, paramInt + 1);
        paramInt += 1;
        i = paramString2.indexOf("|", paramInt);
        str1 = paramString2.substring(paramInt, i);
        AvScienceDataObject localAvScienceDataObject = (AvScienceDataObject)types.get(str3);
        localObject = null;
        try
        {
          localObject = (AvScienceDataObject)localAvScienceDataObject.getClass().newInstance();
        }
        catch (Throwable localThrowable) {}
        ((AvScienceDataObject)localObject).popFromString(str1);
        
        localVector.insertElementAt(localObject, k);
        
        paramInt = i + 1;
        localAvScienceDataObject = null;
      }
    }
    attributes.put(paramString1, localVector);
    return paramInt;
  }
  


  public String getObjectType()
  {
    return types.getKey(this);
  }
  


  private String removeDelims(String paramString)
  {
    Enumeration localEnumeration = delims.elements();
    
    while (localEnumeration.hasMoreElements())
    {
      String str = (String)localEnumeration.nextElement();
      paramString = paramString.replace(str.charAt(0), " ".charAt(0));
    }
    
    return paramString;
  }
  

  public String stringFromInteger(Integer paramInteger)
  {
    int i = paramInteger.byteValue();
    byte[] arrayOfByte = new byte[1];
    arrayOfByte[0] = (byte) i;
    String str = new String(arrayOfByte);
    return str;
  }
  
  public Integer IntegerFromString(String paramString)
  {
    byte[] arrayOfByte = paramString.getBytes();
    int i = arrayOfByte[0];
    
    Integer localInteger = new Integer(i);
    return localInteger;
  }
  
  public abstract String getKey();
  
  public abstract void getAttributes();
  
  public  abstract void setAttributes();
}
