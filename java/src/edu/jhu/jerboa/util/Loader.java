// Copyright 2010-2012 Benjamin Van Durme. All rights reserved.
// This software is released under the 2-clause BSD license.
// See jerboa/LICENSE, or http://cs.jhu.edu/~vandurme/jerboa/LICENSE

// Benjamin Van Durme, vandurme@cs.jhu.edu, 22 Jul 2012

package edu.jhu.jerboa.util;

import java.util.Hashtable;
import java.util.Vector;
import java.io.BufferedReader;
import java.util.AbstractMap.SimpleImmutableEntry;

/**
   @author Benjamin Van Durme

   Utility routines for common types of loading data.
 */
public class Loader {

  /**
     Default to separator: "\\t"
   */
  public static Hashtable<String,Vector<SimpleImmutableEntry<String,Double>>> readWeightedDictionary (String filename) throws Exception {
    return readWeightedDictionary(filename, "\\t");
  }

  /**
     Assumes format:
    
       (String) key SEP (String) mapped SEP (Double) weight

     if there are only 2 columns, then will default to a weight of 1.0
  */
  public static Hashtable<String,Vector<SimpleImmutableEntry<String,Double>>> readWeightedDictionary (String filename, String separator) throws Exception {
    BufferedReader reader = FileManager.getReader(filename);
    String[] tokens;
    String line;
    Hashtable<String,Vector<SimpleImmutableEntry<String,Double>>> dict = new Hashtable();
    while ((line = reader.readLine()) != null) {
	    tokens = line.split(separator);
      if (tokens.length >= 2) {
        if (! dict.containsKey(tokens[0]))
          dict.put(tokens[0], new Vector());
        if (tokens.length > 2)
          dict.get(tokens[0]).add(new SimpleImmutableEntry(tokens[1],Double.parseDouble(tokens[2])));
        else
          dict.get(tokens[0]).add(new SimpleImmutableEntry(tokens[1],1.0));
      }
    }
    reader.close();
    return dict;
  }
}
