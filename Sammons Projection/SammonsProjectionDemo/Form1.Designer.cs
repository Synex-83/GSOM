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
            ((System.ComponentModel.ISupportInitialize)(this.pbProjector)).BeginInit();
            this.SuspendLayout();
            // 
            // pbProjector
            // 
            this.pbProjector.Location = new System.Drawing.Point(12, 12);
            this.pbProjector.Name = "pbProjector";
            this.pbProjector.Size = new System.Drawing.Size(725, 544);
            this.pbProjector.TabIndex = 0;
            this.pbProjector.TabStop = false;
            // 
            // btnProject
            // 
            this.btnProject.Location = new System.Drawing.Point(745, 533);
            this.btnProject.Name = "btnProject";
            this.btnProject.Size = new System.Drawing.Size(75, 23);
            this.btnProject.TabIndex = 1;
            this.btnProject.Text = "Projection";
            this.btnProject.UseVisualStyleBackColor = true;
            this.btnProject.Click += new System.EventHandler(this.btnProject_Click);
            // 
            // frmProjection
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(832, 568);
            this.Controls.Add(this.btnProject);
            this.Controls.Add(this.pbProjector);
            this.Name = "frmProjection";
            this.Text = "Sammon\'s Projection Demo";
            ((System.ComponentModel.ISupportInitialize)(this.pbProjector)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.PictureBox pbProjector;
        private System.Windows.Forms.Button btnProject;
    }
}

