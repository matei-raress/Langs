using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Commons
{
    public interface IPresenter {
         void AddCity(City c);
        bool CityExists(string name);
        void ComputeRoute(string city1, string city2);

        void Exit();

        City GetCity(string name);

        void Init();
        void RemoveCity(string name);

    
    
    }
}
