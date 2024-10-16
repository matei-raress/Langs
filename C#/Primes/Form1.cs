using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Net;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        public int oki;
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            
            if (oki == 1)
            {
                textBox2.Text = String.Empty;
                oki = 0;
            }
            int n=0;
            try
            {
                 n = Convert.ToInt16(textBox1.Text);
            }
            catch {
                MessageBox.Show("Argument invalid");
                return;
            }

            if (n % 2 == 0 && n >= 4)
            {
                for (int i = 1; i < n; i++)
                {
                    
                    if (Prime.Prime.IsPrime(i) && Prime.Prime.IsPrime(n - i))
                    {

                        string stringu = " " + i + " + " + (n - i);
                        textBox2.AppendText(stringu);
                        oki = 1;
                        return;
                    }
                }
            }
            else if (n % 2 == 0 && n <= 4)
            {
                MessageBox.Show("Numarul trebuie sa fie mai mare decat 4");

            }
            else if (n % 2 != 0 && n >= 7)
            {
                for (int i = 1; i < n; i++)
                {
                    for (int j = 1; j < n - 1; j++)
                    {

                        if (Prime.Prime.IsPrime(i) && Prime.Prime.IsPrime(j) && Prime.Prime.IsPrime(n - i - j))
                        {
                            string stringu = i + " + " + j + "+" + (n - j - i);
                            textBox2.AppendText(stringu);
                            oki = 1;
                            return;
                        }
                    }
                }
            }
            else { MessageBox.Show("Numarul trebuie sa fie mai mare decat 7"); }


        }

        private void button2_Click(object sender, EventArgs e)
        {
         
        }

        private void button3_Click(object sender, EventArgs e)
        {
            

        }
    }
}