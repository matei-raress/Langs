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
            bool is_prime = true;


            if (n == 0 || n == 1)
            {
                is_prime = false;
            }
            else if (n == 2)
            {
                is_prime = true;
            }
            else if (n % 2 == 0)
            {
                is_prime = false;
            }
            else
            {
                for (int i = 3; i <= Math.Sqrt(n); i+=2)
                {
                    if (n % i == 0)
                    {
                        is_prime = false;
                        return is_prime;
                    }
                }
            }

            return is_prime;

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
