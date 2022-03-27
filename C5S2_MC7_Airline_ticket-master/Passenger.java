class Passenger
{
    String name;
    int age;
    String gender;
    private long phoneNo;
    int noOfTickets;
    long pnr;
    FlightType flightType;
    String Fnum;
    public Passenger(String name, int age, String gender, long phoneNo, int noOfTickets, long pnr, String Fnum, FlightType flightType)
    {
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.phoneNo=phoneNo;
        this.noOfTickets=noOfTickets;
        this.pnr=pnr;
        this.Fnum=Fnum;
        this.flightType=flightType;
    }
    public long getPhoneNo()
    {
        return phoneNo;
    }
}
