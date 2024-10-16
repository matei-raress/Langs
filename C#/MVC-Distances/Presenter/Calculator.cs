using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.XPath;
using Commons;
using static System.Math;

namespace PresenterInfo
{
    internal class Calculator
    {

        public static double Distance(City c1, City c2)
        {
            double lat1 = (c1.Latitude / 180) * 3.14159265359;
            double lat2 = (c2.Latitude / 180) * 3.14159265359;
            double long1 = (c1.Longitude / 180) * 3.14159265359;
            double long2 = (c2.Longitude / 180) * 3.14159265359;

            double km = Acos(Sin(lat1) * Sin(lat2) + Cos(lat1) * Cos(lat2) * Cos(long2 - long1)) * 6371;


            return km;
        }

        public static double Cost(double distance)
        {
            return 5.0 * (5.0 + distance / 30.0);
        }
    }
}
