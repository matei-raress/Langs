using Speed;
using System;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        private double[] _array;
        private Random _rand;
        private IMeasure _speedMeasure = new MeasuringMethods.DateTimeMeasure();
        private ISort _sortingMethod;
        public Form1()
        {
            InitializeComponent();
        }

        /// <summary>
        /// Functia activeaza butoanele de sortare
        /// </summary>
        private void EnableSortingButtons()
        {
            buttonBubble.Enabled = buttonQuick.Enabled = buttonShell.Enabled = buttonInsertion.Enabled = true;
        }

        /// <summary>
        /// Functia reseteaza barele de progres si valorile ultimelor sortari
        /// </summary>
        private void ResetSortingTimesAndProgressBars()
        {
            progressBarBubble.Value = progressBarQuick.Value = progressBarShell.Value = progressBarInsertion.Value = 0;
        }


        private void buttonSortedReverse_Click(object sender, EventArgs e)
        {
            _speedMeasure.BeginTest();
            long vecSize = Convert.ToInt64(textBoxVecLength.Text);
            int seed = Convert.ToInt32(textBoxSeedRandom.Text);
           
            _rand = new Random(seed);
            _array = new double[vecSize];
            for (int i = 0; i < vecSize; i++)
            {
                _array[i] = _rand.Next();
            }

            Array.Sort(_array);
            Array.Reverse(_array);

            double endTestTime = _speedMeasure.EndTest();
            richTextBoxSortOutput.Text += "Vector sortat descrescator generat in:" + endTestTime + " ms\n";

            EnableSortingButtons();
            ResetSortingTimesAndProgressBars();
    }


        private void buttonRandomVect_Click(object sender, EventArgs e)
        {
            _speedMeasure.BeginTest();
            long vecSize = Convert.ToInt64(textBoxVecLength.Text);
            int seed = Convert.ToInt32(textBoxSeedRandom.Text);

            _rand = new Random(seed);
            _array = new double[vecSize];
            for (int i = 0; i < vecSize; i++)
            {
                _array[i] = _rand.Next();
            }

            double endTestTime = _speedMeasure.EndTest();
            richTextBoxSortOutput.Text += "Vector aleator generat in:" + endTestTime + " ms\n";
                
            EnableSortingButtons();
            ResetSortingTimesAndProgressBars();
    }

        private void buttonSortedVectAsc_Click(object sender, EventArgs e)
        {
            _speedMeasure.BeginTest();
            long vecSize = Convert.ToInt64(textBoxVecLength.Text);
            int seed = Convert.ToInt32(textBoxSeedRandom.Text);

            _rand = new Random(seed);
            _array = new double[vecSize];
            for (int i = 0; i < vecSize; i++)
            {
                _array[i] = _rand.Next();
            }
            Array.Sort(_array);

            double endTestTime = _speedMeasure.EndTest();
            richTextBoxSortOutput.Text += "Vector sortat crescator generat in:" + endTestTime + " ms\n";

            EnableSortingButtons();
            ResetSortingTimesAndProgressBars();
            
        }

        private void radioButtonDateTime_CheckedChanged(object sender, EventArgs e)
        {
            ResetSortingTimesAndProgressBars();
            _speedMeasure = new MeasuringMethods.DateTimeMeasure();
        }

        private void radioButtonPerformanceCounter_CheckedChanged(object sender, EventArgs e)
        {
            ResetSortingTimesAndProgressBars();
            _speedMeasure = new MeasuringMethods.PerformanceCounterMeasure();
        }

        private void radioButtonStopwatch_CheckedChanged(object sender, EventArgs e)
        {
            ResetSortingTimesAndProgressBars();
            _speedMeasure = new MeasuringMethods.StopwatchMeasure();
        }

        private void buttonClearConsole_Click(object sender, EventArgs e)
        {
            ResetSortingTimesAndProgressBars();
            richTextBoxSortOutput.Clear();
        }

        private void buttonAbout_Click(object sender, EventArgs e)
        {
            MessageBox.Show("2023 - Matei Rares 1306A");
        }

        private void buttonQuick_Click(object sender, EventArgs e)
        {
            _sortingMethod = new QuickSort();
            _speedMeasure.BeginTest();
            _sortingMethod.Sort(_array);
            double time = _speedMeasure.EndTest();
            UpdateProgressBars(time, "quick");
            richTextBoxSortOutput.Text += "Vector sortat cu QuickSort in:" + time + " ms\n";
        }

        private void buttonShell_Click(object sender, EventArgs e)
        {
            _sortingMethod = new ShellSort();
            _speedMeasure.BeginTest();
            _sortingMethod.Sort(_array);
            double time = _speedMeasure.EndTest();
            UpdateProgressBars(time, "shell");
            richTextBoxSortOutput.Text += "Vector sortat cu ShellSort in:" + time + " ms\n";

        }

        private void buttonBubble_Click(object sender, EventArgs e)
        {
            _sortingMethod = new BubbleSort();
            _speedMeasure.BeginTest();
            _sortingMethod.Sort(_array);
            double time = _speedMeasure.EndTest();
            UpdateProgressBars(time, "bubble");
            richTextBoxSortOutput.Text += "Vector sortat cu BubbleSort in:" + time + " ms\n";

        }

        private void buttonInsertion_Click(object sender, EventArgs e)
        {
            _sortingMethod = new InsertionSort();
            _speedMeasure.BeginTest();
            _sortingMethod.Sort(_array);
            double time = _speedMeasure.EndTest();
            UpdateProgressBars(time, "insertion");
            richTextBoxSortOutput.Text += "Vector sortat cu InsertionSort in:" + time + " ms\n";
        }

        private void buttonClose_Click(object sender, EventArgs e)
        {
            Application.Exit();
            Environment.Exit(0);
        }

        /// <summary>
        /// Functia actualizaza procentul din barele de progres
        /// </summary>
        private void UpdateProgressBars(double time, string sortType)
        {

            switch (sortType) {
                case "bubble":
                    progressBarBubble.Value = (int)(100 / time * time);
                    break;
                case "quick":
                    progressBarQuick.Value = (int)(100 / time * time);
                    break;
                case "shell":
                    progressBarShell.Value = (int)(100 / time * time);
                    break;
                case "insertion":
                    progressBarInsertion.Value = (int)(100 / time * time);
                    break;

            }

        }
    }
}
