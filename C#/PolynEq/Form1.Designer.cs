namespace WindowsFormsApp1
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.Calculeaza = new System.Windows.Forms.Button();
            this.buttonDespre = new System.Windows.Forms.Button();
            this.buttonIesire = new System.Windows.Forms.Button();
            this.radioButtonPolyn = new System.Windows.Forms.RadioButton();
            this.coef1 = new System.Windows.Forms.TextBox();
            this.coef2 = new System.Windows.Forms.TextBox();
            this.coef3 = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.textBox4 = new System.Windows.Forms.TextBox();
            this.radioButtonTrig = new System.Windows.Forms.RadioButton();
            this.label6 = new System.Windows.Forms.Label();
            this.comboBox1 = new System.Windows.Forms.ComboBox();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.textBox5 = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.groupBox3.SuspendLayout();
            this.SuspendLayout();
            // 
            // Calculeaza
            // 
            this.Calculeaza.Location = new System.Drawing.Point(394, 105);
            this.Calculeaza.Name = "Calculeaza";
            this.Calculeaza.Size = new System.Drawing.Size(75, 23);
            this.Calculeaza.TabIndex = 0;
            this.Calculeaza.Text = "Calculeaza";
            this.Calculeaza.UseVisualStyleBackColor = true;
            this.Calculeaza.Click += new System.EventHandler(this.buttonCalculeaza_Click);
            // 
            // buttonDespre
            // 
            this.buttonDespre.Location = new System.Drawing.Point(394, 152);
            this.buttonDespre.Name = "buttonDespre";
            this.buttonDespre.Size = new System.Drawing.Size(75, 23);
            this.buttonDespre.TabIndex = 1;
            this.buttonDespre.Text = "Despre";
            this.buttonDespre.UseVisualStyleBackColor = true;
            this.buttonDespre.Click += new System.EventHandler(this.buttonDespre_Click);
            // 
            // buttonIesire
            // 
            this.buttonIesire.Location = new System.Drawing.Point(394, 206);
            this.buttonIesire.Name = "buttonIesire";
            this.buttonIesire.Size = new System.Drawing.Size(75, 23);
            this.buttonIesire.TabIndex = 2;
            this.buttonIesire.Text = "Iesire";
            this.buttonIesire.UseVisualStyleBackColor = true;
            this.buttonIesire.Click += new System.EventHandler(this.buttonIesire_Click);
            // 
            // radioButtonPolyn
            // 
            this.radioButtonPolyn.AutoSize = true;
            this.radioButtonPolyn.Location = new System.Drawing.Point(24, 24);
            this.radioButtonPolyn.Name = "radioButtonPolyn";
            this.radioButtonPolyn.Size = new System.Drawing.Size(116, 17);
            this.radioButtonPolyn.TabIndex = 3;
            this.radioButtonPolyn.TabStop = true;
            this.radioButtonPolyn.Text = "Ecuatie polinomiala";
            this.radioButtonPolyn.UseVisualStyleBackColor = true;
            this.radioButtonPolyn.CheckedChanged += new System.EventHandler(this.radioButtonPolyn_CheckedChanged);
            // 
            // coef1
            // 
            this.coef1.Location = new System.Drawing.Point(24, 47);
            this.coef1.Name = "coef1";
            this.coef1.Size = new System.Drawing.Size(50, 20);
            this.coef1.TabIndex = 5;
            this.coef1.TextChanged += new System.EventHandler(this.coef1_TextChanged);
            // 
            // coef2
            // 
            this.coef2.Location = new System.Drawing.Point(116, 47);
            this.coef2.Name = "coef2";
            this.coef2.Size = new System.Drawing.Size(50, 20);
            this.coef2.TabIndex = 6;
            this.coef2.TextChanged += new System.EventHandler(this.textBox2_TextChanged);
            // 
            // coef3
            // 
            this.coef3.Location = new System.Drawing.Point(208, 47);
            this.coef3.Name = "coef3";
            this.coef3.Size = new System.Drawing.Size(50, 20);
            this.coef3.TabIndex = 7;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(77, 50);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(33, 13);
            this.label1.TabIndex = 8;
            this.label1.Text = "x^2 +";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(172, 50);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(30, 13);
            this.label2.TabIndex = 9;
            this.label2.Text = "x    +";
            this.label2.Click += new System.EventHandler(this.label2_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(264, 50);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(40, 13);
            this.label3.TabIndex = 10;
            this.label3.Text = "    =   0";
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.coef2);
            this.groupBox1.Controls.Add(this.label3);
            this.groupBox1.Controls.Add(this.radioButtonPolyn);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Controls.Add(this.coef1);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Controls.Add(this.coef3);
            this.groupBox1.Location = new System.Drawing.Point(12, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(364, 87);
            this.groupBox1.TabIndex = 11;
            this.groupBox1.TabStop = false;
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.comboBox1);
            this.groupBox2.Controls.Add(this.textBox4);
            this.groupBox2.Controls.Add(this.radioButtonTrig);
            this.groupBox2.Controls.Add(this.label6);
            this.groupBox2.Location = new System.Drawing.Point(12, 105);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(364, 87);
            this.groupBox2.TabIndex = 12;
            this.groupBox2.TabStop = false;
            // 
            // textBox4
            // 
            this.textBox4.Location = new System.Drawing.Point(170, 48);
            this.textBox4.Name = "textBox4";
            this.textBox4.Size = new System.Drawing.Size(50, 20);
            this.textBox4.TabIndex = 6;
            // 
            // radioButtonTrig
            // 
            this.radioButtonTrig.AutoSize = true;
            this.radioButtonTrig.Location = new System.Drawing.Point(24, 24);
            this.radioButtonTrig.Name = "radioButtonTrig";
            this.radioButtonTrig.Size = new System.Drawing.Size(130, 17);
            this.radioButtonTrig.TabIndex = 3;
            this.radioButtonTrig.TabStop = true;
            this.radioButtonTrig.Text = "Ecuatie trigonometrica";
            this.radioButtonTrig.UseVisualStyleBackColor = true;
            this.radioButtonTrig.CheckedChanged += new System.EventHandler(this.radioButtonTrig_CheckedChanged);
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(151, 52);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(13, 13);
            this.label6.TabIndex = 8;
            this.label6.Text = "=";
            this.label6.Click += new System.EventHandler(this.label6_Click);
            // 
            // comboBox1
            // 
            this.comboBox1.FormattingEnabled = true;
            this.comboBox1.Items.AddRange(new object[] {
            "sin(x)"});
            this.comboBox1.Location = new System.Drawing.Point(24, 47);
            this.comboBox1.Name = "comboBox1";
            this.comboBox1.Size = new System.Drawing.Size(121, 21);
            this.comboBox1.TabIndex = 9;
            this.comboBox1.SelectedIndexChanged += new System.EventHandler(this.comboBox1_SelectedIndexChanged);
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.label4);
            this.groupBox3.Controls.Add(this.textBox5);
            this.groupBox3.Location = new System.Drawing.Point(12, 198);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(364, 87);
            this.groupBox3.TabIndex = 13;
            this.groupBox3.TabStop = false;
            // 
            // textBox5
            // 
            this.textBox5.Location = new System.Drawing.Point(24, 48);
            this.textBox5.Name = "textBox5";
            this.textBox5.Size = new System.Drawing.Size(319, 20);
            this.textBox5.TabIndex = 6;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(21, 18);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(42, 13);
            this.label4.TabIndex = 7;
            this.label4.Text = "Solutie:";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(480, 293);
            this.Controls.Add(this.groupBox3);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.buttonIesire);
            this.Controls.Add(this.buttonDespre);
            this.Controls.Add(this.Calculeaza);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            this.groupBox3.ResumeLayout(false);
            this.groupBox3.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button Calculeaza;
        private System.Windows.Forms.Button buttonDespre;
        private System.Windows.Forms.Button buttonIesire;
        private System.Windows.Forms.RadioButton radioButtonPolyn;
        private System.Windows.Forms.TextBox coef1;
        private System.Windows.Forms.TextBox coef2;
        private System.Windows.Forms.TextBox coef3;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.TextBox textBox4;
        private System.Windows.Forms.RadioButton radioButtonTrig;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.ComboBox comboBox1;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.TextBox textBox5;
        private System.Windows.Forms.Label label4;
    }
}

