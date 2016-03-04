package pkg;
public class Print_Statement 
{
	static Variable_Declaration vobj=new Variable_Declaration();
	static Operator_Statement oobj=new Operator_Statement();
	@SuppressWarnings("static-access")
	public static void print(String prog,int line) 
	{
		if(prog.contains("\""))//check if quotes are present
		{
			String varip=new String();//to store variable in print statement
			String valip=new String();//to store value of variable
			String print=prog.substring(prog.indexOf("\"")+1,prog.lastIndexOf("\""));//print stores the text between quotes
			int lio=prog.lastIndexOf('"');//lio stores the last index of " in the print statement
			if(prog.charAt(lio+1)=='+')
			{
				int ind;
				varip=prog.substring(prog.indexOf("+")+1,prog.indexOf(")")).trim();
				if(varip.contains("[")&&!oobj.isNumber(varip.substring(varip.indexOf("[")+1,varip.indexOf("]"))))
				{
					String varipind=varip.substring(varip.indexOf("[")+1,varip.indexOf("]"));
					varip.replace(varipind, vobj.val[vobj.retindex(varipind, line)]);
				}
				ind=vobj.retindex(varip,line);
				if(ind!=-1)
				{
					valip=vobj.val[ind];
				}
				if(valip==null)
				{
					System.out.println("ERROR:Variable "+varip+" not initialised at line "+line);
				}
				else
				{
					System.out.println(print+valip);
				}
			}
			else
			{
				System.out.println(print);
			}
			
		}
	}

}
