package com.example.pdfreader;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.pdfreader.databinding.ActivityPdfBinding;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class PdfActivity extends AppCompatActivity {
    ActivityPdfBinding binding;
    String filePath = "";
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.backBtn.setOnClickListener(v -> {
            onBackPressed();
        });

        pdfView = findViewById(R.id.pdfView);
        filePath = getIntent().getStringExtra("path");
        String pdfName = getIntent().getStringExtra("name");
        File file = new File(filePath);
        Uri uri = Uri.fromFile(file);
        pdfView.fromUri(uri).load();

        binding.title.setText(pdfName);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}