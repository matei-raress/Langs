using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Prime
{
    public class Prime
    {

        public static bool IsPrime(int n)
        {
            if (n < 0)
            {
                throw new Exception("Numarul este negativ");
            }
            if (n <= 1) return false;
            if (n == 2) return true;
            if (n % 2 == 0) return false;
            for (int i = 3; i <= n / 2; i += 2)
            {
                if (n % i == 0)
                {
                    return false;
                }
            }
            return true;
        }

        public static int NumaraPrime(int nr)
        {
            int n = 0;
            for (int i = 2; i <= nr; i++)
            {
                if (IsPrime(i))
                {
                    n++;
                }
            }
            return n;
        }
    }
}
