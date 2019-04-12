package com.jxq.douban;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestngHelloWorldSingle1 {
  @Test
  public void f() {
	  String str ="Hello World!";
	  
	  System.out.println(str);
	  
	  Assert.assertNotNull(str);
  }
}
