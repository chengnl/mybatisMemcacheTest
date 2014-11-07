package com.vrv.im.domain;

import java.io.Serializable;

public class Blog  implements  Serializable{
	private static final long serialVersionUID = 1L;
		private long id;
	    private long testid;
	    private String name;
	    private String addr;
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddr() {
			return addr;
		}
		public void setAddr(String addr) {
			this.addr = addr;
		}
		
       public long getTestid() {
			return testid;
		}
		public void setTestid(long testid) {
			this.testid = testid;
		}
	public String toString(){
    	   return "id="+this.id+";name="+this.name+";addr="+this.addr;
       }
}
