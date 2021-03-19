package net.deali.popuptest

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.dialog_common.*

class CommonAlertDialogFragment : DialogFragment() {
    private var isCancelablePopup: Boolean = true
    var commonAlertDialogListener: CommonAlertDialogListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            commonAlertDialogListener = getParcelable(KEY_COMMON_ALERT_DIALOG_LISTENER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_common, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return object : Dialog(activity!!, theme) {
            override fun onBackPressed() {
                if (isCancelablePopup) dismissAllowingStateLoss()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.apply {
            isCancelable = isCancelablePopup
            setCanceledOnTouchOutside(isCancelablePopup)
//            window?.setBackgroundDrawableResource(R.drawable.bg_transparent_rounded_4dp)
        }
        tvText.text = "tvTextView"
        showing = true
    }

    override fun onDetach() {
        super.onDetach()
        commonAlertDialogListener = null
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        showing = false
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            super.show(manager, tag)
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    companion object {
        const val TAG = "CommonAlertDialogFragment"
        const val KEY_COMMON_ALERT_DIALOG_LISTENER = "KEY_ALERT_LISTENER"
        const val KEY_EVENT_ALERT_CLOSE = "KEY_EVENT_ALERT_CLOSE"

        @JvmField
        var showing = false

        @JvmStatic
        fun showForNormal(
            fragmentManager: FragmentManager,
            commonAlertDialogListener: CommonAlertDialogListener? = null
        ) {
            CommonAlertDialogFragment().apply {
                Bundle().apply {
                    putParcelable(KEY_COMMON_ALERT_DIALOG_LISTENER, commonAlertDialogListener)
                }.also {
                    arguments = it
                }
            }.show(fragmentManager, TAG)
        }

    }

    @Parcelize
    open class CommonAlertDialogListener : Parcelable {
        open fun onLeftButtonClicked() {}
        open fun onRightButtonClicked() {}
        open fun onRightButtonClicked(isChecked: Boolean) {}
    }

}