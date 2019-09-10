import java.util.regex.*;
import java.util.*;
import java.io.*;
 
public class Disorder {
 public static void main(String[] args) throws IOException{
	 double begin = System.currentTimeMillis();//計時開始
	 
	 //讀取txt檔全文
	 FileReader fr = new FileReader("C:\\INPUT.txt");
	 BufferedReader br = new BufferedReader(fr);
	 String strNum = br.readLine();
	 StringBuffer novel = new StringBuffer();
	 while ((strNum=br.readLine())!=null){
	   novel.append(strNum.toLowerCase());//大寫轉小寫
	 }
	 //Regular expression
	 Pattern p = Pattern.compile("\\w[^\\.?!@)#$'(:,;\\s-\"]*");
	 Matcher m = p.matcher(novel);
	 
	 
	 ArrayList<String> strArray = new ArrayList<String>();
	 while(m.find()){
	   strArray.add(m.group());
	  }
	  
	 int x = 0;
	 int y = 0;
	 for(int i=0; i<strArray.size();i++) {
		 for(int j=i+1; j<strArray.size();j++) {
			 x = strArray.get(i).compareTo(strArray.get(j));
               //比較大小。若X>0則為亂序(相等回傳0，正序回傳負值)
			 if(x>0) {
				 y=y+1;  
				 }
			 }
		 }
	 
	 double over = System.currentTimeMillis();
	 FileWriter fw = new FileWriter("OUTPUT-A.txt");
	 fw.write(y+"\r\n"+"程式執行總共花費 "+ (over - begin)/1000 + "秒");
	 fw.close();
	 System.out.println(y+"\r\n"+"程式執行總共花費 "+ (over - begin)/1000 + "秒");
	 }
 }
