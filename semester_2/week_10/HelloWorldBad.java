// Daniel Smith (daniel.smith@malad.us)
// CTE Software Development II
// Instructor Mr. Gross

// I did not create this file, nor is this my code.
// The GPL license does not apply to this file or this code.
// I am only here to revise code for academic purposes.

class HelloWorldBad
{
    public static void main(String[] args)
    {
        for (int i = 0; i < 25; i++)
        {
            System.out.printf("the value of i is %d\n", i);

            if (i == 1)
            {
                System.out.println("i is equal to 1");
            }
        }    
    }
}
