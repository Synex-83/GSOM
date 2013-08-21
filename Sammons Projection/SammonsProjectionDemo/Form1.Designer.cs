namespace SammonsProjectionDemo
{
    partial class frmProjection
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
            this.pbProjector = new System.Windows.Forms.PictureBox();
            this.btnProject = new System.Windows.Forms.Button();
            this.lblDimensions = new System.Windows.Forms.Label();
            this.txtDimensions = new System.Windows.Forms.TextBox();
            this.lblNumberOfRecords = new System.Windows.Forms.Label();
            this.txtDataPoints = new System.Windows.Forms.TextBox();
            ((System.ComponentModel.ISupportInitialize)(this.pbProjector)).BeginInit();
            this.SuspendLayout();
            // 
            // pbProjector
            // 
            this.pbProjector.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pbProjector.Location = new System.Drawing.Point(12, 5);
            this.pbProjector.Name = "pbProjector";
            this.pbProjector.Size = new System.Drawing.Size(750, 750);
            this.pbProjector.TabIndex = 0;
            this.pbProjector.TabStop = false;
            // 
            // btnProject
            // 
            this.btnProject.Location = new System.Drawing.Point(1006, 732);
            this.btnProject.Name = "btnProject";
            this.btnProject.Size = new System.Drawing.Size(75, 23);
            this.btnProject.TabIndex = 1;
            this.btnProject.Text = "Projection";
            this.btnProject.UseVisualStyleBackColor = true;
            this.btnProject.Click += new System.EventHandler(this.btnProject_Click);
            // 
            // lblDimensions
            // 
            this.lblDimensions.AutoSize = true;
            this.lblDimensions.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblDimensions.Location = new System.Drawing.Point(782, 34);
            this.lblDimensions.Name = "lblDimensions";
            this.lblDimensions.Size = new System.Drawing.Size(124, 17);
            this.lblDimensions.TabIndex = 2;
            this.lblDimensions.Text = "Dimension of Data";
            // 
            // txtDimensions
            // 
            this.txtDimensions.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtDimensions.Location = new System.Drawing.Point(912, 31);
            this.txtDimensions.Name = "txtDimensions";
            this.txtDimensions.Size = new System.Drawing.Size(169, 23);
            this.txtDimensions.TabIndex = 3;
            // 
            // lblNumberOfRecords
            // 
            this.lblNumberOfRecords.AutoSize = true;
            this.lblNumberOfRecords.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblNumberOfRecords.Location = new System.Drawing.Point(825, 87);
            this.lblNumberOfRecords.Name = "lblNumberOfRecords";
            this.lblNumberOfRecords.Size = new System.Drawing.Size(81, 17);
            this.lblNumberOfRecords.TabIndex = 4;
            this.lblNumberOfRecords.Text = "Data Points";
            // 
            // txtDataPoints
            // 
            this.txtDataPoints.Enabled = false;
            this.txtDataPoints.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtDataPoints.Location = new System.Drawing.Point(912, 84);
            this.txtDataPoints.Name = "txtDataPoints";
            this.txtDataPoints.Size = new System.Drawing.Size(169, 23);
            this.txtDataPoints.TabIndex = 5;
            this.txtDataPoints.Text = "0";
            this.txtDataPoints.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // frmProjection
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1093, 762);
            this.Controls.Add(this.txtDataPoints);
            this.Controls.Add(this.lblNumberOfRecords);
            this.Controls.Add(this.txtDimensions);
            this.Controls.Add(this.lblDimensions);
            this.Controls.Add(this.btnProject);
            this.Controls.Add(this.pbProjector);
            this.Name = "frmProjection";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Sammon\'s Projection Demo";
            ((System.ComponentModel.ISupportInitialize)(this.pbProjector)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox pbProjector;
        private System.Windows.Forms.Button btnProject;
        private System.Windows.Forms.Label lblDimensions;
        private System.Windows.Forms.TextBox txtDimensions;
        private System.Windows.Forms.Label lblNumberOfRecords;
        private System.Windows.Forms.TextBox txtDataPoints;
    }
}

