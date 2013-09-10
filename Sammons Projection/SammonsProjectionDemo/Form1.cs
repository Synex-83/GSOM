using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace SammonsProjectionDemo
{
    public partial class frmProjection : Form
    {
        private int INPUT_DIMENSION = 0;
        private int NUMBER_OF_POINTS = 0;
        private const string FILE = @"CSV\Game-average.csv";
        private double[][] _inputData;

        public frmProjection()
        {
            InitializeComponent();
        }

        private void btnProject_Click(object sender, EventArgs e)
        {
            if (initializeProjection())
            {
                
                Bitmap bmp = CreateProjection();
                pbProjector.Image = bmp;
            }
        }

        private bool initializeProjection()
        {
            int dimension;
            bool check = true;

            if (int.TryParse(txtDimensions.Text.ToString(), out dimension))
            {
                INPUT_DIMENSION = dimension;
                check = check & true;
                ReadData();
                txtDataPoints.Text = _inputData.Length.ToString();
                NUMBER_OF_POINTS = int.Parse(_inputData.Length.ToString());
            }
            else
            {
                MessageBox.Show("Dimension entered is not an integer.");
                txtDimensions.Clear();
                check = check & false;
            }

            return check;
        }

        private void ReadData()
        {
            List<double[]> inputs = new List<double[]>();

            using (StreamReader sr = File.OpenText(FILE))
            {
                sr.ReadLine();

                while (!sr.EndOfStream)
                {
                    string line = sr.ReadLine();
                    string[] cols = line.Split(';');

                    double[] input = new double[INPUT_DIMENSION];
                    for (int i = 0; i < INPUT_DIMENSION; i++)
                        input[i] = double.Parse(cols[i + 1], NumberFormatInfo.InvariantInfo);
                    inputs.Add(input);
                }
            }

            _inputData = inputs.ToArray();
        }

        private Bitmap CreateProjection()
        {
            SammonsProjection projection = new SammonsProjection(
                _inputData,
                2,
                1000);
            projection.CreateMapping();

            // Create colors and labels - here a lazy version is shown, it should
            // be read from the data set in real applications ;)



            Color[] color = new Color[NUMBER_OF_POINTS];
            string[] labels = new string[NUMBER_OF_POINTS];
            for (int i = 0; i < NUMBER_OF_POINTS; i++)
            {
                color[i] = Color.Red;
                labels[i] = "1";
            }
            ////for (int i = 7; i < 12; i++)
            ////{
            ////    color[i] = Color.Green;
            ////    labels[i] = "2";
            ////}
            ////for (int i = 13; i < 18; i++)
            ////{
            ////    color[i] = Color.Blue;
            ////    labels[i] = "3";
            ////}

            ////for (int i = 19; i < 24; i++)
            ////{
            ////    color[i] = Color.Maroon;
            ////    labels[i] = "4";
            ////}

            SammonsProjectionPostProcess processing = new SammonsProjectionPostProcess(
                projection);
            processing.PointSize = 4;
            processing.FontSize = 8;
            return processing.CreateImage(750, 750, labels, color);
        }


    }
}
