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
        private const int INPUT_DIMENSION = 24;
        private const string FILE = @"E:\workspace\GSOM\Sammons Projection\SammonsProjectionDemo\data.csv";
        private double[][] _inputData;

        public frmProjection()
        {
            InitializeComponent();
        }

        private void btnProject_Click(object sender, EventArgs e)
        {
            ReadData();
            Bitmap bmp = CreateProjection();
            pbProjector.Image = bmp;
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
            Color[] color = new Color[13];
            string[] labels = new string[13];
            for (int i = 0; i < 4; i++)
            {
                color[i] = Color.Red;
                labels[i] = "1";
            }
            for (int i = 4; i < 8; i++)
            {
                color[i] = Color.Green;
                labels[i] = "2";
            }
            for (int i = 8; i < 11; i++)
            {
                color[i] = Color.Blue;
                labels[i] = "3";
            }

            for (int i = 11; i < 13; i++)
            {
                color[i] = Color.Yellow;
                labels[i] = "4";
            }

            SammonsProjectionPostProcess processing = new SammonsProjectionPostProcess(
                projection);
            processing.PointSize = 4;
            processing.FontSize = 8;
            return processing.CreateImage(725, 544, labels, color);
        }
    }
}
