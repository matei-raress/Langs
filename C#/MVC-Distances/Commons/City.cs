using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Commons
{
    
    public class City
    {
        public readonly double Latitude;
        public readonly double Longitude;
        public readonly string Name;

        public City() { }

        public City(string name, double latitude, double longitude) { 
            Name = name;
            Latitude = latitude;   
            Longitude = longitude;
        
        }

    }

}
