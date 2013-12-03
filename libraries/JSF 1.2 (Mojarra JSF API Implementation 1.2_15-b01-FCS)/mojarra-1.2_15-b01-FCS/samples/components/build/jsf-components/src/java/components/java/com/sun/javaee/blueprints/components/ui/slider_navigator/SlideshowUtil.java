/* Copyright 2005 Sun Microsystems, Inc. All rights reserved.
   You may not modify, use, reproduce, or distribute this software except in
   compliance with the terms of the License at: http://developer.sun.com/berkeley_license.html
   $Id: SlideshowUtil.java,v 1.1 2005/11/19 00:53:52 inder Exp $  */

package com.sun.javaee.blueprints.components.ui.slider_navigator;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;
import java.io.Writer;

public class SlideshowUtil {
    
    private static boolean bDebug=false;
    
    /** Creates a new instance of SlideshowUtil */
    public SlideshowUtil() {
    }
    
    /**
    * Performs Substitution on passed in strings.  This method could also use a cache to loop up strings previously used
    *
    * @param sxString String to parse
    * @param hmSub A Hashmap of of keys/variables to change
    * @param bLookup Whether to see if the value is to be looked up in the cache.  If true the sxString is used as a key
    */
    public static String parseString(String sxString, HashMap hmSub, boolean bLookup) {

        int iPos=0;
        String sxStringx="";

        if(bLookup) {
            // pull from cache
            //sxStringx=(String)getPages().get(sxString);
        } else {		    
            sxStringx=sxString;
        }

        if (sxStringx != null) {
            // populate other values that are page specific
            Set<String> keySet = hmSub.keySet();
            String valueStr;
            for (String keyStr : keySet) {
                valueStr = (String)hmSub.get(keyStr);
                sxStringx = sxStringx.replaceAll(keyStr, valueStr);
            }
        }
        return sxStringx;
    }     
        

    /*
     * Reads in a file in for processing, using rendering class location
     *
     * @param sxFile The file name to read in
     * @return File in string format
     */
    public static String readInFragmentAsString(String sxURL, String resource) throws IOException {
        BufferedReader bfReader=null;
        StringBuffer sxOut=new StringBuffer();
        sxURL=getResourceURL(sxURL, resource);

        try {
            bfReader=new BufferedReader(new InputStreamReader(new URL(sxURL).openConnection().getInputStream()));
            
            int byteCnt=0;
            char[] buffer=new char[4096];
            while ((byteCnt=bfReader.read(buffer)) != -1) {
                if (byteCnt > 0) {
                   sxOut.append(buffer, 0, byteCnt);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(bfReader != null) {
                try {
                    bfReader.close();
                } catch (Exception ee){}
            }
        }
        
        // place page in cache ???
        //htTemp.put(sxPageUrl,sxOut);
        
        return sxOut.toString();
    }
    
    
    /**
     * The URL looks like a request for a resource, such as a JavaScript or CSS file. Write
     * the given resource to the response output in binary format (needed for images).
     */
    public static void readWriteBinaryUtil(String sxURL, String resource, OutputStream outStream) throws IOException {
        
        DataOutputStream outData=null;
        DataInputStream inData=null;
        int byteCnt=0;
        byte[] buffer=new byte[4096];

        // get full qualified path of class
        System.out.println("RW Base Directory - " + sxURL);
        
        // remove class and add resource relative path
        sxURL=getResourceURL(sxURL, resource);

        System.out.println("RW Loading - " + sxURL);
        try {
            outData=new DataOutputStream(outStream);
            inData=new DataInputStream(new URL(sxURL).openConnection().getInputStream());

            while ((byteCnt=inData.read(buffer)) != -1) {
                if (outData != null && byteCnt > 0) {
                    outData.write(buffer, 0, byteCnt);
                }
            }
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if(inData != null) {
                    inData.close();
                }
            } catch (IOException ioe) {}
        }        
    }    
    
    
    /**
     * The URL looks like a request for a resource, such as a JavaScript or CSS file. Write
     * the given resource to the response writer in "char" format.
     */
    public static void readWriteCharUtil(String sxURL, String resource, Writer writer) throws IOException {
        
        BufferedReader bfReader=null;
        int byteCnt=0;
        char[] buffer=new char[4096];

        // get full qualified path of class
        System.out.println("RW Base Directory - " + sxURL);
        
        // remove class and add resource relative path
        sxURL=getResourceURL(sxURL, resource);

        System.out.println("RW Loading - " + sxURL);
        try {
            bfReader=new BufferedReader(new InputStreamReader(new URL(sxURL).openConnection().getInputStream()));

            while ((byteCnt=bfReader.read(buffer)) != -1) {
                if (writer != null && byteCnt > 0) {
                    writer.write(buffer, 0, byteCnt);
                }
            }
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if(bfReader != null) {
                    bfReader.close();
                }
            } catch (IOException ioe) {}
        }        
    }    
        
    
    
    public static String getResourceURL(String sxURL, String resource) {
        return sxURL.substring(0, sxURL.lastIndexOf("/")) + resource;
    }
    
}
