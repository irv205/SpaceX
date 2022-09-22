package com.irv205.xpacex.core.utils

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.irv205.xpacex.R
import com.irv205.xpacex.databinding.LoadingDialogBinding
import javax.annotation.Nullable

class LoadingDialog : DialogFragment() {

    private var _bindingDialogFragment: LoadingDialogBinding? = null
    private val bindingDialogFragment get() = _bindingDialogFragment

    override fun onCreateDialog(@Nullable savedInstanceState: Bundle?): Dialog {
        _bindingDialogFragment = LoadingDialogBinding.inflate(layoutInflater)
        val dialog = Dialog(requireContext(), R.style.DialogTheme)
        dialog.window?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(requireContext(),R.color.transparent_black)))
        dialog.setCancelable(false)
        bindingDialogFragment?.let {
            dialog.setContentView(it.root)
        }

        return dialog
    }

    fun showDialog(fragmentManager: FragmentManager){
        this.isCancelable = false
        if (!this.isAdded){
            show(fragmentManager, TAG)
        }
    }

    override fun dismiss() {
        try {
            super.dismiss()
        }catch (e: Exception){}

    }
    companion object {
        val TAG: String = LoadingDialog::class.java.simpleName
        fun newInstance() = LoadingDialog()
    }
}