package com.ayowainc.quizbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class worldclasscertificate extends AppCompatActivity {

    private String Subject;
    private Button btnDownloadCertificate;
    private ConstraintLayout worldClassCertificateCL;
    TextView worldClassCertificate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worldclasscertificate);
        worldClassCertificate = findViewById(R.id.worldClassCertificate);
        btnDownloadCertificate = findViewById(R.id.btnDownloadCertificate);
        worldClassCertificateCL = findViewById(R.id.worldClassCertificateCL);

        Intent intent = getIntent();
        if (intent.hasExtra("FRACTIONAL")){
            Subject = intent.getStringExtra("FRACTIONAL");
        }
        if (intent.hasExtra("LOGICAL")){
            Subject = intent.getStringExtra("LOGICAL");
        }
        if (intent.hasExtra("REASONING")){
            Subject = intent.getStringExtra("REASONING");
        }
        if (intent.hasExtra("APTITUDE")){
            Subject = intent.getStringExtra("APTITUDE");
        }

        if (Objects.equals(Subject, "fractional")) {
            worldClassCertificate.setText("Fractional Expert Certificate");
        } else if (Objects.equals(Subject, "logical")) {
            worldClassCertificate.setText("Logical Expert Certificate");
        } else if (Objects.equals(Subject, "reasoning")) {
            worldClassCertificate.setText("Reasoning Expert Certificate");
        } else if (Objects.equals(Subject, "aptitude")){
            worldClassCertificate.setText("Aptitude Expert Certificate");
        }

        btnDownloadCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Objects.equals(Subject, "fractional")) {
                    createAndSavePdf("FractionalExpertCertificate.pdf");
                } else if (Objects.equals(Subject, "logical")) {
                    createAndSavePdf("LogicalExpertCertificate.pdf");
                } else if (Objects.equals(Subject, "reasoning")) {
                    createAndSavePdf("ReasoningExpertCertificate.pdf");
                } else if (Objects.equals(Subject, "aptitude")){
                    createAndSavePdf("AptitudeExpertCertificate.pdf");
                }
            }
        });

    }

    private void createAndSavePdf(String pdf) {
        // Create a new PdfDocument
        PdfDocument document = new PdfDocument();

        // Create a PageInfo
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(worldClassCertificateCL.getWidth(), worldClassCertificateCL.getHeight(), 1).create();

        // Start a new page
        PdfDocument.Page page = document.startPage(pageInfo);

        // Draw the view on the page
        worldClassCertificateCL.draw(page.getCanvas());

        // Finish the page
        document.finishPage(page);

        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        // Create a file to save the PDF
        File file = new File(downloadsDir, pdf);

        try {
            // Write the PDF content to the file
            document.writeTo(new FileOutputStream(file));

            // Close the document
            document.close();

            // Show a toast indicating the PDF is saved
            Toast.makeText(getApplicationContext(), "PDF saved to " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            Log.e("path",file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}