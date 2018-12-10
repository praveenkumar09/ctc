package com.censof.myfi.hidefmyfi.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SampleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String newFormat = sdf.format(new Date());
		System.out.println(newFormat);
		
	    SimpleDateFormat sdfFileName = new SimpleDateFormat("yyyyMMdd'T'HHmmssSSS'Z'");
	    String sampleFormat = sdfFileName.format(new Date(System.currentTimeMillis()));
	    System.out.println(sampleFormat);

	}

}
