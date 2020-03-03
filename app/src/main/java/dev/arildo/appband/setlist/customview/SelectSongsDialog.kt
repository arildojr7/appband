package dev.arildo.appband.setlist.customview

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.arildojr.data.song.model.Song
import dev.arildo.appband.R
import dev.arildo.appband.songlist.SongsAdapter
import kotlinx.android.synthetic.main.dialog_select_songs.view.*

class SelectSongsDialog {

    interface SelectSongsDialogListener {
        fun onSelectSong(song: Song, dialog: SelectSongsDialog?)
        fun onDismissDialog(dialog: SelectSongsDialog?)
    }

    private val adapter2 = SongsAdapter(emptyList()) {
        listener.onSelectSong(it, this)
    }

    lateinit var alertDialog: AlertDialog
    private lateinit var listener: SelectSongsDialogListener

    private fun createAlert(context: Context) {

        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_select_songs, null)
        val alertDialogBuilder = AlertDialog.Builder(context, R.style.RoundAlertDialog)

        alertDialogBuilder.setView(dialogView)

        dialogView.rvSongs.adapter = adapter2

        alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    companion object {
        class Builder(private val mContext: Context) {

            var dialog = SelectSongsDialog()

            fun setData(songs: List<Song>) = apply { dialog.adapter2.setData(songs) }

            fun setListener(mListener: SelectSongsDialogListener) =
                apply { dialog.listener = mListener }

            fun build() = dialog.createAlert(mContext)
        }
    }
}
