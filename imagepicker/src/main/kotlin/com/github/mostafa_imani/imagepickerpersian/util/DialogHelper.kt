package com.github.mostafa_imani.imagepickerpersian.util

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.github.mostafa_imani.imagepickerpersian.R
import com.github.mostafa_imani.imagepickerpersian.constant.ImageProvider
import com.github.mostafa_imani.imagepickerpersian.listener.DismissListener
import com.github.mostafa_imani.imagepickerpersian.listener.ResultListener


/**
 * Show Dialog
 *
 * @author Dhaval Patel
 * @version 1.0
 * @since 04 January 2018
 */
internal object DialogHelper {

    /**
     * Show Image Provide Picker Dialog. This will streamline the code to pick/capture image
     *
     */
    fun showChooseAppDialog(
        context: Context,
        listener: ResultListener<ImageProvider>,
        dismissListener: DismissListener?,
        resources: Resources?
    ) {
        val layoutInflater = LayoutInflater.from(context)
        val customView = layoutInflater.inflate(R.layout.dialog_choose_app, null)

        resources?.let {
        try {
            setDefaultLanguage(it,customView)
        }   catch (e:Exception){
            // no need set phone default
        }
        }

        val dialog = AlertDialog.Builder(context)
//            .setTitle(R.string.title_choose_image_provider)
            .setView(customView)
            .setOnCancelListener {
                listener.onResult(null)
            }
            .setNegativeButton(R.string.action_cancel) { _, _ ->
                listener.onResult(null)
            }
            .setOnDismissListener {
                dismissListener?.onDismiss()
            }
            .show()

        // Handle Camera option click
        customView.findViewById<View>(R.id.lytCameraPick).setOnClickListener {
            listener.onResult(ImageProvider.CAMERA)
            dialog.dismiss()
        }

        // Handle Gallery option click
        customView.findViewById<View>(R.id.lytGalleryPick).setOnClickListener {
            listener.onResult(ImageProvider.GALLERY)
            dialog.dismiss()
        }

    }

    private fun setDefaultLanguage(resources: Resources, customView: View) {
        val cameraTitle =  resources.getString(R.string.title_camera)
        val galleryTitle = resources.getString(R.string.title_gallery)
        cameraTitle.let {
            val tvCameraTitle =   customView.findViewById<View>(R.id.tvCameraDialogTitle)
            tvCameraTitle as TextView
            tvCameraTitle.text = it
        }
        galleryTitle.let {
            val tvGalleryTitle =   customView.findViewById<View>(R.id.tvGalleryDialogTitle)
            tvGalleryTitle as TextView
            tvGalleryTitle.text = it
        }
    }

}
