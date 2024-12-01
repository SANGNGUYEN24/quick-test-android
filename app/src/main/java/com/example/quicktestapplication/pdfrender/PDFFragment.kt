package com.example.quicktestapplication.pdfrender

import android.content.Context
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quicktestapplication.databinding.LayoutPdfBinding
import kotlinx.coroutines.launch

class PDFFragment : Fragment() {
    private lateinit var binding: LayoutPdfBinding
    private val viewModel by viewModels<PDFViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutPdfBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            val baseUrl = "https://www.antennahouse.com/"
//            val baseUrl = "https://ontheline.trincoll.edu"
//            val url = "https://www.adobe.com/support/products/enterprise/knowledgecenter/media/c4611_sample_explain.pdf"
            val url = "https://ontheline.trincoll.edu/images/bookdown/sample-local-pdf.pdf"
            val pdfFile =
                viewModel.downloadPdf(baseUrl, url, requireContext().cacheDir.absolutePath, false)
            val fileDescriptor =
                ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY)
            val pdfRenderer = PdfRenderer(fileDescriptor)
            binding.tvPageCount.text = "# page = ${pdfRenderer.pageCount}"
            val pageBitmaps = viewModel.createPageBitmaps(
                pdfRenderer,
                getDeviceDpi(requireContext()),
                getScreenWidth(requireContext())
            )
            pdfRenderer.close()
            val pdfAdapter = PDFAdapter(pageBitmaps)
            binding.rcvPdf.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = pdfAdapter
            }
            binding.pbLoading.isVisible = false
        }
    }

    fun getDeviceDpi(context: Context): Int {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        return displayMetrics.densityDpi
    }

    fun getScreenWidth(context: Context): Int {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        return displayMetrics.widthPixels
    }
}