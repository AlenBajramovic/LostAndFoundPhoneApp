import java.io.*;
import java.lang.*;

public class PhoneDriver
{
	private InputStreamReader ir=new InputStreamReader(System.in);
	private BufferedReader br;
	private FileOutputStream fos;
	private	PrintStream ps; 
	private FileReader frs;
	
	//function to check the existence for a file
	private boolean checkFile(String filename)
	{
		File myFile=new File(filename);
		boolean b=myFile.exists();	
		
		return b;
	}
	
	//function for auto-generating the product id
	private String generate_id()
	{
		String id=" ";
		String thisLine;
		String[] fullText = new String[200];
		String[] lastLine=new String[5];
		int counter=0,nid=0,l=0,z=0;
		
		boolean b=checkFile("Item.txt");
		
		if(b==false)
		{
			id="00001";
		}
		else
		{
			try
			{
				frs=new FileReader("Item.txt");
			 	br=new BufferedReader(frs);
				while((thisLine=br.readLine()) != null)
				{
					counter=counter+1;
					fullText[counter] = thisLine;
				}
				frs.close();
 				lastLine=fullText[counter].split(",");
 				nid=Integer.parseInt(lastLine[0]);
 				nid=nid+1;
 				l=(String.valueOf(nid)).length();
 				z=5-l;
 				for(int i=1;i<=z;i++)
 				{
 					id=id+"0";	
 				}
 				id=id+String.valueOf(nid);
 				id=id.substring(1);
			}
			catch(Exception ex)
			{
				System.out.println("Could not generate the Item ID...!");
			}
		}
		return id;
	}

	private String itemNum(){
		String id=" ";
		String thisLine;
		//String[] fullText = new String[200];
		//String[] lastLine=new String[5];
		int counter=0,nid=0,l=0,z=0;
		
		boolean b=checkFile("Item.txt");
		
		if(b==false)
		{
			id="1";
		}
		else
		{
			try
			{
				frs=new FileReader("Item.txt");
			 	br=new BufferedReader(frs);
				while((thisLine=br.readLine()) != null)
				{
					counter++;
				}
				frs.close();
 				id=String.valueOf(counter);
			}
			catch(Exception ex)
			{
				System.out.println("Could not generate the Item ID...!");
			}
		}
		return id;
	}
	
	//function to add new product
	private void addItem(String user)
	{
		try
 		{
			//id=generate_id();
			Device device= new Device();
			device.setDeviceId(itemNum());
			
			System.out.print("\n Device ID :"+device.getDeviceId()+"\n");
			br=new BufferedReader(ir);
			
			System.out.print("\n Enter name of Device: :");
			device.setDeviceName((br.readLine()));
			
			System.out.print("\n Enter Latitude: :");
			device.setLatitude(Float.parseFloat(br.readLine()));	

			System.out.print("\n Enter Longitude: :");
			device.setLongitude(Float.parseFloat(br.readLine()));
			
			fos=new FileOutputStream("Item.txt",true);
			ps=new PrintStream(fos);
			
			ps.println(user+","+device.getDeviceId()+","+device.getDeviceName()+","+device.getLatitude()+","+device.getLongitude());
			fos.close();
			System.out.println("Successfully added the device...");
		}
 		catch(Exception ex)
 		{
 			System.out.println("Error in accepting device specifications...");
 		}	
	}
	
	//function to make selling transaction for a specific product
	/*
	private void makeTransaction()
	{
		String id,desc,s="y";
		String msg,stock;
		String value[]=new String[5];
		double price=0,amount=0;
		int qty=0;
		
		try
		{
			while(s.equals("y"))
			{
				br=new BufferedReader(ir);
				
				System.out.print("\n Enter Product ID :");
				id=br.readLine();
				msg=findProduct(stock);
				if(msg.equals("Product not found..."))
				{
					System.out.print("\n"+msg);
					continue;
				}
				br=new BufferedReader(ir);
				value=msg.split(",");
				if(Integer.parseInt(value[3])==Integer.parseInt(value[4]))
				{
					System.out.println("Product in Re-Order Level.");
					System.out.println("Need to generate a purchase order...");
					continue;	
				}
				desc=value[1];
				price=Double.parseDouble(value[2]);
				stock=value[3];
				
				if(Integer.parseInt(stock)==0)
				{
					System.out.println("Current stock is NIL...");
					System.out.println("Could not process any transaction on this product...");
					continue;
				}
				
				System.out.print("\n Qty to sold :");
				qty=Integer.parseInt(br.readLine());
				
				if(qty>Integer.parseInt(stock))
				{
					System.out.println("Out of stock...!");
					System.out.println("Could not sell out this product...");
					continue;
				}
				amount=qty*price;
				
				int d=updateStock(id,qty);
				if(d==1)
				{
					System.out.println("Failed to update the stock...!");	
				}
				
				frs=new FileReader("Item.txt");
				br=new BufferedReader(frs);
				
				while((msg=br.readLine()) != null)
				{
					value=msg.split(",");
					if(value[0].equals(id))
					{
						stock=value[3];
						break;
					}
				}
				
				fos=new FileOutputStream("Transaction.txt",true);
				ps=new PrintStream(fos);
				ps.println(id+","+desc+","+qty+","+amount+","+stock);
				fos.close();
				
				s="n";
			}
			System.out.println("Transaction processed successfully...");	
		}
		catch(Exception ex)
		{
			System.out.println("Could not process the transaction...!");	
		}	
	}
	*/
	
	//COULD BE USED TO FIND A SPECIFIC NAME
	private String deleteDevicePrototype(String user) throws IOException
	{
		int counter=0,i=0,flag=0;
		String s;
		String cline[]=new String[255];
		String cid[]=new String[5];
		String value=" ";
		
		frs=new FileReader("Item.txt");
		br=new BufferedReader(frs);
		
		try
		{
			while((s=br.readLine()) != null)
			{
				counter=counter+1;
				cline[counter]=s;
				cid=cline[counter].split(",");
				if(cid[0].equals(user))
				{
					for(;i<cid.length;i++)
					{
						value=value+","+cid[i];	
					}
					flag=1;
					break;
				}
			}
			switch(flag)
			{
				case 0:
					value="Device not found...";
					break;
				case 1:
					value=value.substring(2);
					break;						
			}
		}
		catch(Exception ex)
		{
			System.out.println("Could continue operation on searching for the device...!");
		}
		
		return value;
	}
	/*
	private void deleteDevice(String user) {
		
		String deviceId;
		String deviceUser,id,deviceName,latitude,longitude,s;
		String cline[]=new String[4];
		
		try
		{
			

			displayReport(user);
			System.out.println("Enterdevice ID to remove: :");
			InputStreamReader input=new InputStreamReader(System.in);
			BufferedReader inputReader=new BufferedReader(input);
			deviceId= inputReader.readLine();
			//inputReader.close();
			
			frs=new FileReader("Item.txt");
			br=new BufferedReader(frs);
			
			while((s=br.readLine()) != null)
			{
				cline=s.split(",");
				deviceUser=cline[0];
				id=cline[1];
				deviceName=cline[2];
				latitude=cline[3];
				longitude=cline[4];
				//test
				boolean test= (Integer.parseInt(deviceId)==Integer.parseInt(id));
				System.out.println(test);
				System.out.println(deviceUser.contentEquals(user));
				
				if(deviceUser.contentEquals(user) && (Integer.parseInt(deviceId)==Integer.parseInt(id))) {
					fos=new FileOutputStream("Item.txt");
					ps=new PrintStream(fos);
					ps.print("");
					fos.close();
				}
				
			}
		}
		catch(Exception ex)
		{
			System.out.println("Some exceptions occured...");
			System.out.println("Could not delete device...!");	
		}
		
	}
	*/
	private int askDelete(String user)throws IOException {
		displayReport(user);
		System.out.println("Enterdevice ID to remove: :");
		InputStreamReader input=new InputStreamReader(System.in);
		BufferedReader inputReader=new BufferedReader(input);
		int deviceId= Integer.parseInt(inputReader.readLine());
		//inputReader.close();
		return deviceId;
	}
	
	private void deleteDevice(String user) {
		
		String deviceId;
		String deviceUser,id,deviceName,latitude,longitude,s;
		String cline[]=new String[4];
		
		try
		{
			File inputFile = new File("Item.txt");
	        File tempFile = new File("ItemTemp.txt");

	        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
	        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			
	        int idToRemove = askDelete(user);
	        String currentLine;
	        int count=0;
	        	
	        while((currentLine = reader.readLine()) != null) {
	            // trim newline when comparing with lineToRemove
	            String trimmedLine = currentLine.trim();
	            
	            cline=currentLine.split(",");
				deviceUser=cline[0];
				id=cline[1];
				deviceName=cline[2];
				latitude=cline[3];
				longitude=cline[4];
	            
				//test
				boolean test= ((idToRemove)==Integer.parseInt(id));
				System.out.println(test);
				System.out.println(deviceUser.contentEquals(user));
				
	            if(deviceUser.contentEquals(user) && (idToRemove)==Integer.parseInt(id)) continue;
	            writer.write(currentLine + System.getProperty("line.separator"));
	        }
	        writer.close(); 
	        reader.close(); 
	        inputFile.delete();
	        tempFile.renameTo(inputFile);
	        //tempFile.renameTo(inputFile);
	        /*
			while((s=br.readLine()) != null)
			{
				cline=s.split(",");
				deviceUser=cline[0];
				id=cline[1];
				deviceName=cline[2];
				latitude=cline[3];
				longitude=cline[4];
				//test
				boolean test= (Integer.parseInt(deviceId)==Integer.parseInt(id));
				System.out.println(test);
				System.out.println(deviceUser.contentEquals(user));
				
				if(deviceUser.contentEquals(user) && (Integer.parseInt(deviceId)==Integer.parseInt(id))) {
					//deletes line
				}
				
			}
			*/
		}
		catch(Exception ex)
		{
			System.out.println("Some exceptions occured...");
			System.out.println("Could not delete device...!");	
		}
		
	}
	
	//NOT USING THIS
	private int updateStock(String pID,int qty) throws IOException
	{
		int counter=0,i=0,pos=0;
		String s;
		String cline[]=new String[255];
		String cID[]=new String[5];
		String row[]=new String[5];
		String desc,price,rol;
		int stock=0,status=0;
		
		try
		{
			frs=new FileReader("Item.txt");
			br=new BufferedReader(frs);		
			
			while((s=br.readLine()) != null)
			{
				counter=counter+1;
				cline[counter]=s;			
				cID=s.split(",");
				if(cID[0].equals(pID))
 				{
 					pos=counter;
 					row=s.split(",");
 				}	
			}
			
			fos=new FileOutputStream("Item.txt",false);
			ps=new PrintStream(fos);
			
			for(i=0;i<cline.length;i++)
			{			
				if(i==pos)
				{
					desc=row[1];
					price=row[2];
					stock=Integer.parseInt(row[3])-qty;
					rol=row[4];
					ps.println(pID+","+desc+","+price+","+stock+","+rol);
				}
				else
				{
					if(cline[i]!=null)
					{
						ps.println(cline[i]);
					}
				}
			}
			fos.close();	
			status=0;
		}
		catch(Exception ex)
		{
			status=1;	
		}
		return status;	
	}
	
	//WE ARENT USING THIS 
	private void displayReport(String user)
	{
		String deviceUser,id,deviceName,latitude,longitude,s;
		String cline[]=new String[4];
		
		try
		{
			frs=new FileReader("Item.txt");
			br=new BufferedReader(frs);		
			
			System.out.println("\n*** CURRENT INVENTORY ***");
			//System.out.println("----------------------------------------------");
		
			while((s=br.readLine()) != null)
			{
				cline=s.split(",");
				deviceUser=cline[0];
				id=cline[1];
				deviceName=cline[2];
				latitude=cline[3];
				longitude=cline[4];
				
				if(deviceUser.contentEquals(user)) {
					System.out.println("DEVICE ID: " + id+", NAME: "+deviceName+", (Latitude,Longitude): ("+latitude+","+longitude+")  ");
				}
			}	
		}
		catch(Exception ex)
		{
			System.out.println("Some exceptions occured...");
			System.out.println("Could not generate the daily transaction report...!");	
		}
	}
	
	private static String login() throws IOException {
		
		System.out.println("Enter your username: :");
		InputStreamReader ir=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(ir);
		String username= br.readLine();
		return username;
	}
	
	public void markLost() {
		
	}
	
	public void markFound() {
		
	}
	
	private static void menu(String user) throws IOException {
		
		
		PhoneDriver inv=new PhoneDriver();
		int ch=0;
		String s;
		InputStreamReader ir=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(ir);
		
		begin:
		while(ch!=6)
		{
			System.out.print("\n");
			System.out.println("                                ");
			System.out.println("Main Menu:");
			System.out.println("1 ... Register Device");
			System.out.println("2 ... Unregister Device");
			System.out.println("3 ... Display Devices");
			System.out.println("4 ... Mark Device Lost");
			System.out.println("5 ... Mark Device Found");
			System.out.println("6 ... Logout");
			System.out.print("\n Enter a choice and Press ENTER to continue[1-6]:");
			ch=Integer.parseInt(br.readLine());
			
			if(ch>6||ch<1)
			{
				System.out.println("This doesn't appear to be a valid option...!");
				continue begin;	
			}
			
			if(ch==1)
			{
				s="y";
				while(s.equals("y")||s.equals("Y"))
				{
					inv.addItem(user);
					System.out.print("\n Register another[y/n]:");
					s=br.readLine();						
				}
				continue begin;
			}
			else
			if(ch==2)
			{
				s="y";
				while(s.equals("y")||s.equals("Y"))
				{
					inv.deleteDevice(user);			
					System.out.print("\n Unregister another[y/n]:");
					s=br.readLine();						
				}
				continue begin;
			}
			else
			if(ch==3)
			{
				//DISPLAY ALL DEVICES
				inv.displayReport(user);
				continue begin;
			}	
			if(ch==4)
			{
				//DEVICE LOST
				inv.markLost();	
				continue begin;
			}
			if(ch==5)
			{
				//DEVICE FOUND
				inv.markFound();
				continue begin;
			}
		}
		
	}
	
	public static void main(String args[]) throws IOException
	{
		PhoneDriver inv=new PhoneDriver();
		int ch=0;
		String s;
		InputStreamReader ir=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(ir);
		
		begin:
			while(ch!=2)
			{
				System.out.print("\n");
				System.out.println("                                ");
				System.out.println("Phone Options:");
				System.out.println("1 ... Log In");
				System.out.println("2 ... Shut Down");
				System.out.print("\nEnter a choice and Press ENTER to continue :");
				ch=Integer.parseInt(br.readLine());
				
				if(ch>2||ch<1)
				{
					System.out.println("This doesn't appear to be a valid option...!");
					continue begin;	
				}
				
				if(ch==1) {
					//LOGIN GOES HERE
						String username=login();
						menu(username);	
						continue begin;
					}
					
				if(ch==2)
				{
					System.out.print("\n");
					System.out.println("Thanks for using this program...!");
				}
	
			}

	}
}
