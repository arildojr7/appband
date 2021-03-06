package dev.arildo.appband.setlist.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.lifecycle.Observer
import dev.arildo.data.musician.model.Musician
import dev.arildo.data.song.model.Song
import dev.arildo.appband.R
import dev.arildo.appband.core.base.BaseActivity
import dev.arildo.appband.core.util.BUNDLE
import dev.arildo.appband.core.util.SONG
import dev.arildo.appband.databinding.ActivitySetListSetBinding
import dev.arildo.appband.setlist.adapter.SetListMusiciansAdapter
import dev.arildo.appband.setlist.adapter.SetListSongsAdapter
import dev.arildo.appband.setlist.customview.SelectMusiciansDialog
import dev.arildo.appband.setlist.customview.SelectSongsDialog
import dev.arildo.appband.setlist.viewmodel.SetListSetViewModel
import dev.arildo.appband.song.activity.SongDetailActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.*

class SetListSetActivity : BaseActivity<ActivitySetListSetBinding>(R.layout.activity_set_list_set),
    DatePickerDialog.OnDateSetListener {

    private val viewModel: SetListSetViewModel by inject()
    private val songs = mutableListOf<Song>()
    private val musicians = mutableListOf<Musician>()
    private val datePickerDialog: DatePickerDialog by lazy {
        DatePickerDialog(
            this, this, 2020, 1, 1
        )
    }

    private val adapterSelectedSongs = SetListSongsAdapter(emptyList()) {
        val bundle = Bundle()
        bundle.putParcelable(SONG, it)
        startActivity(Intent(this, SongDetailActivity::class.java).apply {
            putExtra(BUNDLE, bundle)
        })
    }

    private val adapterSelectedMusicians = SetListMusiciansAdapter(emptyList()) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupRecycler()
        setupListeners()
        setupScrollView()

        launch {
            viewModel.getMusicians()
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

        binding.fabAddMusicians.setOnClickListener {
            SelectMusiciansDialog.Companion.Builder(this).setListener(
                object : SelectMusiciansDialog.SelectMusiciansDialogListener {
                    override fun onSelectMusician(musician: Musician, dialog: SelectMusiciansDialog?) {
                        viewModel.addSelectedMusician(musician)
                        dialog?.alertDialog?.dismiss()


                    }

                    override fun onDismissDialog(dialog: SelectMusiciansDialog?) {
                        dialog?.alertDialog?.dismiss()
                    }
                }
            ).setData(musicians).build()
        }
        binding.btnSelectDate.setOnClickListener {
            datePickerDialog.show()
        }

        binding.fabSaveSetList.setOnClickListener {
            launch {
                viewModel.createSetList()
                delay(200)
                finish()
            }
        }
    }

    private fun setupRecycler() {
        binding.rvSongs.apply {
            adapter = adapterSelectedSongs
        }
        binding.rvMusicians.apply {
            adapter = adapterSelectedMusicians
        }
    }

    override fun subscribeUi() {
        viewModel.selectedSongs.observe(this, Observer {
            adapterSelectedSongs.setData(it)
        })

        viewModel.selectedMusicians.observe(this, Observer {
            adapterSelectedMusicians.setData(it)
        })

        viewModel.songs.observe(this, Observer {
            songs.addAll(it)
        })
        viewModel.musicians.observe(this, Observer {
            musicians.addAll(it)
        })
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val c = Calendar.getInstance()
        c[Calendar.YEAR] = year
        c[Calendar.MONTH] = month
        c[Calendar.DAY_OF_MONTH] = dayOfMonth

        binding.btnSelectDate.text = SimpleDateFormat("dd/MM/yyyy").format(c.time)

        viewModel.setDateTimestamp(c.time.time)
    }
}
