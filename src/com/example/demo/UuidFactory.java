package com.example.demo;

import java.util.UUID;


public class UuidFactory {

	public static String getUuid(){
		UUID uuidFactory = UUID.randomUUID();
		String uuid = uuidFactory.toString().replaceAll("-", "");
		return uuid;
	}

	public static void main(String[] args){
		System.out.println(UuidFactory.getUuid());
		String oldUUid = null;
		for(int i = 0; i < 100000; i++){
			String newUUid = UuidFactory.getUuid();
			if(oldUUid != newUUid){
				oldUUid = newUUid;
				System.out.println(oldUUid);
				System.out.println(newUUid);
				break;
			}else{
				System.out.println("wokaole");
			}
			System.out.println(i);
		}
	}
}
