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
            this.buttonRandomVect = new System.Windows.Forms.Button();
            this.buttonSortedVectAsc = new System.Windows.Forms.Button();
            this.buttonSortedVecDesc = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.textBoxSeedRandom = new System.Windows.Forms.TextBox();
            this.textBoxVecLength = new System.Windows.Forms.TextBox();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.radioButtonStopwatch = new System.Windows.Forms.RadioButton();
            this.radioButtonPerformanceCounter = new System.Windows.Forms.RadioButton();
            this.radioButtonDateTime = new System.Windows.Forms.RadioButton();
            this.buttonQuick = new System.Windows.Forms.Button();
            this.buttonShell = new System.Windows.Forms.Button();
            this.buttonBubble = new System.Windows.Forms.Button();
            this.progressBarQuick = new System.Windows.Forms.ProgressBar();
            this.progressBarShell = new System.Windows.Forms.ProgressBar();
            this.progressBarBubble = new System.Windows.Forms.ProgressBar();
            this.richTextBoxSortOutput = new System.Windows.Forms.RichTextBox();
            this.buttonClearConsole = new System.Windows.Forms.Button();
            this.buttonAbout = new System.Windows.Forms.Button();
            this.buttonClose = new System.Windows.Forms.Button();
            this.buttonInsertion = new System.Windows.Forms.Button();
            this.progressBarInsertion = new System.Windows.Forms.ProgressBar();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // buttonRandomVect
            // 
            this.buttonRandomVect.Location = new System.Drawing.Point(12, 73);
            this.buttonRandomVect.Name = "buttonRandomVect";
            this.buttonRandomVect.Size = new System.Drawing.Size(131, 52);
            this.buttonRandomVect.TabIndex = 0;
            this.buttonRandomVect.Text = "Genereaza vector aleator";
            this.buttonRandomVect.UseVisualStyleBackColor = true;
            this.buttonRandomVect.Click += new System.EventHandler(this.buttonRandomVect_Click);
            // 
            // buttonSortedVectAsc
            // 
            this.buttonSortedVectAsc.Location = new System.Drawing.Point(163, 73);
            this.buttonSortedVectAsc.Name = "buttonSortedVectAsc";
            this.buttonSortedVectAsc.Size = new System.Drawing.Size(136, 52);
            this.buttonSortedVectAsc.TabIndex = 1;
            this.buttonSortedVectAsc.Text = "Genereaza vector sortat crescator";
            this.buttonSortedVectAsc.UseVisualStyleBackColor = true;
            this.buttonSortedVectAsc.Click += new System.EventHandler(this.buttonSortedVectAsc_Click);
            // 
            // buttonSortedVecDesc
            // 
            this.buttonSortedVecDesc.Location = new System.Drawing.Point(305, 73);
            this.buttonSortedVecDesc.Name = "buttonSortedVecDesc";
            this.buttonSortedVecDesc.Size = new System.Drawing.Size(126, 52);
            this.buttonSortedVecDesc.TabIndex = 2;
            this.buttonSortedVecDesc.Text = "Genereaza vector sortat descrescator";
            this.buttonSortedVecDesc.UseVisualStyleBackColor = true;
            this.buttonSortedVecDesc.Click += new System.EventHandler(this.buttonSortedReverse_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(9, 33);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(35, 13);
            this.label1.TabIndex = 3;
            this.label1.Text = "Seed ";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(219, 33);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(80, 13);
            this.label2.TabIndex = 4;
            this.label2.Text = "Lungime vector";
            // 
            // textBoxSeedRandom
            // 
            this.textBoxSeedRandom.Location = new System.Drawing.Point(50, 30);
            this.textBoxSeedRandom.Name = "textBoxSeedRandom";
            this.textBoxSeedRandom.Size = new System.Drawing.Size(62, 20);
            this.textBoxSeedRandom.TabIndex = 5;
            this.textBoxSeedRandom.Text = "1234";
            // 
            // textBoxVecLength
            // 
            this.textBoxVecLength.Location = new System.Drawing.Point(305, 26);
            this.textBoxVecLength.Name = "textBoxVecLength";
            this.textBoxVecLength.Size = new System.Drawing.Size(100, 20);
            this.textBoxVecLength.TabIndex = 6;
            this.textBoxVecLength.Text = "10000";
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.radioButtonStopwatch);
            this.groupBox1.Controls.Add(this.radioButtonPerformanceCounter);
            this.groupBox1.Controls.Add(this.radioButtonDateTime);
            this.groupBox1.Location = new System.Drawing.Point(13, 149);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(418, 63);
            this.groupBox1.TabIndex = 7;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Metoda de masurare";
            // 
            // radioButtonStopwatch
            // 
            this.radioButtonStopwatch.AutoSize = true;
            this.radioButtonStopwatch.Location = new System.Drawing.Point(316, 32);
            this.radioButtonStopwatch.Name = "radioButtonStopwatch";
            this.radioButtonStopwatch.Size = new System.Drawing.Size(76, 17);
            this.radioButtonStopwatch.TabIndex = 2;
            this.radioButtonStopwatch.Text = "Stopwatch";
            this.radioButtonStopwatch.UseVisualStyleBackColor = true;
            this.radioButtonStopwatch.CheckedChanged += new System.EventHandler(this.radioButtonStopwatch_CheckedChanged);
            // 
            // radioButtonPerformanceCounter
            // 
            this.radioButtonPerformanceCounter.AutoSize = true;
            this.radioButtonPerformanceCounter.Location = new System.Drawing.Point(150, 32);
            this.radioButtonPerformanceCounter.Name = "radioButtonPerformanceCounter";
            this.radioButtonPerformanceCounter.Size = new System.Drawing.Size(125, 17);
            this.radioButtonPerformanceCounter.TabIndex = 1;
            this.radioButtonPerformanceCounter.Text = "Performance Counter";
            this.radioButtonPerformanceCounter.UseVisualStyleBackColor = true;
            this.radioButtonPerformanceCounter.CheckedChanged += new System.EventHandler(this.radioButtonPerformanceCounter_CheckedChanged);
            // 
            // radioButtonDateTime
            // 
            this.radioButtonDateTime.AutoSize = true;
            this.radioButtonDateTime.Checked = true;
            this.radioButtonDateTime.Location = new System.Drawing.Point(21, 32);
            this.radioButtonDateTime.Name = "radioButtonDateTime";
            this.radioButtonDateTime.Size = new System.Drawing.Size(71, 17);
            this.radioButtonDateTime.TabIndex = 0;
            this.radioButtonDateTime.TabStop = true;
            this.radioButtonDateTime.Text = "DateTime";
            this.radioButtonDateTime.UseVisualStyleBackColor = true;
            this.radioButtonDateTime.CheckedChanged += new System.EventHandler(this.radioButtonDateTime_CheckedChanged);
            // 
            // buttonQuick
            // 
            this.buttonQuick.Enabled = false;
            this.buttonQuick.Location = new System.Drawing.Point(13, 242);
            this.buttonQuick.Name = "buttonQuick";
            this.buttonQuick.Size = new System.Drawing.Size(75, 23);
            this.buttonQuick.TabIndex = 8;
            this.buttonQuick.Text = "QuickSort";
            this.buttonQuick.UseVisualStyleBackColor = true;
            this.buttonQuick.Click += new System.EventHandler(this.buttonQuick_Click);
            // 
            // buttonShell
            // 
            this.buttonShell.Enabled = false;
            this.buttonShell.Location = new System.Drawing.Point(13, 281);
            this.buttonShell.Name = "buttonShell";
            this.buttonShell.Size = new System.Drawing.Size(75, 23);
            this.buttonShell.TabIndex = 9;
            this.buttonShell.Text = "ShellSort";
            this.buttonShell.UseVisualStyleBackColor = true;
            this.buttonShell.Click += new System.EventHandler(this.buttonShell_Click);
            // 
            // buttonBubble
            // 
            this.buttonBubble.Enabled = false;
            this.buttonBubble.Location = new System.Drawing.Point(13, 323);
            this.buttonBubble.Name = "buttonBubble";
            this.buttonBubble.Size = new System.Drawing.Size(75, 23);
            this.buttonBubble.TabIndex = 10;
            this.buttonBubble.Text = "BubbleSort";
            this.buttonBubble.UseVisualStyleBackColor = true;
            this.buttonBubble.Click += new System.EventHandler(this.buttonBubble_Click);
            // 
            // progressBarQuick
            // 
            this.progressBarQuick.Location = new System.Drawing.Point(112, 241);
            this.progressBarQuick.Name = "progressBarQuick";
            this.progressBarQuick.Size = new System.Drawing.Size(319, 23);
            this.progressBarQuick.TabIndex = 11;
            // 
            // progressBarShell
            // 
            this.progressBarShell.Location = new System.Drawing.Point(112, 281);
            this.progressBarShell.Name = "progressBarShell";
            this.progressBarShell.Size = new System.Drawing.Size(319, 23);
            this.progressBarShell.TabIndex = 12;
            // 
            // progressBarBubble
            // 
            this.progressBarBubble.Location = new System.Drawing.Point(112, 323);
            this.progressBarBubble.Name = "progressBarBubble";
            this.progressBarBubble.Size = new System.Drawing.Size(319, 23);
            this.progressBarBubble.TabIndex = 13;
            // 
            // richTextBoxSortOutput
            // 
            this.richTextBoxSortOutput.Location = new System.Drawing.Point(13, 400);
            this.richTextBoxSortOutput.Name = "richTextBoxSortOutput";
            this.richTextBoxSortOutput.Size = new System.Drawing.Size(418, 151);
            this.richTextBoxSortOutput.TabIndex = 14;
            this.richTextBoxSortOutput.Text = "";
            // 
            // buttonClearConsole
            // 
            this.buttonClearConsole.Location = new System.Drawing.Point(30, 571);
            this.buttonClearConsole.Name = "buttonClearConsole";
            this.buttonClearConsole.Size = new System.Drawing.Size(75, 23);
            this.buttonClearConsole.TabIndex = 15;
            this.buttonClearConsole.Text = "Sterge";
            this.buttonClearConsole.UseVisualStyleBackColor = true;
            this.buttonClearConsole.Click += new System.EventHandler(this.buttonClearConsole_Click);
            // 
            // buttonAbout
            // 
            this.buttonAbout.Location = new System.Drawing.Point(181, 571);
            this.buttonAbout.Name = "buttonAbout";
            this.buttonAbout.Size = new System.Drawing.Size(75, 23);
            this.buttonAbout.TabIndex = 16;
            this.buttonAbout.Text = "Despre";
            this.buttonAbout.UseVisualStyleBackColor = true;
            this.buttonAbout.Click += new System.EventHandler(this.buttonAbout_Click);
            // 
            // buttonClose
            // 
            this.buttonClose.Location = new System.Drawing.Point(329, 571);
            this.buttonClose.Name = "buttonClose";
            this.buttonClose.Size = new System.Drawing.Size(75, 23);
            this.buttonClose.TabIndex = 17;
            this.buttonClose.Text = "Inchide";
            this.buttonClose.UseVisualStyleBackColor = true;
            this.buttonClose.Click += new System.EventHandler(this.buttonClose_Click);
            // 
            // buttonInsertion
            // 
            this.buttonInsertion.Enabled = false;
            this.buttonInsertion.Location = new System.Drawing.Point(13, 365);
            this.buttonInsertion.Name = "buttonInsertion";
            this.buttonInsertion.Size = new System.Drawing.Size(75, 23);
            this.buttonInsertion.TabIndex = 18;
            this.buttonInsertion.Text = "InsertionSort";
            this.buttonInsertion.UseVisualStyleBackColor = true;
            this.buttonInsertion.Click += new System.EventHandler(this.buttonInsertion_Click);
            // 
            // progressBarInsertion
            // 
            this.progressBarInsertion.Location = new System.Drawing.Point(112, 365);
            this.progressBarInsertion.Name = "progressBarInsertion";
            this.progressBarInsertion.Size = new System.Drawing.Size(319, 23);
            this.progressBarInsertion.TabIndex = 19;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(449, 610);
            this.Controls.Add(this.progressBarInsertion);
            this.Controls.Add(this.buttonInsertion);
            this.Controls.Add(this.buttonClose);
            this.Controls.Add(this.buttonAbout);
            this.Controls.Add(this.buttonClearConsole);
            this.Controls.Add(this.richTextBoxSortOutput);
            this.Controls.Add(this.progressBarBubble);
            this.Controls.Add(this.progressBarShell);
            this.Controls.Add(this.progressBarQuick);
            this.Controls.Add(this.buttonBubble);
            this.Controls.Add(this.buttonShell);
            this.Controls.Add(this.buttonQuick);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.textBoxVecLength);
            this.Controls.Add(this.textBoxSeedRandom);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.buttonSortedVecDesc);
            this.Controls.Add(this.buttonSortedVectAsc);
            this.Controls.Add(this.buttonRandomVect);
            this.Name = "Form1";
            this.Text = "Form1";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button buttonRandomVect;
        private System.Windows.Forms.Button buttonSortedVectAsc;
        private System.Windows.Forms.Button buttonSortedVecDesc;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox textBoxSeedRandom;
        private System.Windows.Forms.TextBox textBoxVecLength;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.RadioButton radioButtonStopwatch;
        private System.Windows.Forms.RadioButton radioButtonPerformanceCounter;
        private System.Windows.Forms.RadioButton radioButtonDateTime;
        private System.Windows.Forms.Button buttonQuick;
        private System.Windows.Forms.Button buttonShell;
        private System.Windows.Forms.Button buttonBubble;
        private System.Windows.Forms.ProgressBar progressBarQuick;
        private System.Windows.Forms.ProgressBar progressBarShell;
        private System.Windows.Forms.ProgressBar progressBarBubble;
        private System.Windows.Forms.RichTextBox richTextBoxSortOutput;
        private System.Windows.Forms.Button buttonClearConsole;
        private System.Windows.Forms.Button buttonAbout;
        private System.Windows.Forms.Button buttonClose;
        private System.Windows.Forms.Button buttonInsertion;
        private System.Windows.Forms.ProgressBar progressBarInsertion;
    }
}

