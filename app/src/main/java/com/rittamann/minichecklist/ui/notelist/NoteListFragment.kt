package com.rittamann.minichecklist.ui.notelist

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.rittamann.minichecklist.R
import com.rittamann.minichecklist.data.base.Note
import com.rittamann.minichecklist.ui.base.BaseActivity
import com.rittamann.minichecklist.ui.base.BaseFragment
import com.rittamann.minichecklist.ui.keepnote.KeepNoteFragment
import com.rittamann.minichecklist.utils.FragmentUtil
import kotlinx.android.synthetic.main.fragment_note_list.buttonNew
import kotlinx.android.synthetic.main.fragment_note_list.recyclerView

class NoteListFragment : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_note_list
    private var listener: OnFragmentInteractionListener? = null
    private var adapter: RecyclerAdapterNote? = null
    private lateinit var viewModel: NoteListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = NoteListViewModel(NoteListModel(activity!!))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        initObservers()
    }

    override fun onStart() {
        super.onStart()
        viewModel.fetchNoteList()
    }

    private fun initViews() {
        buttonNew.setOnClickListener {
            viewModel.addNote(Note())
        }
    }

    private fun initObservers() {
        viewModel.getCheckListResult().observe(this, Observer { list ->
            list?.also { initRecycler(list) }
        })
        viewModel.getNewItemReuslt().observe(this, Observer { item ->
            item?.also {
                if (it.id > 0) {
                    newItemCreated(it)
                } else {
                    createItemError()
                }
            }
        })
    }

    private fun newItemCreated(item: Note) {
        FragmentUtil.add(activity!! as BaseActivity, KeepNoteFragment.newInstance(item), true)
    }

    private fun createItemError() {
        Toast.makeText(
            activity!!,
            getString(R.string.error_new_item),
            Toast.LENGTH_LONG
        ).show()
    }

    private fun initRecycler(list: List<Note>) {
        if (adapter == null) {
            RecyclerAdapterNote(activity!!, list).apply {
                adapter = this
                recyclerView.adapter = this
                recyclerView.layoutManager = LinearLayoutManager(activity!!)
                recyclerView.addItemDecoration(
                    DividerItemDecoration(
                        activity!!,
                        DividerItemDecoration.VERTICAL
                    ).apply {
                        setDrawable(
                            ContextCompat.getDrawable(
                                activity!!,
                                R.drawable.custom_divider_item_decoration
                            )!!
                        )
                    }
                )
            }
        } else {
            adapter!!.forceUpdate(list)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }
}