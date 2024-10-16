class ProgramareFilm(Client:Client,film:Film) {
    private var client=Client.getCerere()
    private var film=film

    public fun check():Boolean{
        if(client.getName()==film.getName())
        {
            if(client.getHour()==film.getHour())
            {
                if(client.getSeats() > film.getSeats())
                {
                    print("Nu mai sunt locuri")
                    return false
                }
                else
                {
                    return true
                }
            }
            print("Nu exista filmul la ora cautata")
            return false
        }
        print("Nu s-a gasit filmul cu numele " + client.getName())
            return false
    }
    public fun getPrice():Double{
        return (client.getSeats()*film.getPrice())
    }
}