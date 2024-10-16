using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Runtime.Remoting.Contexts;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Xml.Linq;
using creatori;
using produse;

namespace Factory
{
    public partial class Form1 : Form
    {
        private OpenFileDialog openFileDialog;
        private TabControl tabControl;

        public Form1()
        {
            InitializeComponent();
            openFileDialog = new OpenFileDialog();
            tabControl = new TabControl();

        }

        private void openMenuButton_Click(object sender, EventArgs e)
        {
            Document doc  ;
            openFileDialog = new OpenFileDialog();
            openFileDialog.Filter = "Text documents (*.txd)|*.txd|Graphic documents (*.grd)|*.grd";//indexi: 1 txd, 2 grd
          
            
            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                    switch (openFileDialog.FilterIndex)
                    {
                        case 1:
                            doc = new TextDocument(openFileDialog.FileName);
                            break;
                        case 2:
                            doc = new GraphicDocument(openFileDialog.FileName);
                            break;
                        default:
                            return;
                    }
                tabControl1.Controls.Clear();
                foreach (Page p in doc.Pages)
                {
                    TabPage tp = new TabPage(p.Name);
                    p.Content.Dock = DockStyle.Fill;
                    tp.Controls.Add(p.Content);
                    tabControl1.TabPages.Add(tp);
                }
            }
        }

        private void aboutMenuButton_Click(object sender, EventArgs e)
        {

        }

    }
}
