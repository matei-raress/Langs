using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.Button;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void label6_Click(object sender, EventArgs e)
        {

        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void radioButtonPolyn_CheckedChanged(object sender, EventArgs e)
        {
            if (radioButtonPolyn.Checked == true) {
                radioButtonTrig.Checked = false;
            
            }

        }

        private void radioButtonTrig_CheckedChanged(object sender, EventArgs e)
        {
            if (radioButtonTrig.Checked == true)
            {
                radioButtonPolyn.Checked = false;
            }


        }

        private void buttonCalculeaza_Click(object sender, EventArgs e)
        {
            if (radioButtonPolyn.Checked == true)
            {
                try
                {
                    IEquation eq = new PolyEquation(Double.Parse(coef1.Text),
                        Double.Parse(coef2.Text),
                        Double.Parse(coef3.Text));
                    textBox5.Text = eq.Solve();
                }
                catch (PolyException poly_ex)
                {
                    MessageBox.Show(poly_ex.Message);
                }
                catch (System.FormatException format_ex)
                {
                    MessageBox.Show(format_ex.Message);
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message);
                }
            }
            if (radioButtonTrig.Checked == true)
            {
                try
                {
                    TrigEquation.TrigonometricFunction tf = (TrigEquation.TrigonometricFunction)comboBox1.SelectedIndex;
                    IEquation eq = new TrigEquation(tf, Double.Parse(textBox4.Text));
                    textBox5.Text = eq.Solve();
                }
                catch (TrigException trig_ex)
                {
                    MessageBox.Show(trig_ex.Message);
                }
                catch (System.FormatException format_ex)
                {
                    MessageBox.Show(format_ex.Message);
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message);
                }
            }

        }

        private void buttonDespre_Click(object sender, EventArgs e)
        {
            MessageBox.Show("Selectati o singura ecuatie(polinomiala sau trigonometrica) si apasati butonul de calculare pentru rezultat ");

        }

        private void buttonIesire_Click(object sender, EventArgs e)
        {
            System.Windows.Forms.Application.Exit();

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void coef1_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
