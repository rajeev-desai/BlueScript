package pkg;
public class Variable_Declaration
{
	static String var[]=new String[100],val[]=new String[100];//var stores all the variables and val stores all the values
	static int varco=0;//varco stores variable count
	static Operator_Statement oobj=new Operator_Statement();
	public static void analyze_var(String prog,int lineco)//read input line and line count
	{
		String temp,parts[],subparts[];//parts and subparts stores substrings
		parts = prog.split(" ");//splitting the line at space
		if(parts[0].trim().equals("var"))
		{
			//if(!parts[1].trim().contains(","))//to check for multiple variable declarations write later
			{
				if(parts[1].trim().contains("="))//if initialization is there
				{
					if(parts[1].trim().contains("["))
					{
						int size=Integer.parseInt(parts[1].substring(parts[1].trim().indexOf("[")+1,parts[1].trim().lastIndexOf("]")));//finding size
						subparts=parts[1].trim().split("=");//split with =
						temp=subparts[1].substring(subparts[1].trim().indexOf("{")+1,subparts[1].trim().lastIndexOf("}"));
						String num[]=temp.split(",");
						for(int i=0;i<size;i++)
						{
							var[varco]=subparts[0].charAt(0)+"["+i+"]";//store variable name in variable array
							val[varco]=num[i];//store value in value array
							varco++;
						}
					}
					else
					{
						subparts=parts[1].trim().split("=");//split with =
						var[varco]=subparts[0].trim();//store variable name in variable array
						String num=subparts[1].substring(0,subparts[1].length()-1).trim();//read initial value
						val[varco]=num.trim();//store value in value array
						varco++;//increment variable count
					}
				}
				else//if initialization is not there
				{
					if(parts[1].trim().contains("["))
					{
						int size=Integer.parseInt(parts[1].substring(parts[1].trim().indexOf("["),parts[1].trim().lastIndexOf("]")));//finding size
						for(int i=0;i<size;i++)
						{
							var[varco]=parts[1].substring(0,parts[1].indexOf("[")).trim()+"["+i+"]";//store variable name in variable array
							varco++;
						}
						
					}
					else
					{
						var[varco]=parts[1].substring(0,parts[1].length()-1).trim();//store variable name in variable array
						varco++;
					}
				}
			}
		}
		else
		{
			System.out.println("ERROR:Keyword missing at lineco:"+lineco);
		}
	}
	public static int retindex(String cvar,int lineco)/*method accepts a variable name 
	and line count and returns the index of the variable if found in variable array 
	else returns error message*/
	{
		int ind;//stores current index
		boolean found=false;
		for(ind=0;ind<varco;ind++)
		{
			/*if(cvar.contains("["))
			{
				String in=cvar.charAt(2)+"";
				if(!oobj.isNumber(in))
				{
					int index=retindex1(cvar,lineco);
					if(index!=-1)
					{
						cvar.replace(cvar.charAt(2)+"", index+"");
					}
				}
			}*/
			if(var[ind].equals(cvar.trim()))
			{
				found=true;
				break;
			}
		}
		if(!found)
		{
			System.out.println("ERROR:Variable "+cvar+" not declared at lineco:"+lineco);
			return -1;
		}
		return ind;
	}
	public static int retindex1(String cvar,int lineco)/*method accepts a variable name 
	and line count and returns the index of the variable if found in variable array 
	else returns -1*/
	{
		int ind;//stores current index
		boolean found=false;
		for(ind=0;ind<varco;ind++)
		{
			if(var[ind].equals(cvar.trim()))
			{
				found=true;
				break;
			}
		}
		if(!found)
		{
			return -1;
		}
		return ind;
	}
}
