package dev.arildo.appband.setlist.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.lifecycle.Observer
import com.arildojr.data.song.model.Song
import dev.arildo.appband.R
import dev.arildo.appband.core.base.BaseActivity
import dev.arildo.appband.databinding.ActivitySetListSetBinding
import dev.arildo.appband.setlist.adapter.SetListSongsAdapter
import dev.arildo.appband.setlist.customview.SelectSongsDialog
import dev.arildo.appband.setlist.viewmodel.SetListSetViewModel
import dev.arildo.appband.songlist.SongDetailActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*

class SetListSetActivity : BaseActivity<ActivitySetListSetBinding>(R.layout.activity_set_list_set),
    DatePickerDialog.OnDateSetListener {

    private val viewModel: SetListSetViewModel by inject()
    private val songs = mutableListOf<Song>()
    private val datePickerDialog: DatePickerDialog by lazy {
        DatePickerDialog(
            this, this, 2020, 1, 1
        )
    }

    private val adapter2 = SetListSongsAdapter(emptyList()) {
        val bundle = Bundle()
        bundle.putParcelable("song", it)
        startActivity(Intent(this, SongDetailActivity::class.java).apply {
            putExtra(
                "bundle",
                bundle
            )
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupRecycler()
        setupListeners()
        setupScrollView()

        launch {
            viewModel.getSongs()
        }
    }

    private fun setupScrollView() {
        binding.svContainer.apply {
            viewTreeObserver.addOnScrollChangedListener {
                binding.llTopHeader.isSelected = this.canScrollVertically(-1)
            }
        }
    }

    private fun setupListeners() {
        binding.fabAddSongs.setOnClickListener {
            SelectSongsDialog.Companion.Builder(this).setListener(
                object : SelectSongsDialog.SelectSongsDialogListener {
                    override fun onSelectSong(song: Song, dialog: SelectSongsDialog?) {
                        viewModel.addSelectedSong(song)
                        dialog?.alertDialog?.dismiss()

                        launch {
                            delay(100)
                            binding.svContainer.apply { smoothScrollTo(0, height) }
                        }
                    }

                    override fun onDismissDialog(dialog: SelectSongsDialog?) {
                        dialog?.alertDialog?.dismiss()
                    }
                }
            ).setData(songs).build()
        }
        binding.fabSaveSetList.setOnClickListener {
            finish()
        }
        binding.etSelectDate.apply {
            setOnClickListener {
                datePickerDialog.show()
            }
            showSoftInputOnFocus = false
            isFocusableInTouchMode = false
        }
    }

    private fun setupRecycler() {
        binding.rvSongs.apply {
            adapter = adapter2
        }
    }

    override fun subscribeUi() {
        viewModel.selectedSongs.observe(this, Observer {
            adapter2.setData(it)
        })

        viewModel.songs.observe(this, Observer {
            songs.addAll(it)
        })
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val c = Calendar.getInstance()
        c[Calendar.YEAR] = year
        c[Calendar.MONTH] = month
        c[Calendar.DAY_OF_MONTH] = dayOfMonth

        binding.etSelectDate.setText(
            SimpleDateFormat("dd/MM/yyyy").format(c.time)

        )

        viewModel.setDateTimestamp(c.time.time)

    }
}
