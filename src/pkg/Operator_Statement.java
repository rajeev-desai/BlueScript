package pkg;
public class Operator_Statement
{
	static Variable_Declaration vobj=new Variable_Declaration();
	@SuppressWarnings("static-access")
	public static void operator(String prog,int lineco)
	{
		if(prog.contains("["))
		{
			String ch=prog.substring(prog.indexOf("[")+1,prog.indexOf("]"));
			if(!isNumber(ch))
			{
				if(vobj.retindex1(ch, lineco)!=-1)
				{
					prog=prog.replace(ch, vobj.val[vobj.retindex1(ch, lineco)]);
					ops(prog,lineco);
				}
				else
				{
					System.out.println("ERROR:Variable "+ch+" not declared at lineco:"+lineco);
				}
			}
		}
		ops(prog,lineco);
	}
	@SuppressWarnings("static-access")
	public static void ops(String prog, int lineco)
	{
		if(prog.contains("=")&&!prog.contains("==")&&!prog.contains("~="))
		{
			String parts[]=prog.split("=");
			int ind=vobj.retindex1(parts[0],lineco);
			if(ind!=-1)
			{
				if(parts[1].trim().contains("+"))
				{
					oper(parts,lineco,ind,'+');
				}
				else if(parts[1].trim().contains("-"))
				{
					oper(parts,lineco,ind,'-');
				}
				else if(parts[1].contains("*"))
				{
					oper(parts,lineco,ind,'*');
				}
				else if(parts[1].contains("/"))
				{
					oper(parts,lineco,ind,'/');
				}
				else if(parts[1].contains("%"))
				{
					oper(parts,lineco,ind,'%');
				}
				else if(parts[1].contains("^"))
				{
					oper(parts,lineco,ind,'^');
				}
				else
				{
					parts[1]=parts[1].trim().substring(0,parts[1].length()-1)+"";
					if(isNumber(parts[1]))
					{
						vobj.val[ind]=parts[1].trim();
					}
					else if(vobj.retindex1(parts[1],lineco)!=-1)
					{
						vobj.val[ind]=vobj.val[vobj.retindex1(parts[1],lineco)];
					}
				}
			}
		}
		else if(prog.contains("++"))
		{
			String parts[]=prog.split("\\++");
			int ind=vobj.retindex1(parts[0],lineco);
			if(ind!=-1)
			{
				int t=Integer.parseInt(vobj.val[ind]);
				t++;
				vobj.val[ind]=t+"";
			}
		}
		else if(prog.contains("--"))
		{
			String parts[]=prog.split("--");
			int ind=vobj.retindex1(parts[0],lineco);
			if(ind!=-1)
			{
				int t=Integer.parseInt(vobj.val[ind]);
				t--;
				vobj.val[ind]=t+"";
			}
		}
	}
	@SuppressWarnings("static-access")
	public static void oper(String parts[], int lineco, int ind, char c)
	{
		String subparts[] = null;
		subparts=parts[1].trim().split(c);
		subparts[1]=subparts[1].trim().substring(0,subparts[1].length()-1);
		int ind1=vobj.retindex1(subparts[0].trim(),lineco);
		int ind2=vobj.retindex1(subparts[1].trim(),lineco);
		if(ind1!=-1)
		{
			if(ind2!=-1)
			{
				int result=0;
				if(c==1)
				{
					result=Integer.parseInt(vobj.val[ind1])+Integer.parseInt(vobj.val[ind2]);
				}
				else if(c==2)
				{
					result=Integer.parseInt(vobj.val[ind1])-Integer.parseInt(vobj.val[ind2]);
				}
				else if(c==3)
				{
					result=Integer.parseInt(vobj.val[ind1])*Integer.parseInt(vobj.val[ind2]);
				}
				else if(c==4)
				{
					result=Integer.parseInt(vobj.val[ind1])/Integer.parseInt(vobj.val[ind2]);
				}
				else if(c==5)
				{
					result=Integer.parseInt(vobj.val[ind1])%Integer.parseInt(vobj.val[ind2]);
				}
				else if(c==6)
				{
					result=pow(Integer.parseInt(vobj.val[ind1]),Integer.parseInt(vobj.val[ind2]));
				}
				vobj.val[ind]=result+"";
			}
			else if(isNumber(subparts[1].trim()))
			{
				int result=0;
				if(c==1)
				{
					result=Integer.parseInt(vobj.val[ind1])+Integer.parseInt(subparts[1].trim());
				}
				else if(c==2)
				{
					result=Integer.parseInt(vobj.val[ind1])-Integer.parseInt(subparts[1].trim());
				}
				else if(c==3)
				{
					result=Integer.parseInt(vobj.val[ind1])*Integer.parseInt(subparts[1].trim());
				}
				else if(c==4)
				{
					result=Integer.parseInt(vobj.val[ind1])/Integer.parseInt(subparts[1].trim());
				}
				else if(c==5)
				{
					result=Integer.parseInt(vobj.val[ind1])%Integer.parseInt(subparts[1].trim());
				}
				else if(c==6)
				{
					result=pow(Integer.parseInt(vobj.val[ind1]),Integer.parseInt(subparts[1].trim()));
				}
				vobj.val[ind]=result+"";
			}
		}
		else if(isNumber(subparts[0].trim())&&isNumber(subparts[1].trim()))
		{
			int result=0;
			if(c==1)
			{
				result=Integer.parseInt(subparts[0].trim())+Integer.parseInt(subparts[1].trim());
			}
			else if(c==2)
			{
				result=Integer.parseInt(subparts[0].trim())-Integer.parseInt(subparts[1].trim());
			}
			else if(c==3)
			{
				result=Integer.parseInt(subparts[0].trim())*Integer.parseInt(subparts[1].trim());
			}
			else if(c==4)
			{
				result=Integer.parseInt(subparts[0].trim())/Integer.parseInt(subparts[1].trim());
			}
			else if(c==5)
			{
				result=Integer.parseInt(subparts[0].trim())%Integer.parseInt(subparts[1].trim());
			}
			else if(c==6)
			{
				result=pow(Integer.parseInt(subparts[0].trim()),Integer.parseInt(subparts[1].trim()));
			}
			vobj.val[ind]=result+"";
		}
	}
	public static int pow(int a,int b)
	{
		int pow=1;
		for(int i=1;i<=b;i++)
		{
			pow=pow*a;
		}
		return pow;
	}
	public static boolean isNumber(String num)
	{
		try
		{
			Integer.parseInt(num);
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
	}
}