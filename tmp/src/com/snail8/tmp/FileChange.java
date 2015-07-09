/**
 * 
 */
package com.snail8.tmp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author nuudng
 *
 */
public class FileChange {
	private static final String dir_path = "/opt/Documents/API/";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		File dir = new File(dir_path);
		if(dir.isDirectory()) {
			for(String file : dir.list()) {
				System.out.println(file);
				if(file.endsWith(".html")) {
					changeFile(dir_path + File.separator + file);
				}
			}
		}
	}

	/**
	 * 将GBK编码的html文件读出，并写入UTF-8编码的文件中．
	 * @param file
	 * @return
	 */
	public static StringBuffer changeFile(String file) {
		StringBuffer fileContent = new StringBuffer();
		InputStreamReader fr = null;
		BufferedReader br = null;
		OutputStreamWriter os = null;
		BufferedWriter bw = null;
		try {
			fr = new InputStreamReader(new FileInputStream(file), "gbk");
			br = new BufferedReader(fr);
			os = new OutputStreamWriter(new FileOutputStream(file + ".back"), "UTF-8");
			bw = new BufferedWriter(os);
			String line;
			while((line = br.readLine()) != null) {
				System.out.println(line);
				bw.write(line);
				if(line.equals("<HEAD>")) {
					System.out.println("  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
					bw.write("  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null)
					fr.close();
				if(br != null)
					br.close();
				if(bw != null) {
					bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return fileContent;
	}
}
