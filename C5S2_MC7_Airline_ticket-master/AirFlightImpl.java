import java.util.*;
import java.io.*;
class AirFlightImpl
{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        Scanner sc1=new Scanner(System.in);
        TicketImp imp=new TicketImp();
        List<Ticket> ticketList=new LinkedList<>();
        List<Passenger> passengerList=new LinkedList<>();
        List<Passenger> newPassenger=new LinkedList<>();
        try
        {
            BufferedReader bf=new BufferedReader(new FileReader("routes.txt"));
            String line;
            bf.readLine();
            while((line=bf.readLine())!=null)
            {
                String[] str=line.split(",");
                ticketList.add(new Ticket(str[0], str[1], str[2], str[3], str[4]));
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("Select Service Type: 1.Book Flight Tickets\n 2.View Booked Flights\n 3.Cancel Flight Tickets");
        int service=sc.nextInt();
        if(service==1)
        {
            System.out.println("Select Flight Type: 1.Domestic(D) or 2.International(I)");
            int Ftype=sc.nextInt();
            FlightType type;
            if(Ftype==1)
                type=FlightType.Domestic;
            else
                type=FlightType.International;
            System.out.println("Enter Origin, Destination and DOJ: ");
            String ori=sc1.nextLine();
            String dest=sc1.nextLine();
            String doj=sc1.nextLine();
            if(imp.displayAvailableFlights(ticketList,ori,dest,doj))
            {
                imp.display(ticketList,ori,dest,doj);
                System.out.println();
                System.out.println("Enter Flight Number, Number of Tickets, Name, age, PhNumber, gender");
                String Fnum=sc1.nextLine();
                int x=sc.nextInt();
                int noOfTicks=sc.nextInt();
                String nam=sc1.nextLine();
                int ag=sc1.nextInt();
                long PhNumber=sc.nextLong();
                String gen1=sc1.nextLine();
                String gen=sc1.nextLine();
                String ben=sc1.nextLine();
                PNR Pobj=new PNR();
                long number=Pobj.generatePNR();
                passengerList.add(new Passenger(nam,ag,gen,PhNumber,noOfTicks,number,Fnum,type));
                imp.displayE_ticket(passengerList);
                try{
                    BufferedWriter bw=new BufferedWriter(new FileWriter("src/passengerDetails.txt"));
                    String ticks=String.valueOf(noOfTicks);
                    String ags=String.valueOf(ag);
                    String phNo=String.valueOf(PhNumber);
                    String nums=String.valueOf(number);
                    bw.write(nam);bw.write(",");bw.write(ags);bw.write(",");bw.write(gen);bw.write(",");bw.write(phNo);bw.write(",");bw.write(ticks);bw.write(",");
                    bw.write(nums);bw.write(",");bw.write(Fnum);bw.write(",");bw.write(String.valueOf(type));
                    bw.close();
                }
                catch(Exception e){System.out.println(e.getMessage());}
            }
            else System.out.println("No Flights are Available");
        }
        else if(service==2)
        {
            try
            {
                BufferedReader bf1=new BufferedReader(new FileReader("passengerDetails.txt"));
                String line;
                while((line=bf1.readLine())!=null)
                {
                    String[] str=line.split(",");
                    int ages=Integer.parseInt(str[1]);
                    int ticks=Integer.parseInt(str[4]);
                    long phNum=Long.parseLong(str[3]);
                    long pnrs=Long.parseLong(str[5]);
                    FlightType Flight=FlightType.valueOf(str[7]);
                    newPassenger.add(new Passenger(str[0], ages, str[2], phNum, ticks, pnrs, str[6], Flight));
                }
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
            System.out.println("Enter PNR: ");
            long verifyPNR=sc.nextLong();
            imp.displayBasedOnPNR(verifyPNR,newPassenger);
        }
        else
        {
            System.out.println("Enter PNR to cancel Tickets");
            long verify=sc.nextLong();
            imp.CancelTickets(verify,passengerList);
        }
    }
}
