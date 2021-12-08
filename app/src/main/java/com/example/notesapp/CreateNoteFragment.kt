package com.example.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.notesapp.database.NotesDatabase
import com.example.notesapp.entities.Notes
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class CreateNoteFragment : BaseFragment(){

    var currentDate:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CreateNoteFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dateTime = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        currentDate = dateTime.format(Date())
        tvDateTime.text = currentDate

        imgDone.setOnClickListener{
            saveNote()

        }

        imgBack.setOnClickListener {
            replaceFragment(HomeFragment.newInstance(),false)
        }
    }


    private fun saveNote() {
        if(etNoteTitle.text.isNullOrEmpty()){
           Toast.makeText(context, "Title Required", Toast.LENGTH_SHORT).show()
       }
        if(etNoteSubTitle.text.isNullOrEmpty()){
           Toast.makeText(context, "Subtitle Required", Toast.LENGTH_SHORT).show()
       }
        if(etNoteDesc.text.isNullOrEmpty()){
           Toast.makeText(context, "Description should not be empty", Toast.LENGTH_SHORT).show()
       }

        launch {
            var notes = Notes()
            notes.title = etNoteTitle.text.toString()
            notes.subTitle = etNoteSubTitle.text.toString()
            notes.noteText = etNoteDesc.text.toString()
            notes.dateTime = currentDate
            context?.let {

                NotesDatabase.getDatabase(it).noteDao().insertNotes(notes)
                etNoteTitle.setText("")
                etNoteSubTitle.setText("")
                etNoteDesc.setText("")
            }
        }

    }

    fun replaceFragment(fragment:Fragment, transition:Boolean) {
        val fragmentTransition = requireActivity()!!.supportFragmentManager.beginTransaction()

//        if(transition){
//            fragmentTransition.setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left)
//        }
        fragmentTransition.replace(R.id.frame_layout,fragment).addToBackStack(fragment.javaClass.simpleName).commit()

    }
}