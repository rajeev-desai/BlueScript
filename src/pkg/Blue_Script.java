package pkg;
import java.io.*;
import java.util.Scanner;
public class Blue_Script
{
	static String fname,prog;//fname stores file address and prog stores line read from file
	static int lineco=1,lineskip=0;//lineco is the line count
	static String regp="(.*put\\(.*)([\\w])(.*\\).*)",regs="(.*get\\(.*)([\\w])(.*\\).*)"; 
	static Variable_Declaration vobj=new Variable_Declaration();//object declaration for Variable_declaration class
	static Print_Statement pobj=new Print_Statement();//object declaration for Print_statement class
	static Operator_Statement oobj=new Operator_Statement();
	static Input_Statement iobj=new Input_Statement();
	static If_Statement ifobj=new If_Statement();
	static Loop_Statement lobj=new Loop_Statement();
	@SuppressWarnings({ "static-access", "resource", "deprecation" })
	public static void main(String[] args)throws IOException
	{
			System.out.println("Enter the filename.");
			Scanner s=new Scanner(System.in);
			fname=s.nextLine();
			DataInputStream dis = new DataInputStream(new FileInputStream(fname));
			while((prog=dis.readLine())!=null)
			{
				if(lineskip==0)
				{
					prog.trim();
					String par[]=prog.split(" ");
					if(prog.charAt(0)=='?')
					{
						lineskip=ifobj.ifstatement(fname,lineco);
					}
					/*if(prog.charAt(0)!='?'||prog.charAt(0)!='!')
					{
					if(prog.charAt(prog.length()-1)!=';')//check for semicolon 
					{
						System.out.println("ERROR:; missing at line:"+lineco);
						break;
					}
					}*/
					if(par[0].equals("var"))//call variable declaration
					{
						vobj.analyze_var(prog,lineco);
					}
					else if(prog.matches(regp))//call print statement
					{
						pobj.print(prog,lineco);
					}
					else if(prog.matches(regs))
					{
						iobj.input(prog,lineco);
					}
					else if(prog.contains("times"))
					{
						lineskip=lobj.loop(prog,fname,lineco);
					}
					else
					{
						oobj.operator(prog,lineco);
					}
					lineco++;//increment line count
				}
				else
				{
					lineskip--;
					lineco++;//increment line count
				}
			}
	}
}
//C:\Users\Rajeev\Desktop\BlueScript\src\pkg\Sample Program4.txt