import java.util.regex.*;
import java.util.*;
import java.io.*;
 
public class Count {
 public static void main(String[] args) throws IOException{
	 double begin = System.currentTimeMillis();//計時開始
	 
	 //讀取txt檔全文
	 FileReader fr = new FileReader("C:\\INPUT.txt");
	 BufferedReader br = new BufferedReader(fr);
	 String strNum = br.readLine();
	 StringBuffer novel = new StringBuffer();
	 while ((strNum=br.readLine())!=null){
	   novel.append(strNum.toLowerCase());//讀txt全部文字檔並大寫轉小寫
	 }

	 //Regular expression
	 Pattern p = Pattern.compile("\\w[^\\.?!@)#$'(:,;\\s-\"]*");
	 Matcher m = p.matcher(novel);
	 
	 Set<String> sortVerb = new TreeSet<String>();
	 ArrayList<String> strArray = new ArrayList<String>();
	 while(m.find()){
		 strArray.add(m.group());
		 sortVerb.add(m.group());
		 }
	 
	 //讀取novel單字頻率
	 int[] verbCount = new int[sortVerb.size()];
	 int index = 0;
	 for(String sv : sortVerb){
		 int count = 0;
		 for(String sa : strArray){
			 if(sv.equals(sa))
				 count++;
			 }
		 verbCount[index++] = count;
		 }
	 
	 //開始排序
	 int[] indexArray = new int[sortVerb.size()];
	 for(int i = 0; i < indexArray.length; i++) {
		 indexArray[i] = i;
		 }
	 int temp, indexTemp;
	 index = 0;
	 for (int f = 1; f < sortVerb.size(); f++) {
		 if (verbCount[f] < verbCount[f-1]) {
			 continue;
			 }
		 temp = verbCount[f];
		 indexTemp = indexArray[f];
		 index = f-1;
		 while ((index >= 0)&&(verbCount[index] < temp)) {
			 verbCount[index+1] = verbCount[index];
			 indexArray[index+1] = indexArray[index];
			 index--;
			 }
		 verbCount[index+1]=temp;
		 indexArray[index+1] = indexTemp;
		 }
	 
	 Object[] printStr = sortVerb.toArray();
	 for(int i = 0; i < indexArray.length; i++) {
		 System.out.println(verbCount[i]+": "+printStr[indexArray[i]].toString());
		 }
	 
	 double over = System.currentTimeMillis();
	 System.out.println("程式執行總共花費 "+ (over - begin)/1000 + " 秒 " );
	 




	 FileWriter fw = new FileWriter("OUTPUT-B.txt");
	 for(int i = 0; i < indexArray.length; i++) { 
		 fw.write(verbCount[i]+": "+printStr[indexArray[i]].toString()+"\r\n");
		 }
	 fw.write("程式執行總共花費 "+ (over - begin)/1000 + " 秒 ");
	 fw.close();
	 }
 }
