/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t9;

/**
 *
 * @author rb
 */
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.io.File;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class T9algo {
static String ans;

 //   private static Object FileUtils;
    static String predict(String s) throws IOException {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     String num_string=text_to_nums(s) ;
        ans=nums_to_text(num_string);
     return ans;
    }

    private static String text_to_nums(String s) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String k="";
     Map<String, Integer> KEYPAD_MAP = new HashMap<String, Integer>();
		int c=2;
                    for(char i='a';i<'z';){
                    String a=""+(char)i;
                    KEYPAD_MAP.put(a, c*10+1);
                    i++;
                     a=""+(char)i;
                    KEYPAD_MAP.put(a, c*10+2);
                    i++;
                      a=""+(char)i;
                    KEYPAD_MAP.put(a, c*10+3);
                    i++;
                    c++;
                    }
                    KEYPAD_MAP.put("z", 94);
                    
		
	
        for(int i=0;i<s.length();++i){
            if(s.charAt(i)==32) k+="*";
            else{
          String tmp=s.charAt(i)+"";
          k+=KEYPAD_MAP.get(tmp)/10;
            }
        
    }
        return k;
    }

    private static String nums_to_text(String num_string) throws FileNotFoundException, IOException  {
    
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         String li[]=num_string.split("\\*");
         TreeMap<String,String[]> z= new TreeMap<>();
         for(String num_word:li){
             String tmp[]=textonyms(num_word);
             z.put(num_word, tmp);
         }
               String bnc_str;
       StringBuilder sb = new StringBuilder();
     
BufferedReader br = new BufferedReader(new FileReader("/home/rb/NetBeansProjects/Test/src/test/all.num.o5.txt"));
    try {
         
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        bnc_str = sb.toString();//.split("\\n");
     //   System.out.println(everything[1]);
    } finally {
        br.close();
    }
       Multimap<String, String> d3 = ArrayListMultimap.create();
       StringBuilder s_words = new StringBuilder();
       StringBuilder all_words = new StringBuilder();       
   //      TreeMap<String,String> d3= new TreeMap<>();
       for(Map.Entry<String,String[]> entry : z.entrySet()) {
  String key = entry.getKey();
  String[] value = entry.getValue();
     for(String item :value){
         String pattern = "(\\S*)"+item;
      Pattern r = Pattern.compile(pattern);
      Matcher m = r.matcher(bnc_str);
      int i;
      for( i= m.start()-2;i>=0;--i)
      if(bnc_str.charAt(i)==32)
      break;
     d3.put(item,bnc_str.substring(i+1,m.start()-1));
     
     }
 //   all_words.append(bnc_str.substring(i+1,m.start()-1)+" ");
           
all_words.append(d3.get(key)+" ");
  
}
        return all_words.toString();
    }

    private static String[] textonyms(String num_word) throws FileNotFoundException, IOException {
      //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      String []all_possible ;
      String []everything;
       StringBuilder sb = new StringBuilder();
     // List<String> lines= Files.readLines(new File("/path/to/file.txt"), Charset.forName("utf-8"));
BufferedReader br = new BufferedReader(new FileReader("/home/rb/NetBeansProjects/Test/src/test/words.txt"));
    try {
         
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        everything = sb.toString().split("\\n");
     //   System.out.println(everything[1]);
    } finally {
        br.close();
    }
    sb = new StringBuilder();
    for(String word:everything){
        if(num_word==text_to_nums(word))
            sb.append(word+"\n");
    }
        all_possible=sb.toString().split("\\n");
        return all_possible;
    }
    
}