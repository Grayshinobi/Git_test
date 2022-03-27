package com.example.dogpics.fragments

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.dogpics.R
import com.example.dogpics.databinding.CustomDialogFragBinding
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.custom_dialog_frag.*


class CustomDialog(position: Int, photoLists: List<String>, var a: Context) : DialogFragment() {
    private lateinit var binding: CustomDialogFragBinding
    private var pos = position
    private var photo = photoLists
    private var downloadManager: DownloadManager? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.custom_dialog_frag, null, false)


        val imageView = binding.img
        Glide.with(requireContext())
            .load(photo[pos])
            .fitCenter()
            .into(imageView)

        binding.customCardV.setOnLongClickListener {
            Blurry.with(context)
                .capture(img)

                .into(img)

            binding.downloadImg.visibility = View.VISIBLE
            binding.downloadImg.setOnClickListener { view ->
                downloadImage(photo = photo, pos = pos)
            }




            true
        }

        return binding.root

    }

    private fun downloadImage(photo: List<String>, pos: Int) {
        print("working")

        downloadManager = a.applicationContext.getSystemService(DOWNLOAD_SERVICE) as DownloadManager


        val request: DownloadManager.Request = DownloadManager.Request(Uri.parse(photo[pos]))
            .setTitle("Dog pic")
            .setDescription("File is downloading")
            .setDestinationInExternalFilesDir(
                requireContext(),
                Environment.DIRECTORY_DOWNLOADS,
                "dog pic"
            )
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        val download = downloadManager?.enqueue(request)
    }


}