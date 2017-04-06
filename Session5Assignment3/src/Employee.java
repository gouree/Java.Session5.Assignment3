/*
 * Write a program that will compute an Employee's salary and manage their leave details.
Note:
Pf = 12% of basic
hra = 50% of basic
total_sal = basic + hra – pf
 */

abstract public class Employee
{
	int empId;
	String empName;
	int total_leaves;
	double total_salary;
	
	abstract void calculate_balance_leaves();
	abstract boolean avail_leave(int no_of_leaves, char type_of_leave);
	abstract void calculate_salary();

	public static void main(String args[])
	{
		boolean ans;
		int leave;
		PermanentEmp p1= new PermanentEmp(10000);//create object of permanent employee
		PermanentEmp p2= new PermanentEmp(12000); //create object of permanent employee
		PermanentEmp p3= new PermanentEmp(15000);  //create object of permanent employee
		
		
		System.out.println("Permanent Employee Details");
		System.out.println("***************************");
		
		
		System.out.println("Employee 1--->");
		leave=6;
		System.out.println("Leave requested for "+leave+" number of days");
		ans=p1.avail_leave(leave, 's');
		
		//true is returned if requested no of days <= total leaves available
		if (ans==true)
			System.out.println("Leaves can be granted");
		else
			System.out.println("Leave cannot be granted");
		
		p1.calculate_balance_leaves();
		p1.print_leave_details();
		p1.calculate_salary();
		System.out.println("-----------------------------------------------");
		
		System.out.println("Employee 2--->");
		leave=2;
		System.out.println("Leave requested for "+leave+" number of days");
		ans=p2.avail_leave(leave, 'e');
		if(ans==true)
			System.out.println("Leaves can be granted");
		else
			System.out.println("Leave cannot be granted");
		
		p2.calculate_balance_leaves();
		p2.print_leave_details();
		p2.calculate_salary();
		System.out.println("-----------------------------------------------");
		
		
		System.out.println("Employee 3--->");
		leave=1;
		System.out.println("Leave requested for "+leave+" number of days");
		ans=p3.avail_leave(leave, 'c');
		if(ans==true)
			System.out.println("Leaves can be granted");
		else
			System.out.println("Leave cannot be granted");
		
		p3.calculate_balance_leaves();
		p3.print_leave_details();
		p3.calculate_salary();
		System.out.println("-----------------------------------------------");
		
		
		//object of temporaryemployee created
		TemporaryEmp t1= new TemporaryEmp(7000);
		TemporaryEmp t2= new TemporaryEmp(5000);
		
		
		System.out.println("Temporary Employee Details");
		System.out.println("***************************");
		
		
		System.out.println("Temporary Employee 1--->");
		leave=1;
		System.out.println("Leave requested for "+leave+" number of days");
		ans=t1.avail_leave(leave, 's');
		if(ans==true)
			System.out.println("Leaves can be granted");
		else
			System.out.println("Leave cannot be granted");
		t1.calculate_salary();
		System.out.println("-----------------------------------------------");
		
		System.out.println("Temporary Employee 2--->");
		leave=3;
		System.out.println("Leave requested for "+leave+" number of days");
		ans=t2.avail_leave(leave, 'e');
		if(ans==true)
			System.out.println("Leaves can be granted");
		else
			System.out.println("Leave cannot be granted");
		t2.calculate_salary();
		
		System.out.println("-----------------------------------------------");
	}

}

class PermanentEmp extends Employee
{
		double basic,pf,hra;
		int e=0,c=0,s=0, //counters for earned leave,casual leave,sick leave
		el=10,cl=6,sl=5,	// max values of earned leave,casual leave,sick leave
		be,bc,bs ;	//balance of earned leave,casual leave,sick leave
	
		PermanentEmp(double val)
		{
			basic= val;	//initialize basic salary 
		}
		
		void calculate_balance_leaves()
		{
			//balance leave = max value- total used value
		 
			be= el-e;	
			bc=cl-c;
			bs=sl-s;
		}
	 
		boolean avail_leave(int no_of_leaves, char type_of_leave)
		{
		 
			if(type_of_leave=='e')
			{
				// increment the counter of earned leave whenever leave is requested
				e=e+no_of_leaves;
				
				//check whether the counter <= total no of leaves possible 
				if(e<=el)
				{
					return(true);
				}
				else
				{
					//if leave cannot be granted bring the counter back to original value before allocation
					e=e-no_of_leaves;
					return(false);
				}
			}
		 
		
			if(type_of_leave=='s')
			{
				s=s+no_of_leaves;
				if(s<=sl)
				{
					return(true);
				}
				else
				{
					s=s-no_of_leaves;
					return(false);
				}
			}
		
			if(type_of_leave=='c')
			{
				c=c+no_of_leaves;
				if(c<=cl)
				{
					return(true);
				}
				else
				{
					c=c-no_of_leaves;
					return(false);
				}
			}
			
			else 
				return false;
		}
	 
		void calculate_salary()
		{
			pf = 0.12*basic;
			hra = .50*basic;
			total_salary = basic + hra - pf;
			System.out.println("Total salary --->"+total_salary);
		}
	 
		void print_leave_details()
		{
		 
			System.out.println("Maximum Casual leaves   --> "+cl);
			System.out.println("Maximum Earned leaves  --> "+el);
			System.out.println("Maximum Sick leaves  --> "+sl);
		 
		 
			System.out.println("Total Casual leaves remaining --> "+bc);
			System.out.println("Total Earned leaves remaining --> "+be);
			System.out.println("Total Sick leaves remaining --> "+bs);
		 
			System.out.println("Total Casual leaves used --> "+c);
			System.out.println("Total Sick leaves used --> "+s);
			System.out.println("Total Earned leaves used --> "+e);
		 
		 }

}

class TemporaryEmp  extends Employee
{
		double basic,pf,hra;
		int e=0,c=0,s=0, //counters for earned leave,casual leave,sick leave
		el=10,cl=6,sl=5,	// max values of earned leave,casual leave,sick leave
		be,bc,bs ;	//balance of earned leave,casual leave,sick leave
	
		TemporaryEmp(double val)
		{
			basic= val;	//initialize basic salary 
		}
	 
		void calculate_balance_leaves()
		{
		//balance leave = max value- total used value
		 
			be= el-e;	
			bc=cl-c;
			bs=sl-s;
		 
		}
	 
		boolean avail_leave(int no_of_leaves, char type_of_leave)
		{
		 
			if(type_of_leave=='e')
			{
				e=e+no_of_leaves;
				if(e<=el)
				{
					return(true);
				}
				else
				{
					return(false);
				}
		}
		 
		
			if(type_of_leave=='s')
			{
				s=s+no_of_leaves;
				if(s<=sl)
				{
					return(true);
				}
				else
				{
					return(false);
				}
			}
		
			if(type_of_leave=='c')
			{
				c=c+no_of_leaves;
				if(c<=cl)
				{
					return(true);
				}
				else
				{
					return(false);
				}
			}
			else 
				return false;
		}
	 
		void calculate_salary()
		{
			pf = 0.12*basic;
			hra = .50*basic;
			total_salary = basic + hra - pf;
			System.out.println("Total salary --->"+total_salary);
		}
	
}
