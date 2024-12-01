package com.example.quicktestapplication.pdfrender

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.quicktestapplication.databinding.ItemPdfPageBinding

class PDFPageViewHolder(
    private val binding: ItemPdfPageBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(pageBitmap: Bitmap) {
        binding.ivPageContent.setImageBitmap(pageBitmap)
    }
}

class PDFAdapter(
    private val pageList: List<Bitmap>
): RecyclerView.Adapter<PDFPageViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PDFPageViewHolder {
        return PDFPageViewHolder(ItemPdfPageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: PDFPageViewHolder,
        position: Int
    ) {
        holder.bind(pageList[position])
    }

    override fun getItemCount(): Int {
        return pageList.size
    }
}