package pkg;
import java.io.*;
public class Loop_Statement
{
	static String reg="(.*put\\(.*)([\\w])(.*\\).*)",regs="(.*get\\(.*)([\\w])(.*\\).*)"; 
	static Variable_Declaration vobj=new Variable_Declaration();//object declaration for Variable_declaration class
	static Print_Statement pobj=new Print_Statement();//object declaration for Print_statement class
	static Operator_Statement oobj=new Operator_Statement();
	static Input_Statement iobj=new Input_Statement();
	static If_Statement ifobj=new If_Statement();
	@SuppressWarnings({ "resource", "deprecation", "static-access", "unused" })
	public static int loop(String prog,String fname,int line)throws IOException
	{
		boolean cond=false,cond1=false;
		int lineno=0,lineskip=0,ind=-1,i;
		String parts[]=null;
		parts=prog.split(" ");
		ind=vobj.retindex(parts[0],lineno);
		if(ind==-1)
		{
			cond=oobj.isNumber(parts[0]);
		}
		if(ind!=-1)
		{
			for(i=0;i<Integer.parseInt(vobj.val[ind]);i++)
			{
				lineno=0;
				DataInputStream dis = new DataInputStream(new FileInputStream(fname));
				while((prog=dis.readLine())!=null)
				{
					prog.trim();
					lineno++;
					if(lineskip==0)
					{
						if(prog.equals("end loop"))
						{
							break;
						}
						if(lineno>line)
						{
							if(prog.charAt(0)=='?')
							{
								lineskip=ifobj.ifstatement(fname,lineno);
							}
							/*if(prog.charAt(prog.length()-1)!=';'&&(prog.charAt(0)!='?'||prog.charAt(0)!='!'))//check for semicolon 
							{
								System.out.println("ERROR:; missing at line:"+lineno);
								break;
							}*/
							if(prog.equals("var"))//call variable declaration
							{
								vobj.analyze_var(prog,lineno);
							}
							else if(prog.matches(reg))//call print statement
							{
								pobj.print(prog,lineno);
							}
							else if(prog.matches(regs))
							{
								iobj.input(prog,lineno);
							}
							else if(prog.contains("times"))
							{
								lineskip=loop(prog,fname,lineno);
							}
							else
							{
								oobj.operator(prog,lineno);
							}
						}
					}
					else
					{
						lineskip--;
					}
				}
			}
		}
		else if(cond==true)
		{
			for(i=0;i<Integer.parseInt(parts[0]);i++)
			{
				lineno=0;
				DataInputStream dis = new DataInputStream(new FileInputStream(fname));
				while((prog=dis.readLine())!=null)
				{
					prog.trim();
					lineno++;
					if(lineskip==0)
					{
						if(prog.equals("end loop"))
						{
							break;
						}
						if(lineno>line)
						{
							if(prog.charAt(0)=='?')
							{
								lineskip=ifobj.ifstatement(fname,lineno);
							}
							/*if(prog.charAt(prog.length()-1)!=';'&&(prog.charAt(0)!='?'||prog.charAt(0)!='!'))//check for semicolon 
							{
								System.out.println("ERROR:; missing at line:"+lineno);
								break;
							}*/
							if(prog.equals("var"))//call variable declaration
							{
								vobj.analyze_var(prog,lineno);
							}
							else if(prog.matches(reg))//call print statement
							{
								pobj.print(prog,lineno);
							}
							else if(prog.matches(regs))
							{
								iobj.input(prog,lineno);
							}
							else if(prog.contains("times"))
							{
								lineskip=loop(prog,fname,lineno);
							}
							else
							{
								oobj.operator(prog,lineno);
							}
						}
					}
					else
					{
						lineskip--;
					}
				}
			}
		}
		return lineno-line;
	}
}
