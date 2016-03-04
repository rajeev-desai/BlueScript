package pkg;
import java.io.*;
public class If_Statement
{
	static String prog,condition;
	static String reg="(.*put\\(.*)([\\w])(.*\\).*)",regs="(.*get\\(.*)([\\w])(.*\\).*)"; 
	static Variable_Declaration vobj=new Variable_Declaration();//object declaration for Variable_declaration class
	static Print_Statement pobj=new Print_Statement();//object declaration for Print_statement class
	static Operator_Statement oobj=new Operator_Statement();
	static Input_Statement iobj=new Input_Statement();
	static Loop_Statement lobj=new Loop_Statement();
	@SuppressWarnings({ "resource", "deprecation", "static-access", "unused" })
	public static int ifstatement(String fname,int line)throws IOException
	{
		boolean cond=false,ifflag=false,elseflag=false;
		int lineskip=0,lineno=0;
		DataInputStream dis = new DataInputStream(new FileInputStream(fname));
		while((prog=dis.readLine())!=null)
		{
			prog.trim();
			lineno++;
			if(prog.charAt(0)=='!')
			{
				elseflag=true;
			}
			if(lineskip==0)
			{
				if(prog.equals("end ?")&&lineno>line)
				{
					return lineno-line;
				}
				if(lineno==line)
				{
					String parts[]=prog.split(" ");
					String subparts[]=parts[1].split("then");
					cond=condition(subparts[0].trim(),lineno);
				}
				else if(lineno>line)
				{
					if(cond==true/*&&elseflag==false*/)
					{
						//ifflag=true;
						if(prog.charAt(0)=='?')
						{
							lineskip=ifstatement(fname,lineno);
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
							lineskip=lobj.loop(prog,fname,lineno);
						}
						else
						{
							oobj.operator(prog,lineno);
						}
					}
					if(prog.charAt(0)=='!'/*&&ifflag==false*/)//else if statement
					{
						//elseflag=true;
						String s=prog.substring(prog.indexOf('?')+1,prog.indexOf("then"));
						cond=condition(s.trim(),lineno);
						if(cond==true)
						{
							lineskip=ifstatement(fname,lineno);
							continue;
						}
						else
						{
							lineskip=ifstatement(fname,lineno);
						}
					}
				}
			}
			else
			{
				lineskip--;
			}
		}
		return lineno-line;
	}
	@SuppressWarnings("static-access")
	public static boolean condition(String condition,int lineno)
	{
		if(condition.contains("<"))
		{
			String parts[]=condition.split("<");
			int ind=vobj.retindex(parts[0],lineno);
			int ind1=vobj.retindex(parts[1],lineno);
			if(ind!=-1)
			{
				if(ind1!=-1)
				{
					int var=Integer.parseInt(vobj.val[ind]);
					int cvar=Integer.parseInt(vobj.val[ind1]);
					if(var<cvar)
					{
						return true;
					}
				}
				else if(oobj.isNumber(parts[1]))
				{
					int var=Integer.parseInt(vobj.val[ind]);
					int cvar=Integer.parseInt(parts[1]);
					if(var<cvar)
					{
						return true;
					}
				}
			}
			else if(oobj.isNumber(parts[0])&&oobj.isNumber(parts[1]))
			{
				int var=Integer.parseInt(parts[0]);
				int cvar=Integer.parseInt(parts[1]);
				if(var<cvar)
				{
					return true;
				}
			}
		}
		else if(condition.contains(">"))
		{
			String parts[]=condition.split(">");
			int ind=vobj.retindex(parts[0],lineno);
			int ind1=vobj.retindex(parts[1],lineno);
			if(ind!=-1)
			{
				if(ind1!=-1)
				{
					int var=Integer.parseInt(vobj.val[ind]);
					int cvar=Integer.parseInt(vobj.val[ind1]);
					if(var>cvar)
					{
						return true;
					}
				}
				else if(oobj.isNumber(parts[1]))
				{
					int var=Integer.parseInt(vobj.val[ind]);
					int cvar=Integer.parseInt(parts[1]);
					if(var>cvar)
					{
						return true;
					}
				}
			}
			else if(oobj.isNumber(parts[0])&&oobj.isNumber(parts[1]))
			{
				int var=Integer.parseInt(parts[0]);
				int cvar=Integer.parseInt(parts[1]);
				if(var>cvar)
				{
					return true;
				}
			}
		}
		else if(condition.contains("=="))
		{
			String parts[]=condition.split("==");
			int ind=vobj.retindex(parts[0],lineno);
			int ind1=vobj.retindex(parts[1],lineno);
			if(ind!=-1)
			{
				if(ind1!=-1)
				{
					int var=Integer.parseInt(vobj.val[ind]);
					int cvar=Integer.parseInt(vobj.val[ind1]);
					if(var==cvar)
					{
						return true;
					}
				}
				else if(oobj.isNumber(parts[1]))
				{
					int var=Integer.parseInt(vobj.val[ind]);
					int cvar=Integer.parseInt(parts[1]);
					if(var==cvar)
					{
						return true;
					}
				}
			}
			else if(oobj.isNumber(parts[0])&&oobj.isNumber(parts[1]))
			{
				int var=Integer.parseInt(parts[0]);
				int cvar=Integer.parseInt(parts[1]);
				if(var==cvar)
				{
					return true;
				}
			}
		}
		else if(condition.contains("~="))
		{
			String parts[]=condition.split("~=");
			int ind=vobj.retindex(parts[0],lineno);
			int ind1=vobj.retindex(parts[1],lineno);
			if(ind!=-1)
			{
				if(ind1!=-1)
				{
					int var=Integer.parseInt(vobj.val[ind]);
					int cvar=Integer.parseInt(vobj.val[ind1]);
					if(var!=cvar)
					{
						return true;
					}
				}
				else if(oobj.isNumber(parts[1]))
				{
					int var=Integer.parseInt(vobj.val[ind]);
					int cvar=Integer.parseInt(parts[1]);
					if(var!=cvar)
					{
						return true;
					}
				}
			}
			else if(oobj.isNumber(parts[0])&&oobj.isNumber(parts[1]))
			{
				int var=Integer.parseInt(parts[0]);
				int cvar=Integer.parseInt(parts[1]);
				if(var!=cvar)
				{
					return true;
				}
			}
		}
		
		return false;
	}
}
