package pkg;
import java.util.Scanner;
public class Input_Statement
{
	static Variable_Declaration vobj=new Variable_Declaration();
	@SuppressWarnings({ "static-access", "resource" })
	public static void input(String prog,int lineco)
	{
		String var=prog.substring(prog.indexOf("\\(")+5,prog.length()-2);
		int ind=vobj.retindex(var,lineco);
		if(ind!=-1)
		{
			Scanner sc=new Scanner(System.in);
			String text=sc.next();
			vobj.val[ind]=text;
		}
	}
}
