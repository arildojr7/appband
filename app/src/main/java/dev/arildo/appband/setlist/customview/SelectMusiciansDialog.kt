package dev.arildo.appband.setlist.customview

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.arildojr.data.musician.model.Musician
import dev.arildo.appband.R
import dev.arildo.appband.setlist.adapter.SetListMusiciansAdapter
import kotlinx.android.synthetic.main.dialog_select_musicians.view.*

class SelectMusiciansDialog {

    interface SelectMusiciansDialogListener {
        fun onSelectMusician(musician: Musician, dialog: SelectMusiciansDialog?)
        fun onDismissDialog(dialog: SelectMusiciansDialog?)
    }

    private val adapter2 =
        SetListMusiciansAdapter(emptyList()) {
            listener.onSelectMusician(it, this)
        }

    lateinit var alertDialog: AlertDialog
    private lateinit var listener: SelectMusiciansDialogListener

    private fun createAlert(context: Context) {

        val dialogView =
            LayoutInflater.from(context).inflate(R.layout.dialog_select_musicians, null)
        val alertDialogBuilder = AlertDialog.Builder(context, R.style.RoundAlertDialog)

        alertDialogBuilder.setView(dialogView)

        dialogView.rvMusicians.adapter = adapter2

        alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    companion object {
        class Builder(private val mContext: Context) {

            var dialog = SelectMusiciansDialog()

            fun setData(songs: List<Musician>) = apply { dialog.adapter2.setData(songs) }

            fun setListener(mListener: SelectMusiciansDialogListener) =
                apply { dialog.listener = mListener }

            fun build() = dialog.createAlert(mContext)
        }
    }
}
